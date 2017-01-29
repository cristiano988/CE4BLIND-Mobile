package projeto.utils.net;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by crist on 21/11/2016.
 */

public class HTTPConnection extends AbstractConnection{

    private HttpURLConnection connection = null;

    public HTTPConnection(String url) throws MalformedURLException {
        super(url);
    }

    @Override
    public void connect() {
        try {
            connection = (HttpURLConnection)url.openConnection();
            connection.setDoInput(true);
            connection.setRequestMethod("GET");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void disconnect() {
        if(connection == null)
            connection.disconnect();
    }

    @Override
    public void sendRequest(String type, String parameters, SessionHandler session) {

    }

    @Override
    public StringBuilder getHTML() {
        if(connection == null)
            return null;

        StringBuilder buffer = new StringBuilder();
        InputStream in = null;
        BufferedReader br = null;
        try {
            in = new BufferedInputStream(connection.getInputStream());
            br = new BufferedReader(new InputStreamReader(in,"UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String line;
        try {
            while((line = br.readLine()) != null)
                buffer.append(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                br.close();
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return buffer;
    }

    @Override
    public Map<String, List<String>> getHeader() {
        return connection != null ? connection.getHeaderFields() : null;
    }

    @Override
    public int getResponseCode() {
        int response = 0;
        try {
            response = connection.getResponseCode();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }
}
