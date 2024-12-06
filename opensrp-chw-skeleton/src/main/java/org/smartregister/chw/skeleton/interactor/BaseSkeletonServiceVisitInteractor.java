package org.smartregister.chw.skeleton.interactor;


import android.content.Context;

import androidx.annotation.VisibleForTesting;

import org.apache.commons.lang3.StringUtils;
import org.smartregister.chw.skeleton.R;
import org.smartregister.chw.skeleton.SkeletonLibrary;
import org.smartregister.chw.skeleton.actionhelper.SkeletonHtsActionHelper;
import org.smartregister.chw.skeleton.actionhelper.SkeletonMedicalHistoryActionHelper;
import org.smartregister.chw.skeleton.contract.BaseSkeletonVisitContract;
import org.smartregister.chw.skeleton.domain.MemberObject;
import org.smartregister.chw.skeleton.domain.VisitDetail;
import org.smartregister.chw.skeleton.model.BaseSkeletonVisitAction;
import org.smartregister.chw.skeleton.util.AppExecutors;
import org.smartregister.chw.skeleton.util.Constants;
import org.smartregister.sync.helper.ECSyncHelper;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import timber.log.Timber;

public class BaseSkeletonServiceVisitInteractor extends BaseSkeletonVisitInteractor {

    protected BaseSkeletonVisitContract.InteractorCallBack callBack;

    String visitType;
    private final SkeletonLibrary skeletonLibrary;
    private final LinkedHashMap<String, BaseSkeletonVisitAction> actionList;
    protected AppExecutors appExecutors;
    private ECSyncHelper syncHelper;
    private Context mContext;


    @VisibleForTesting
    public BaseSkeletonServiceVisitInteractor(AppExecutors appExecutors, SkeletonLibrary SkeletonLibrary, ECSyncHelper syncHelper) {
        this.appExecutors = appExecutors;
        this.skeletonLibrary = SkeletonLibrary;
        this.syncHelper = syncHelper;
        this.actionList = new LinkedHashMap<>();
    }

    public BaseSkeletonServiceVisitInteractor(String visitType) {
        this(new AppExecutors(), SkeletonLibrary.getInstance(), SkeletonLibrary.getInstance().getEcSyncHelper());
        this.visitType = visitType;
    }

    @Override
    protected String getCurrentVisitType() {
        if (StringUtils.isNotBlank(visitType)) {
            return visitType;
        }
        return super.getCurrentVisitType();
    }

    @Override
    protected void populateActionList(BaseSkeletonVisitContract.InteractorCallBack callBack) {
        this.callBack = callBack;
        final Runnable runnable = () -> {
            try {
                evaluateSkeletonMedicalHistory(details);
                evaluateSkeletonPhysicalExam(details);
                evaluateSkeletonHTS(details);

            } catch (BaseSkeletonVisitAction.ValidationException e) {
                Timber.e(e);
            }

            appExecutors.mainThread().execute(() -> callBack.preloadActions(actionList));
        };

        appExecutors.diskIO().execute(runnable);
    }

    private void evaluateSkeletonMedicalHistory(Map<String, List<VisitDetail>> details) throws BaseSkeletonVisitAction.ValidationException {

        SkeletonMedicalHistoryActionHelper actionHelper = new SkeletonMedicalHistory(mContext, memberObject);
        BaseSkeletonVisitAction action = getBuilder(context.getString(R.string.skeleton_medical_history))
                .withOptional(true)
                .withDetails(details)
                .withHelper(actionHelper)
                .withFormName(Constants.SKELETON_FOLLOWUP_FORMS.MEDICAL_HISTORY)
                .build();
        actionList.put(context.getString(R.string.skeleton_medical_history), action);

    }

    private void evaluateSkeletonPhysicalExam(Map<String, List<VisitDetail>> details) throws BaseSkeletonVisitAction.ValidationException {

        SkeletonPhysicalExamActionHelper actionHelper = new SkeletonPhysicalExamActionHelper(mContext, memberObject);
        BaseSkeletonVisitAction action = getBuilder(context.getString(R.string.skeleton_physical_examination))
                .withOptional(true)
                .withDetails(details)
                .withHelper(actionHelper)
                .withFormName(Constants.SKELETON_FOLLOWUP_FORMS.PHYSICAL_EXAMINATION)
                .build();
        actionList.put(context.getString(R.string.skeleton_physical_examination), action);
    }

    private void evaluateSkeletonHTS(Map<String, List<VisitDetail>> details) throws BaseSkeletonVisitAction.ValidationException {

        SkeletonHtsActionHelper actionHelper = new SkeletonHtsActionHelper(mContext, memberObject);
        BaseSkeletonVisitAction action = getBuilder(context.getString(R.string.skeleton_hts))
                .withOptional(true)
                .withDetails(details)
                .withHelper(actionHelper)
                .withFormName(Constants.SKELETON_FOLLOWUP_FORMS.HTS)
                .build();
        actionList.put(context.getString(R.string.skeleton_hts), action);
    }

    @Override
    protected String getEncounterType() {
        return Constants.EVENT_TYPE.SKELETON_SERVICES;
    }

    @Override
    protected String getTableName() {
        return Constants.TABLES.SKELETON_SERVICE;
    }

    private class SkeletonMedicalHistory extends org.smartregister.chw.skeleton.actionhelper.SkeletonMedicalHistoryActionHelper {


        public SkeletonMedicalHistory(Context context, MemberObject memberObject) {
            super(context, memberObject);
        }

        @Override
        public String postProcess(String s) {
            if (StringUtils.isNotBlank(medical_history)) {
                try {
                    evaluateSkeletonPhysicalExam(details);
                    evaluateSkeletonHTS(details);
                } catch (BaseSkeletonVisitAction.ValidationException e) {
                    e.printStackTrace();
                }
            }
            new AppExecutors().mainThread().execute(() -> callBack.preloadActions(actionList));
            return super.postProcess(s);
        }

    }

    private class SkeletonPhysicalExamActionHelper extends org.smartregister.chw.skeleton.actionhelper.SkeletonPhysicalExamActionHelper {

        public SkeletonPhysicalExamActionHelper(Context context, MemberObject memberObject) {
            super(context, memberObject);
        }

        @Override
        public String postProcess(String s) {
            if (StringUtils.isNotBlank(medical_history)) {
                try {
                    evaluateSkeletonHTS(details);
                } catch (BaseSkeletonVisitAction.ValidationException e) {
                    e.printStackTrace();
                }
            }
            new AppExecutors().mainThread().execute(() -> callBack.preloadActions(actionList));
            return super.postProcess(s);
        }

    }

}
