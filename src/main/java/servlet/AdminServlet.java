package servlet;

import manager.AppointementManager;
import manager.MasterManager;
import manager.ServiceManager;
import model.Admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebServlet(urlPatterns = "/adminHome")

public class AdminServlet extends HomeServlet {
    private ServiceManager serviceManager = new ServiceManager();
    private MasterManager masterManager = new MasterManager();
    private AppointementManager appointmentManager = new AppointementManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin == null ) {
            resp.sendRedirect("home.jsp");
        } else {
            req.setAttribute("master", masterManager.getAllMaster());
            req.setAttribute("service", serviceManager.getAllService());
            req.setAttribute("allAppointment",appointmentManager.getAllAppointement());
            req.getRequestDispatcher("adminHome.jsp").forward(req, resp);

        }
    }

}
