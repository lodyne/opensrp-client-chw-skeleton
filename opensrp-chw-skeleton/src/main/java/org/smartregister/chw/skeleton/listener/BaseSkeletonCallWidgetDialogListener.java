package org.smartregister.chw.skeleton.listener;


import android.view.View;

import org.smartregister.chw.skeleton.fragment.BaseSkeletonCallDialogFragment;
import org.smartregister.chw.skeleton.R;

public class BaseSkeletonCallWidgetDialogListener implements View.OnClickListener {

    private BaseSkeletonCallDialogFragment callDialogFragment;

    public BaseSkeletonCallWidgetDialogListener(BaseSkeletonCallDialogFragment dialogFragment) {
        callDialogFragment = dialogFragment;
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.skeleton_call_close) {
            callDialogFragment.dismiss();
        }
    }
}
