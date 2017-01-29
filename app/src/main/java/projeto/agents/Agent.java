package projeto.agents;

import projeto.models.Medicine;

/**
 * Created by crist on 18/11/2016.
 */

public interface Agent {

    //metodo para pesquisar o produto desejado
    public boolean exists();
    //metodo para uma vez que temos os dados, construir e devolver o medicamento
    public Medicine getMedicine();

    public void setBarcode(String barcode);
}
