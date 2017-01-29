package projeto.proxys;

import com.cristiano.bcreader.BaseScan;

import projeto.models.Medicine;
import projeto.utils.messages.Message;
import projeto.utils.messages.MessageHandler;
import projeto.utils.messages.MessageReceiver;

/**
 * Created by crist on 04/01/2017.
 */

public class InfarmedHandler implements Runnable{

    MessageHandler handler = null;
    String barcode = null;
    Infarmed infarmed = null;
    Medicine medicine;

    public InfarmedHandler(String barcode, MessageHandler handler){
        this.barcode = barcode;
        this.handler = handler;
        infarmed = new Infarmed(barcode);
    }

    public InfarmedHandler(MessageHandler handler){
        this.handler = handler;
        this.barcode = null;
        infarmed = new Infarmed(null);
    }

    @Override
    public void run() {
        if(infarmed != null && infarmed.exists())
            if((medicine = infarmed.getMedicine()) != null)
                handler.sendMessage(BaseScan.class,Message.MEDICINE_RECEIVED,medicine);
    }
}
