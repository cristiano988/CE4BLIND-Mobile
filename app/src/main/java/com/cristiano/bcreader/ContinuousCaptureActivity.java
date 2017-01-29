package com.cristiano.bcreader;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import com.journeyapps.barcodescanner.CompoundBarcodeView;


public class ContinuousCaptureActivity extends Activity {

    private static final String TAG = ContinuousCaptureActivity.class.getSimpleName();
    protected CompoundBarcodeView barcodeView;

    protected String bar_code_text;
    protected Bitmap bitmap_preview;

    protected String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.continuous_scan);
        path = getFilesDir() + "/images/";

        barcodeView = (CompoundBarcodeView) findViewById(R.id.barcode_scanner);

    }

    @Override
    protected void onResume() {
        super.onResume();
        barcodeView.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        barcodeView.pause();
    }

    public void triggerScan(View view) {
        //barcodeView.decodeSingle(callback);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return barcodeView.onKeyDown(keyCode, event) || super.onKeyDown(keyCode, event);
    }
}
