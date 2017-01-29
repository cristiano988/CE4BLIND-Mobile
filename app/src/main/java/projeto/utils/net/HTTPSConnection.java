package projeto.utils.net;


import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by crist on 21/11/2016.
 */

public class HTTPSConnection extends AbstractConnection{

    private HttpsURLConnection connection = null;
    private CertificatesFactory factory = null;

    public HTTPSConnection(String url, CertificatesFactory factory) throws MalformedURLException {
        super(url);
        this.factory = factory;
    }

    @Override
    public void connect(){
        if(connection == null)
            try {
                connection = (HttpsURLConnection)url.openConnection();
                connection.setSSLSocketFactory(factory.getSSLSocketFactory());
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    @Override
    public void disconnect(){
        if(connection != null)
            connection.disconnect();
    }

    @Override
    public StringBuilder getHTML(){
        if(connection == null)
            return null;

        StringBuilder buffer = new StringBuilder();
        int i;
        InputStream in = null;
        try {
            in = new BufferedInputStream(connection.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            while((i = in.read()) >= 0)
                buffer.append((char)i);
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return buffer;
    }

    @Override
    public Map<String, List<String>> getHeader(){
        return connection != null ? connection.getHeaderFields() : null;
    }

    @Override
    public int getResponseCode() {
        return 0;
    }

    @Override
    public void sendRequest(String type, String parameters, SessionHandler session)
    {
        if(connection == null)
            return;

        try {
            if(type.equalsIgnoreCase("POST"))
                connection.setRequestMethod("POST");
            else
                connection.setRequestMethod("GET");
        } catch (ProtocolException e) {
            e.printStackTrace();
        }

        if(session != null)
            connection.setRequestProperty("Cookie", session.build());

        if(parameters == null)
            return;

        OutputStream out = null;
        try {
            out = new DataOutputStream(connection.getOutputStream());
            out.write(parameters.getBytes());
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
