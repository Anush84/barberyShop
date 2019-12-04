package servlet;

import manager.ImagesManager;
import manager.MasterManager;
import manager.ServiceManager;
import model.Images;
import model.Master;
import model.Service;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/home")

public class HomeServlet extends HttpServlet {

    private ServiceManager serviceManager = new ServiceManager();
    private MasterManager masterManager = new MasterManager();
    private ImagesManager imagesManager = new ImagesManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        List<Service> allService = serviceManager.getAllService();
        List<Master> allMaster = masterManager.getAllMaster();
        List<Images> allImages = imagesManager.getAllImages();
        req.getSession().setAttribute("allService", allService);
        req.getSession().setAttribute("allMaster", allMaster);
        req.getSession().setAttribute("allImages", allImages);
        req.getRequestDispatcher("home.jsp").forward(req, resp);

    }
}