package org.smartregister.chw.tbleprosy.presenter;

import static org.apache.commons.lang3.StringUtils.trim;

import org.apache.commons.lang3.StringUtils;
import org.smartregister.chw.tbleprosy.contract.TBLeprosyRegisterFragmentContract;
import org.smartregister.chw.tbleprosy.util.Constants;
import org.smartregister.chw.tbleprosy.util.DBConstants;
import org.smartregister.configurableviews.model.RegisterConfiguration;
import org.smartregister.configurableviews.model.View;
import org.smartregister.configurableviews.model.ViewConfiguration;

import java.lang.ref.WeakReference;
import java.util.Set;
import java.util.TreeSet;

public class BaseTBLeprosyRegisterFragmentPresenter implements TBLeprosyRegisterFragmentContract.Presenter {

    protected WeakReference<TBLeprosyRegisterFragmentContract.View> viewReference;

    protected TBLeprosyRegisterFragmentContract.Model model;

    protected RegisterConfiguration config;

    protected Set<View> visibleColumns = new TreeSet<>();
    protected String viewConfigurationIdentifier;

    public BaseTBLeprosyRegisterFragmentPresenter(TBLeprosyRegisterFragmentContract.View view, TBLeprosyRegisterFragmentContract.Model model, String viewConfigurationIdentifier) {
        this.viewReference = new WeakReference<>(view);
        this.model = model;
        this.viewConfigurationIdentifier = viewConfigurationIdentifier;
        this.config = model.defaultRegisterConfiguration();
    }

    @Override
    public String getMainCondition() {
        return " "+getMainTable()+".is_closed = 0 ";
    }

    @Override
    public String getDefaultSortQuery() {
        return getMainTable() + "." + DBConstants.KEY.LAST_INTERACTED_WITH + " DESC ";
    }

    @Override
    public void processViewConfigurations() {
        if (StringUtils.isBlank(viewConfigurationIdentifier)) {
            return;
        }

        ViewConfiguration viewConfiguration = model.getViewConfiguration(viewConfigurationIdentifier);
        if (viewConfiguration != null) {
            config = (RegisterConfiguration) viewConfiguration.getMetadata();
            this.visibleColumns = model.getRegisterActiveColumns(viewConfigurationIdentifier);
        }

        if (config.getSearchBarText() != null && getView() != null) {
            getView().updateSearchBarHint(config.getSearchBarText());
        }
    }

    @Override
    public void initializeQueries(String mainCondition) {
        String tableName = getMainTable();
        mainCondition = trim(getMainCondition()).equals("") ? mainCondition : getMainCondition();
        String countSelect = model.countSelect(tableName, mainCondition);
        String mainSelect = model.mainSelect(tableName, mainCondition);

        if (getView() != null) {

            getView().initializeQueryParams(tableName, countSelect, mainSelect);
            getView().initializeAdapter(visibleColumns);

            getView().countExecute();
            getView().filterandSortInInitializeQueries();
        }
    }

    protected TBLeprosyRegisterFragmentContract.View getView() {
        if (viewReference != null)
            return viewReference.get();
        else
            return null;
    }

    @Override
    public void startSync() {
//        implement

    }

    @Override
    public void searchGlobally(String s) {
//        implement

    }

    @Override
    public String getMainTable() {
        return Constants.TABLES.TBLEPROSY_ENROLLMENT;
    }

    @Override
    public String getDueFilterCondition() {
        return " (cast( julianday(STRFTIME('%Y-%m-%d', datetime('now'))) -  julianday(IFNULL(SUBSTR(tbleprosy_test_date,7,4)|| '-' || SUBSTR(tbleprosy_test_date,4,2) || '-' || SUBSTR(tbleprosy_test_date,1,2),'')) as integer) between 7 and 14) ";
    }
}
