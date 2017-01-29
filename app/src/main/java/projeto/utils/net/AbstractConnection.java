package projeto.utils.net;

import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by crist on 15/12/2016.
 */

public abstract class AbstractConnection implements WebConnection{

    protected URL url = null;
    protected String _url = null;
    protected SessionHandler session = null;

    public void setURL(String url){
        this._url = url;
    }

    public void setURL(URL url){
        this.url = url;
    }

    public String getURL(){
        return this._url;
    }

    public AbstractConnection(String url) throws MalformedURLException {
        this(new URL(url));
    }

    public AbstractConnection(URL url){
        _url = url.toExternalForm();
        this.url = url;
    }

}
