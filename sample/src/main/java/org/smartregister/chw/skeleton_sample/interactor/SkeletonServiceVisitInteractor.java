package org.smartregister.chw.skeleton_sample.interactor;

import org.smartregister.chw.skeleton.domain.MemberObject;
import org.smartregister.chw.skeleton.interactor.BaseSkeletonServiceVisitInteractor;
import org.smartregister.chw.skeleton_sample.activity.EntryActivity;


public class SkeletonServiceVisitInteractor extends BaseSkeletonServiceVisitInteractor {
    public SkeletonServiceVisitInteractor(String visitType) {
        super(visitType);
    }

    @Override
    public MemberObject getMemberClient(String memberID, String profileType) {
        return EntryActivity.getSampleMember();
    }
}
