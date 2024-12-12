package org.smartregister.chw.tbleprosy_sample.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.widget.Toolbar;

import com.vijay.jsonwizard.activities.JsonFormActivity;
import com.vijay.jsonwizard.activities.JsonWizardFormActivity;
import com.vijay.jsonwizard.utils.FormUtils;

import org.json.JSONException;
import org.json.JSONObject;
import org.smartregister.chw.tbleprosy.contract.BaseTBLeprosyVisitContract;
import org.smartregister.chw.tbleprosy.domain.MemberObject;
import org.smartregister.chw.tbleprosy.util.DBConstants;
import org.smartregister.chw.tbleprosy_sample.R;
import org.smartregister.commonregistry.CommonPersonObjectClient;
import org.smartregister.view.activity.SecuredActivity;

import java.util.HashMap;
import java.util.Map;

import timber.log.Timber;

public class EntryActivity extends SecuredActivity implements View.OnClickListener, BaseTBLeprosyVisitContract.VisitView {
    private static MemberObject tbleprosyMemberObject;

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

        if (tbleprosyMemberObject == null) {
            tbleprosyMemberObject = new MemberObject();
            tbleprosyMemberObject.setFirstName("Glory");
            tbleprosyMemberObject.setLastName("Juma");
            tbleprosyMemberObject.setMiddleName("Ali");
            tbleprosyMemberObject.setGender("Female");
            tbleprosyMemberObject.setMartialStatus("Married");
            tbleprosyMemberObject.setDob("1982-01-18T03:00:00.000+03:00");
            tbleprosyMemberObject.setUniqueId("3503504");
            tbleprosyMemberObject.setBaseEntityId("3503504");
            tbleprosyMemberObject.setFamilyBaseEntityId("3503504");
        }

        return tbleprosyMemberObject;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        findViewById(R.id.tbleprosy_activity).setOnClickListener(this);
        findViewById(R.id.tbleprosy_home_visit).setOnClickListener(this);
        findViewById(R.id.tbleprosy_profile).setOnClickListener(this);
        findViewById(R.id.tbleprosy_tb_screening).setOnClickListener(this);
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
            case R.id.tbleprosy_activity:
                startActivity(new Intent(this, TBLeprosyRegisterActivity.class));
                break;
            case R.id.tbleprosy_tb_screening:
//                tbLeprosyRegisterActivity.startFormActivity("tbleprosy_screening","12345","");

                startFormActivity("tbleprosy_screening","12345","");
            break;
            case R.id.tbleprosy_home_visit:
                TBLeprosyServiceActivity.startTBLeprosyVisitActivity(this, "12345", true);
                break;
            case R.id.tbleprosy_profile:
                TBLeprosyMemberProfileActivity.startMe(this, "12345");
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

    public void startFormActivity(JSONObject jsonObject) {

//        Launch form from parent Activity using Intent
        Intent intent = new Intent(this, JsonFormActivity.class);

//        Convert JSON form to String
        intent.putExtra("json",jsonObject.toString());

//        startActivity(intent);

        startActivityForResult(intent, 700);

    }
    public void startFormActivity(String formName, String entityId, String metadata) {
//        Retrieve the JSON form file in a predefined location,
//        such as the application's assets or a repository directory
//        and parse it into a JSONObject
        try {
            JSONObject jsonObject = new FormUtils().
                    getFormJsonFromRepositoryOrAssets(this,formName);

//        Link JSON object to the JSON form using entity_id Key
            jsonObject.put("entity_id", entityId);

//          Call startFormActivity that includes the launched form
            startFormActivity(jsonObject);


        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

}