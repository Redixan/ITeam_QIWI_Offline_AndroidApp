package qiwi.tokenlogics;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PasswordEnterActivity extends AppCompatActivity {
    private TextView passwordDigit1, passwordDigit2, passwordDigit3, passwordDigit4;
    private TextView wrongPassword;
    private String enteredPassword = "";
    private final String requiredPassword = "1234";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_enter);
        passwordDigit1 = findViewById(R.id.passwordDigit1);
        passwordDigit2 = findViewById(R.id.passwordDigit2);
        passwordDigit3 = findViewById(R.id.passwordDigit3);
        passwordDigit4 = findViewById(R.id.passwordDigit4);
        wrongPassword = findViewById(R.id.wrongPasswordText);
        String lastPasswordInputTime = JSONHelper.readPasswordInputTime(this);
        if (lastPasswordInputTime != null &&
                System.currentTimeMillis() - Long.parseLong(lastPasswordInputTime) < 30000) {
            JSONHelper.writePasswordInputTime(this, String.valueOf(System.currentTimeMillis()));
            Intent intent = new Intent(this, PurchaserOrMerchantActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void onClickDigitButton(View view) {
        enteredPassword += ((Button) view).getText();
        setEnteredPassword();
        if (enteredPassword.length() == 4) {
            if (enteredPassword.equals(requiredPassword)) {
                JSONHelper.writePasswordInputTime(this, String.valueOf(System.currentTimeMillis()));
                Intent intent = new Intent(this, PurchaserOrMerchantActivity.class);
                startActivity(intent);
                finish();
            } else {
                wrongPassword.setText(getResources().getString(R.string.wrong_password));
            }
        }
    }

    private void setEnteredPassword() {
        setEnteredDigit(passwordDigit1, 0);
        setEnteredDigit(passwordDigit2, 1);
        setEnteredDigit(passwordDigit3, 2);
        setEnteredDigit(passwordDigit4, 3);
    }

    private void setEnteredDigit(TextView passwordDigit, int digitIndex) {
        if (enteredPassword.length() > digitIndex) {
            passwordDigit.setText(String.valueOf(enteredPassword.charAt(digitIndex)));
        } else {
            passwordDigit.setText("·");
        }
    }

    public void onClickErase(View view) {
        String s = enteredPassword;
        enteredPassword = s == null || s.length() == 0 ? "" : (s.substring(0, s.length() - 1));
        wrongPassword.setText("");
        setEnteredPassword();
    }
}