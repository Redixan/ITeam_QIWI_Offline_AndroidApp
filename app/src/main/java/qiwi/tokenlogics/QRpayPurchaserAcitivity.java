package qiwi.tokenlogics;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class QRpayPurchaserAcitivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrpay_purchaser);
    }

    public void onClickReady(View view) {
        finish();
    }
}