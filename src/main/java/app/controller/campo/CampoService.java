package app.controller.campo;

import app.dao.CampoDAO;
import app.dao.LocalDAO;
import app.model.Campo;
import app.model.Local;
import java.util.List;

public class CampoService {
    
    private CampoDAO campoDAO = null;
    
    public CampoService(){
        campoDAO = new CampoDAO();
    }
    
    public List<Campo> list() {
        return campoDAO.list();
    }

    public Campo get(Campo local) {
        return campoDAO.get(local);
    }

    public Campo save(Campo local) {
        return campoDAO.save(local);
    }
    
    public Campo update(Campo local) {
        return campoDAO.update(local);
    }
    
    public void delete(Campo local) {
        campoDAO.delete(local);
    }
    
    public List<Local> listLocal(){
        LocalDAO localDAO = new LocalDAO();
        return localDAO.list();
    }
}
