package projeto.models;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.io.Serializable;

/**
 * Created by crist on 18/11/2016.
 */

public class Medicine implements Serializable{

    private String barcode = null;
    private String id = null;
    private FolhetoInformativo fi = null;
    private RCM rcm = null;

    public Medicine(String barcode){
        this.barcode = barcode;
    }

    public Medicine(String barcode, @NonNull FolhetoInformativo fi, @Nullable RCM rcm){
        this.barcode = barcode;
        this.fi = fi;
        this.rcm = rcm;
    }

    public FolhetoInformativo getFi() {
        return fi;
    }

    public void setFi(FolhetoInformativo fi) {
        this.fi = fi;
    }

    public RCM getRcm() {
        return rcm;
    }

    public void setRcm(RCM rcm) {
        this.rcm = rcm;
    }
}
