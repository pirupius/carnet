package com.pirumart.carnet;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {
    private TextView mVersionTextView;
    private Toolbar toolbar;
    String theme, app_version;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        theme = getSharedPreferences(MainActivity.THEME_PREFERENCES, MODE_PRIVATE)
                .getString(MainActivity.THEME_SAVED, MainActivity.LIGHTTHEME);
        if(theme.equals(MainActivity.DARKTHEME)){
            setTheme(R.style.CustomStyle_DarkTheme);
        }
        else{
            setTheme(R.style.CustomStyle_LightTheme);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_layout);

        try {
            PackageManager manager = getApplicationContext().getPackageManager();
            PackageInfo info = manager.getPackageInfo(
                    getApplicationContext().getPackageName(), 0);
            app_version = info.versionName;
        } catch (Exception e) {
            Log.e("YourActivity", "Error getting version");
        }

        mVersionTextView = (TextView)findViewById(R.id.aboutVersionTextView);
        mVersionTextView.setText(String.format(getResources().getString(R.string.app_version), app_version));
        toolbar = (Toolbar)findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }
}
