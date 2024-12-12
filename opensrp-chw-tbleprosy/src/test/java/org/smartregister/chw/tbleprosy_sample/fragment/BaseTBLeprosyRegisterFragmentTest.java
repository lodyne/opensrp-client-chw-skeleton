package org.smartregister.chw.tbleprosy_sample.fragment;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.reflect.Whitebox;
import org.smartregister.chw.tbleprosy.activity.BaseTBLeprosyProfileActivity;
import org.smartregister.chw.tbleprosy.fragment.BaseTBLeprosyRegisterFragment;
import org.smartregister.commonregistry.CommonPersonObjectClient;

import static org.mockito.Mockito.times;

public class BaseTBLeprosyRegisterFragmentTest {
    @Mock
    public BaseTBLeprosyRegisterFragment baseTestRegisterFragment;

    @Mock
    public CommonPersonObjectClient client;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test(expected = Exception.class)
    public void openProfile() throws Exception {
        Whitebox.invokeMethod(baseTestRegisterFragment, "openProfile", client);
        PowerMockito.mockStatic(BaseTBLeprosyProfileActivity.class);
        BaseTBLeprosyProfileActivity.startProfileActivity(null, null);
        PowerMockito.verifyStatic(times(1));

    }
}
