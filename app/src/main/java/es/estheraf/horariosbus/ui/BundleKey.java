package es.estheraf.horariosbus.ui;

import android.os.Bundle;

/**
 * Created by Esther on 26/10/2014.
 */
public enum BundleKey {
    PICKED_DATE ("pickedDate"),
    SEARCH_FILTERS ("searchFilters"),
    RESULTS ("results"),
    MAIN_ACTIVITY_BUNDLE ("mainActivityBundle");

    private final String name;

    private BundleKey(String s) {
        name = s;
    }

    public boolean equalsName(String otherName){
        return (otherName == null)? false:name.equals(otherName);
    }

    public String val(){
        return name;
    }

    public String toString(){
        return name;
    }

//    public static void copyBundleKeys(Bundle from, Bundle to, boolean override){
//        for(BundleKey keyEnum : BundleKey.values()){
//            String key = keyEnum.val();
//            if(from.containsKey(key)) {
//                if (override || !to.containsKey(key))
//                    to.putSerializable(key, from.getSerializable(key));
//            }
//        }
//    }
}
