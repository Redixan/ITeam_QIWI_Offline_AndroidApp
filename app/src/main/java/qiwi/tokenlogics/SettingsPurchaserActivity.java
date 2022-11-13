package qiwi.tokenlogics;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;

public class SettingsPurchaserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_purchaser);
    }

    public void onClickExitApp(View view) {
        finishAffinity();
    }

    public void onClickChangeMode(View view) {
        JSONHelper.writeUserMode(this, "Merchant");
        JSONHelper.writePasswordInputTime(this, String.valueOf(System.currentTimeMillis()));
        PackageManager packageManager = getPackageManager();
        Intent intent = packageManager.getLaunchIntentForPackage(this.getPackageName());
        ComponentName componentName = intent.getComponent();
        Intent mainIntent = Intent.makeRestartActivityTask(componentName);
        startActivity(mainIntent);
        Runtime.getRuntime().exit(0);
    }
}