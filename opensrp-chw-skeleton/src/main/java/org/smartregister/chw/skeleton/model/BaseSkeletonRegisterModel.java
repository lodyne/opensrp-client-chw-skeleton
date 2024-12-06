package org.smartregister.chw.skeleton.model;

import org.json.JSONObject;
import org.smartregister.chw.skeleton.contract.SkeletonRegisterContract;
import org.smartregister.chw.skeleton.util.SkeletonJsonFormUtils;

public class BaseSkeletonRegisterModel implements SkeletonRegisterContract.Model {

    @Override
    public JSONObject getFormAsJson(String formName, String entityId, String currentLocationId) throws Exception {
        JSONObject jsonObject = SkeletonJsonFormUtils.getFormAsJson(formName);
        SkeletonJsonFormUtils.getRegistrationForm(jsonObject, entityId, currentLocationId);

        return jsonObject;
    }

}
