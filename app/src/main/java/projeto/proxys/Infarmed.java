package projeto.proxys;

/**
 * Created by crist on 18/11/2016.
 */

import android.content.Context;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.w3c.dom.Document;

import java.io.IOException;
import java.net.MalformedURLException;

import projeto.agents.WebAgent;
import projeto.models.Medicine;
import projeto.utils.builders.MedicineBuilder;
import projeto.utils.net.CertificatesFactory;
import projeto.utils.net.HTTPConnection;
import projeto.utils.net.HTTPSConnection;
import projeto.utils.net.SessionHandler;
import projeto.utils.net.WebConnection;

public class Infarmed extends WebAgent{

    private String barcode = null;
    private boolean existsOnline = false;
    private boolean existsLocal = false;

    //Links para ligação ao WebService
    private final String webServerIP = "http://192.168.130.164:8080";
    private final String webServerURL = webServerIP + "/projecto/infarmed/med/barcode/";

    //Ligações
    private HTTPConnection connection = null;

    //Documentos
    private StringBuilder document = null;

    public Infarmed(String barcode){
        this.barcode = barcode;
    }

    @Override
    public void setBarcode(String barcode){
        this.barcode = barcode;
    }

    @Override
    public boolean exists() {
        if(existsLocal())
            return true;
        else if(existsOnline())
            return true;

        return false;
    }

    private boolean existsLocal(){
        return false;
    }

    private boolean existsOnline(){
        boolean exists = false;
        try {
            connection = new HTTPConnection(webServerURL + barcode);
            connection.connect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        int response = connection.getResponseCode();
        if(response == 200) {
            document = connection.getHTML();
            exists = true;
            this.existsOnline = true;
        }
        connection.disconnect();

        return exists;
    }

    @Override
    public Medicine getMedicine() {
        if(!(existsLocal || existsOnline)){
            if(!(existsLocal() || existsOnline()))
                return null;
        }

        Medicine medicine = null;
        if(existsLocal)
            medicine = getFromLocal();
        else if(existsOnline)
            medicine = getFromOnline();

        return medicine;
    }

    private Medicine getFromOnline(){
        if(document != null)
            return MedicineBuilder.getFromText(barcode, document);
        return null;
    }

    private Medicine getFromLocal(){
        return null;
    }

    private boolean isValidId(String id){
        return id.matches("^-?\\d+$");
    }

    /*

    //Urls para interação com o site da infarmed
    private final static String INFAMED_BASE_URL = "https://app10.infarmed.pt";
    private final static String INFARMED_URL_LOGIN = "https://app10.infarmed.pt/infomed/login.php"; //Login
    private final static String INFAMED_URL_LISTA = "https://app10.infarmed.pt/infomed/lista.php"; //Pagina para submissão de código

    private final String USERNAME = "u_nome=infomed&"; //Username em 14/11/2016
    private final String PASSWORD = "u_pass=infomed"; //Password em 14/11/2016
    private final String INFAMED_PARAM_LISTA_1 = "dci=&estado_aim=&nome_comer=&disp=&data_fim=&data_inicio=&dosagem=&cnpem=&chnm=&forma_farmac=&atc=&disp=&pesquisa_titular=&numero_reg=";
    private final String INFAMED_RARAM_LISTA_2 = "&grupo_produto=&pesquisa_cft=&lim_sup=15&Submit_.x=99&Submit_.y=23&Submit_=Pesquisar!"; //Estas duas strings contem os parametros a submeter no formulario final
    private final String INFAMED_MED_ID_PARAM = "med_id=";
    private final String INFAMED_DCI_PARAM = "&dci";
    private final String INFAMED_RCM_LINK_PART_1 = "/infomed/download_ficheiro.php?med_id=";
    private final String INFAMED_RCM_LINK_PART_2 = "&tipo_doc=rcm";
    private final String INFAMED_FI_LINK_PART_1 = "/infomed/download_ficheiro.php?med_id=";
    private final String INFAMED_FI_LINK_PART_2 = "&tipo_doc=fi";

    private final static String CERTIFICATAE_NAME = "infarmed.pt.crt";*/

}
