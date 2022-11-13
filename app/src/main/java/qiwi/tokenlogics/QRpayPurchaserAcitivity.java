package qiwi.tokenlogics;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.WHITE;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.util.EnumMap;
import java.util.Map;

public class QRpayPurchaserAcitivity extends AppCompatActivity {

    private ImageView QRcodeImage;
    public String tokenID = "tokenID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        QRcodeImage = (ImageView) findViewById(R.id.QRCodeImage);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrpay_purchaser);
        try {
            createQRCode();
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }

    public void onClickReady(View view) {
        //finish();
        MultiFormatWriter writer = new MultiFormatWriter();
        try {
            BitMatrix qrCode = writer.encode(tokenID, BarcodeFormat.QR_CODE, 270, 270);
            BarcodeEncoder encoder = new BarcodeEncoder();
            Bitmap bitmap = encoder.createBitmap(qrCode);
            QRcodeImage.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }

    private void createQRCode() throws WriterException {
        Bitmap barcode_bitmap = encodeAsBitmap(tokenID, BarcodeFormat.QR_CODE, 270, 270);
        QRcodeImage.setImageBitmap(barcode_bitmap);
    }

    private static Bitmap encodeAsBitmap(String contents, BarcodeFormat format, int img_width, int img_height) throws WriterException {

        if (contents == null) {
            return null;
        }
        Map<EncodeHintType, Object> hints = null;
        String encoding = guessAppropriateEncoding(contents);
        if (encoding != null) {
            hints = new EnumMap<EncodeHintType, Object>(EncodeHintType.class);
            hints.put(EncodeHintType.CHARACTER_SET, encoding);
        }
        MultiFormatWriter writer = new MultiFormatWriter();
        BitMatrix result;
        try {
            result = writer.encode(contents, format, img_width, img_height, hints);
        } catch (IllegalArgumentException iae) {
            // Unsupported format
            return null;
        }
        int width = result.getWidth();
        int height = result.getHeight();
        int[] pixels = new int[width * height];
        for (int y = 0; y < height; y++) {
            int offset = y * width;
            for (int x = 0; x < width; x++) {
                pixels[offset + x] = result.get(x, y) ? BLACK : WHITE;
            }
        }

        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return bitmap;
    }

    private static String guessAppropriateEncoding(CharSequence contents) {
        // Very crude at the moment
        for (int i = 0; i < contents.length(); i++) {
            if (contents.charAt(i) > 0xFF) {
                return "UTF-8";
            }
        }
        return null;
    }
}