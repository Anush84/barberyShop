package servlet;

import manager.ServiceManager;
import model.Service;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

@WebServlet(urlPatterns = "/addService")
@MultipartConfig
public class AddServiceServlet extends HttpServlet {
    private static final String IMAGE_UPLOAD_DIR = "C:\\Users\\Anush\\IdeaProjects\\barbaryShop\\web\\img ";
    private ServiceManager serviceManager = new ServiceManager();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Part filePart = req.getPart("image");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        fileName = System.currentTimeMillis() + "_" + System.nanoTime() + fileName;
        filePart.write(IMAGE_UPLOAD_DIR + fileName);

        Service service = Service.builder()
                .name(req.getParameter("name"))
                .description(req.getParameter("description"))
                .imgPath(fileName)
                .price(Double.parseDouble(req.getParameter("price")))
                .build();
        serviceManager.add(service);
        List<Service> allServices = serviceManager.getAllService();
        req.setAttribute("allServices", allServices);
        resp.sendRedirect("/adminHome");
    }
}
