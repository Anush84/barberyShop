package servlet;

import manager.MasterManager;
import model.Master;

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

@WebServlet(urlPatterns = "/addMaster")
@MultipartConfig
public class AddMasterServlet extends HttpServlet {
    private static final String IMAGE_UPLOAD_DIR = "C:\\Users\\Anush\\IdeaProjects\\barbaryShop\\web\\img";
    private MasterManager masterManager = new MasterManager();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Part filePart = req.getPart("images");
        String fileName =Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        fileName = System.currentTimeMillis() + "_" + System.nanoTime() + fileName;
        filePart.write(IMAGE_UPLOAD_DIR + fileName);

        Master master = Master.builder()
                .name(req.getParameter("name"))
                .surname(req.getParameter("surname"))
                .imgPath(fileName)
                .profession(req.getParameter("profession"))
                .build();
        masterManager.add(master);
        List<Master> allMasters = masterManager.getAllMaster();
        req.setAttribute("allMasters", allMasters);
        resp.sendRedirect("/adminHome");


    }
}
