package qiwi.tokenlogics;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PurchaserOrMerchantActivity extends AppCompatActivity {
    private Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchaser_or_merchant);
        String userMode = JSONHelper.readUserMode(this);
        if (userMode != null) {
            switch (userMode) {
                case "Purchaser":
                    startPurchaserActivity();
                    break;
                case "Merchant":
                    startMerchantActivity();
                    break;
                default:
                    break;
            }
        }
    }

    public void onClickIAmPurchaser(View view) {
        JSONHelper.writeUserMode(this, "Purchaser");
        startPurchaserActivity();
    }

    public void onClickIAmMerchant(View view) {
        JSONHelper.writeUserMode(this, "Merchant");
        startMerchantActivity();
    }

    private void startPurchaserActivity() {
        Intent intent = new Intent(this, PurchaserActivity.class);
        startActivity(intent);
        finish();
    }

    private void startMerchantActivity() {
        Intent intent = new Intent(this, MerchantActivity.class);
        startActivity(intent);
        finish();
    }
}