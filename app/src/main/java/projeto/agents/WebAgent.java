package projeto.agents;

import projeto.models.Medicine;
import projeto.utils.net.WebConnection;

/**
 * Created by crist on 13/12/2016.
 */

public abstract class WebAgent implements Agent{

    protected WebConnection connection;

    private String _url = null;

}
