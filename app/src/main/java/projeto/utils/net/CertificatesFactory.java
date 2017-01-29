package projeto.utils.net;

import android.app.Activity;
import android.content.Context;
import android.os.Environment;
import android.os.SystemClock;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

/**
 * Created by crist on 14/12/2016.
 *
 * Classe Helper para construir os certificados para fazer ligações HTTPS
 */

public class CertificatesFactory {

    private final static String DEFAULT_INSTANCE = "X.509";
    private final static String DEFAULT_SSLCONTEXT_INSTANCE = "TLS";
    public final static String X_509 = "X.509";

    private String certificate_name = null;
    private String factory_instance = null;
    private String sslcontext_instance = null;


    private CertificateFactory cf = null;
    private Certificate ca = null;
    private KeyStore keyStore = null;
    private String tmfAlgorithm = null;
    private TrustManagerFactory tmf = null;
    private SSLContext context = null;
    private Context activity = null;

    public CertificatesFactory(String certificate_name, Context activity) throws Exception {
        this(certificate_name, CertificatesFactory.DEFAULT_INSTANCE,activity);
        if(certificate_name == null || activity == null)
            throw new Exception("Error on parameters");

    }

    public CertificatesFactory(String certificate_name, String factory_instance, Context activity){
        this(certificate_name, factory_instance, DEFAULT_SSLCONTEXT_INSTANCE,activity);
    }

    public CertificatesFactory(String certificate_name,String factory_instance, String sslcontext_instance, Context activity){
        this.certificate_name = certificate_name;
        this.factory_instance = factory_instance;
        this.sslcontext_instance = sslcontext_instance;
        this.activity = activity;
    }

    private boolean startSSLContext(){
        if(activity == null)
            return false;
        try {
            cf = CertificateFactory.getInstance(factory_instance);
        } catch (CertificateException e) {
            e.printStackTrace();
        }

        InputStream caInput = null;
        if((caInput = activity.getClassLoader().getResourceAsStream("assets/certificates/" + certificate_name)) == null)
            return false;

        try {
            ca = cf.generateCertificate(caInput);
            caInput.close();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String keyStoreType = KeyStore.getDefaultType();

        try {
            keyStore = KeyStore.getInstance(keyStoreType);
            keyStore.load(null,null);
            keyStore.setCertificateEntry("ca", ca);
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();

        try {
            tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
            tmf.init(keyStore);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }

        try {
            context = SSLContext.getInstance(sslcontext_instance);
            context.init(null, tmf.getTrustManagers(), null);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
        return true;
    }

    public SSLSocketFactory getSSLSocketFactory(){
        if(context != null || startSSLContext())
            return context.getSocketFactory();
        return null;
    }
}
