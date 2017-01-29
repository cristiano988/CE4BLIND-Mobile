package com.cristiano.bcreader;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Camera;
import android.os.Bundle;

import com.google.zxing.ResultPoint;
import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import projeto.models.Medicine;
import projeto.proxys.InfarmedHandler;
import projeto.utils.messages.Message;
import projeto.utils.messages.MessageHandler;
import projeto.utils.messages.MessageReceiver;

/**
 * Created by crist on 25/09/2016.
 */

public class BaseScan extends ContinuousCaptureActivity implements MessageReceiver{

    private static ArrayList<Thread> agents;
    private MessageHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        barcodeView.decodeSingle(callback);

        handler = new MessageHandler();
        handler.addReceiver(this);
        agents = new ArrayList<>();
    }

    private BarcodeCallback callback = new BarcodeCallback() {
        @Override
        public void barcodeResult(BarcodeResult result) {

            bar_code_text = result.getText();
            bitmap_preview = result.getBitmap();

            if ((bar_code_text != null) && (bitmap_preview != null)) {
                //barcodeView.setStatusText(bar_code_text);
                /*Intent intent = new Intent();
                intent.putExtra("result", bar_code_text);
                setResult(Activity.RESULT_OK, intent);
                finish();*/

                agents.add(new Thread(new InfarmedHandler(bar_code_text,handler)));

                for(Thread agent : agents){
                    if(!agent.isAlive())
                        agent.start();
                }
            }
            else
                barcodeView.decodeSingle(this);
        }

        @Override
        public void possibleResultPoints(List<ResultPoint> resultPoints) {
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        barcodeView.decodeSingle(this.callback);
    }

    @Override
    public void onMessage(Message message, Object data) {

        if(message == Message.MEDICINE_RECEIVED){

            Intent intent = new Intent(this,MedicineActivity.class);
            intent.putExtra("medicine", (Medicine)data);
            //setResult(MainActivity.MEDICINE_RECEIVED, intent);
            startActivityForResult(intent,1);
            //finish();
        }else{
            barcodeView.decodeSingle(this.callback);
        }
    }
}
