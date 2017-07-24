package com.example.wanglixin.icerecycler;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;
import android.widget.Button;

import java.util.List;

/**
 * Created by wanglixin on 2017/7/21.
 */

public class Mys extends PreferenceActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(!hasHeaders()){
            Button button = new Button(this);
            button.setText("Exit");
            setListFooter(button);
        }
    }
    @Override
    protected boolean isValidFragment(String fragmentName) {
        System.out.println(fragmentName);
        return true;
    }

    @Override
    public void onBuildHeaders(List<Header> target) {
        loadHeadersFromResource(R.xml.preference_home, target);
    }

    public static class Prefs1Fragment extends PreferenceFragment {

    }

    public static class Pref2Fragment extends PreferenceFragment {

    }
}
