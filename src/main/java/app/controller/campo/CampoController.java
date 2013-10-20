package app.controller.campo;

import app.model.Campo;
import app.model.Local;
import app.zelper.Constants;
import app.zelper.Helper;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/adm/campo")
public class CampoController extends HttpServlet {

    private CampoService service;

    public CampoController() {
        service = new CampoService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int action = Helper.toInteger(request.getParameter("action"), Constants.ACTION_LIST);

        switch (action) {
            case Constants.ACTION_CREATE:
                this.create(request, response);
                break;

            case Constants.ACTION_UPDATE:
                this.update(request, response);
                break;

            case Constants.ACTION_DELETE:
                this.delete(request, response);
                break;

            default:
                this.list(request, response);
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Campo campo = new Campo();
        campo.setDescripcion(request.getParameter("descripcion"));
        campo.setCostoHora(Double.parseDouble(request.getParameter("costo")));

        Local local = new Local();
        local.setId(Long.parseLong(request.getParameter("local")));

        campo.setLocal(local);

        Long id = Long.parseLong(request.getParameter("id"));
        if (id > 0) {
            campo.setId(id);
            service.update(campo);
        } else {
            service.save(campo);
        }

        response.sendRedirect(request.getContextPath() + "/adm/campo");
    }

    protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Campo> campos = service.list();
        request.setAttribute("campos", campos);
        
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/adm/campo/campo.jsp");
        rd.forward(request, response);


    }

    protected void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Local> locales = service.listLocal();
        request.setAttribute("locales", locales);
        
        request.setAttribute("campo", new Campo());

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/adm/campo/campoForm.jsp");
        rd.forward(request, response);

    }

    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Local> locales = service.listLocal();
        request.setAttribute("locales", locales);
        
        
        Campo campo = new Campo();
        campo.setId(Long.parseLong(request.getParameter("id")));
        request.setAttribute("campo", service.get(campo));

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/adm/campo/campoForm.jsp");
        rd.forward(request, response);

    }

    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Campo campo = new Campo();
        campo.setId(Long.parseLong(request.getParameter("id")));
        service.delete(campo);

        response.sendRedirect(request.getContextPath() + "/adm/campo");

    }
}
