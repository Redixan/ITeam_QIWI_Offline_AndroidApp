package qiwi.tokenlogics;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class MerchantActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchant);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent(this, SettingsMerchantActivity.class);
        startActivity(intent);
        return true;
    }

    public void onClickPayByNFC(View view) {
        Intent intent = new Intent(this, NFCpayPurchaserActivity.class);
        startActivity(intent);
    }

    public void onClickPayByQR(View view) {
        Intent intent = new Intent(this, QRpayPurchaserAcitivity.class);
        startActivity(intent);
    }
}