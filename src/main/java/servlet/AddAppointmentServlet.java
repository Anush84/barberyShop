package servlet;

import manager.AppointementManager;
import manager.MasterManager;
import manager.ServiceManager;
import model.Appointement;
import model.Master;
import model.Service;

import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet (urlPatterns = "/addAppointement")
public class AddAppointmentServlet extends HttpServlet {
    private AppointementManager appointementManager = new AppointementManager();
    private ServiceManager serviceManager = new ServiceManager();
    private MasterManager masterManager = new MasterManager();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String service_id = req.getParameter("service_id");
        String master_id = req.getParameter("master_id");

        int serviceId = Integer.parseInt(service_id);
        int masterId = Integer.parseInt(master_id);

        Service service = serviceManager.getServiceById(serviceId);
        Master master = masterManager.getMasterById(masterId);


     Appointement appointement = Appointement.builder()
                .name(req.getParameter("name"))
                .time(req.getParameter("date") + " " + req.getParameter("time"))
                .service(service)
                .master(master)
                .phoneNumber(req.getParameter("phoneNumber"))
                .email(req.getParameter("email"))
                .build();
        appointementManager.add(appointement);
        req.getRequestDispatcher("home.jsp").forward(req, resp);

    }
}
