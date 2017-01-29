package projeto.utils.messages;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by crist on 04/01/2017.
 */

public class MessageHandler {

    private static ArrayList<MessageReceiver> receivers;

    public MessageHandler(){
        this.receivers = new ArrayList<>();
    }

    public synchronized void addReceiver(MessageReceiver receiver){
        receivers.add(receiver);
    }

    public synchronized void sendMessage(Object target, Message message , Object data){
        for(MessageReceiver receiver : receivers){
            if(receiver.getClass().equals(target)){
                receiver.onMessage(message, data);
            }
        }
    }
}
