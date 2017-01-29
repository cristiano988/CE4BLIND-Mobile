package com.cristiano.bcreader;

import android.os.Bundle;
import com.google.zxing.ResultPoint;
import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;
import java.util.List;


/**
 * Created by Cristiano on 06/08/2015.
 */
public class AddBarCode extends ContinuousCaptureActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        //barcodeView.decodeContinuous(callback);
        barcodeView.decodeSingle(callback);
    }

    private BarcodeCallback callback = new BarcodeCallback() {
        @Override
        public void barcodeResult(BarcodeResult result) {

            bar_code_text = result.getText();
            bitmap_preview = result.getBitmap();

            if ((bar_code_text != null) && (bitmap_preview != null)) {
                barcodeView.setStatusText(bar_code_text);
            }
        }

        @Override
        public void possibleResultPoints(List<ResultPoint> resultPoints) {
        }
    };
}
