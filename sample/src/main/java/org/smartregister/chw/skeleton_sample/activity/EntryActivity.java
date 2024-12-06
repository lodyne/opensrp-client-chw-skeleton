package org.smartregister.chw.skeleton_sample.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.widget.Toolbar;

import org.smartregister.chw.skeleton.contract.BaseSkeletonVisitContract;
import org.smartregister.chw.skeleton.domain.MemberObject;
import org.smartregister.chw.skeleton.util.DBConstants;
import org.smartregister.chw.skeleton_sample.R;
import org.smartregister.commonregistry.CommonPersonObjectClient;
import org.smartregister.view.activity.SecuredActivity;

import java.util.HashMap;
import java.util.Map;

import timber.log.Timber;

public class EntryActivity extends SecuredActivity implements View.OnClickListener, BaseSkeletonVisitContract.VisitView {
    private static MemberObject skeletonMemberObject;

    public static MemberObject getSampleMember() {
        Map<String, String> details = new HashMap<>();
        details.put(DBConstants.KEY.FIRST_NAME, "Glory");
        details.put(DBConstants.KEY.LAST_NAME, "Juma");
        details.put(DBConstants.KEY.MIDDLE_NAME, "Wambui");
        details.put(DBConstants.KEY.DOB, "1982-01-18T03:00:00.000+03:00");
        details.put(DBConstants.KEY.LAST_HOME_VISIT, "");
        details.put(DBConstants.KEY.VILLAGE_TOWN, "Lavingtone #221");
        details.put(DBConstants.KEY.FAMILY_NAME, "Jumwa");
        details.put(DBConstants.KEY.UNIQUE_ID, "3503504");
        details.put(DBConstants.KEY.BASE_ENTITY_ID, "3503504");
        details.put(DBConstants.KEY.FAMILY_HEAD, "3503504");
        details.put(DBConstants.KEY.PHONE_NUMBER, "0934567543");
        CommonPersonObjectClient commonPersonObject = new CommonPersonObjectClient("", details, "Yo");
        commonPersonObject.setColumnmaps(details);

        if (skeletonMemberObject == null) {
            skeletonMemberObject = new MemberObject();
            skeletonMemberObject.setFirstName("Glory");
            skeletonMemberObject.setLastName("Juma");
            skeletonMemberObject.setMiddleName("Ali");
            skeletonMemberObject.setGender("Female");
            skeletonMemberObject.setMartialStatus("Married");
            skeletonMemberObject.setDob("1982-01-18T03:00:00.000+03:00");
            skeletonMemberObject.setUniqueId("3503504");
            skeletonMemberObject.setBaseEntityId("3503504");
            skeletonMemberObject.setFamilyBaseEntityId("3503504");
        }

        return skeletonMemberObject;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        findViewById(R.id.skeleton_activity).setOnClickListener(this);
        findViewById(R.id.skeleton_home_visit).setOnClickListener(this);
        findViewById(R.id.skeleton_profile).setOnClickListener(this);
    }

    @Override
    protected void onCreation() {
        Timber.v("onCreation");
    }

    @Override
    protected void onResumption() {
        Timber.v("onCreation");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.skeleton_activity:
                startActivity(new Intent(this, SkeletonRegisterActivity.class));
                break;
            case R.id.skeleton_home_visit:
                SkeletonServiceActivity.startSkeletonVisitActivity(this, "12345", true);
                break;
            case R.id.skeleton_profile:
                SkeletonMemberProfileActivity.startMe(this, "12345");
                break;
            default:
                break;
        }
    }

    @Override
    public void onDialogOptionUpdated(String jsonString) {
        Timber.v("onDialogOptionUpdated %s", jsonString);
    }

    @Override
    public Context getMyContext() {
        return this;
    }
}