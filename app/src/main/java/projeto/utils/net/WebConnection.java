package projeto.utils.net;

import java.net.HttpURLConnection;
import java.util.List;
import java.util.Map;

/**
 * Created by crist on 15/12/2016.
 */

public interface WebConnection {

    public void connect();
    public void disconnect();
    public void sendRequest(String type, String parameters, SessionHandler session);
    public StringBuilder getHTML();
    public Map<String, List<String>> getHeader();
    public int getResponseCode();
}
