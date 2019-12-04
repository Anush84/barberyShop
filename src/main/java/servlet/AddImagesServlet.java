package servlet;

import manager.ImagesManager;
import model.Images;

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

@WebServlet(urlPatterns = "/addImages")
@MultipartConfig
public class AddImagesServlet extends HttpServlet {
    private ImagesManager imagesManager = new ImagesManager();

    private static final String IMAGE_UPLOAD_DIR = "C:\\Users\\Anush\\IdeaProjects\\barbaryShop\\web\\img";

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Part filePart = req.getPart("images");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        fileName = System.currentTimeMillis() + "_" + System.nanoTime() + fileName;
        filePart.write(IMAGE_UPLOAD_DIR + fileName);
        Images images = Images.builder()
                .imgPath(fileName)
                .build();

        imagesManager.add(images);
        List<Images> allImages=imagesManager.getAllImages();

        req.setAttribute("allImages", allImages);
        resp.sendRedirect("/adminHome");

    }
}