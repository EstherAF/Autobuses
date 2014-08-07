package es.estheraf.horariosbus;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import es.estheraf.horariosbus.fragment.SearchFragment;


/**
 * @author Félix Queiruga Balado
 */
public class MainActivity extends Activity {

    /**
     * Singleton
     */
    private static MainActivity instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        instance = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new SearchFragment())
                    .commit();
        }
    }

    /** Get singleton instance of activity **/
    public static MainActivity getInstance() {
        return instance;
    }

    /** Returns context of this activity **/
    public static Context getContext(){
        return instance.getApplicationContext();
    }

}
