package projeto.utils.builders;

import projeto.models.Medicine;

/**
 * Created by crist on 04/01/2017.
 */

public class MedicineBuilder {

    public static Medicine getFromText(String barcode, StringBuilder document){
        return new Medicine(barcode, new FIBuilder().getFromText(document), null);
    }
}
