package es.estheraf.horariosbus.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import java.util.List;

import es.estheraf.horariosbus.R;
import es.estheraf.horariosbus.data.DataProviderFacade;
import es.estheraf.horariosbus.data.model.SearchRoute;
import es.estheraf.horariosbus.data.model.SimpleResultRoute;
import es.estheraf.horariosbus.ui.BundleKey;
import es.estheraf.horariosbus.ui.fragment.ResultFragment;
import es.estheraf.horariosbus.ui.fragment.SearchFragment;
import es.estheraf.horariosbus.ui.model.UIResult;


/**
 * @author Félix Queiruga Balado
 */
public class MainActivity extends Activity {

    /**
     * Singleton
     */
    private static MainActivity instance;

    private Bundle bundle = new Bundle();

    private SearchFragment searchFragment;

    private ResultFragment resultFragment;

    /**
     * Get singleton instance of activity
     */
    public static MainActivity getInstance() {
        return instance;
    }

    /**
     * Returns context of this activity
     */
    public static Context getContext() {
        return instance.getApplicationContext();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        instance = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            searchFragment = showSearchFragment(bundle);
        } else {
            bundle = savedInstanceState.getBundle(BundleKey.MAIN_ACTIVITY_BUNDLE.val());
        }
    }

    /**
     * Handle Search operation. Takes SEARCH_FILTER from the Bundle.
     */
    public void doSearch() {
        SearchRoute search = (SearchRoute) bundle.getSerializable(BundleKey.SEARCH_FILTERS.val());
        //Business logic: do search
        List<SimpleResultRoute> results = DataProviderFacade.getInstance().doSearch(search);
        //Put results in bundle
        bundle.putSerializable(BundleKey.RESULTS.val(), new UIResult(search, results));
        //Show result fragment
        resultFragment = showResultFragment(bundle);
    }

    private SearchFragment showSearchFragment(Bundle bundle) {
        SearchFragment fragment = SearchFragment.newInstance(bundle);
        getFragmentManager().beginTransaction()
                .add(R.id.container, fragment)
                .commit();
        return fragment;
    }

    private ResultFragment showResultFragment(Bundle bundle) {
        ResultFragment fragment = ResultFragment.newInstance(bundle);
        getFragmentManager().beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack(null)
                .commit();
        getFragmentManager().executePendingTransactions();
        return resultFragment;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save actual entries in private bundle
        outState.putBundle(BundleKey.MAIN_ACTIVITY_BUNDLE.val(), bundle);
    }
}
