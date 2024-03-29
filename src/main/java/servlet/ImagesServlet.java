package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

@WebServlet(urlPatterns = "/getImages")

public class ImagesServlet extends HomeServlet {
    private static final String IMAGE_UPLOAD_DIR = "C:\\Users\\Anush\\IdeaProjects\\barbaryShop\\web\\img";

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String imgName = req.getParameter("imgName");
        resp.setContentType("images");
        FileInputStream fin = new FileInputStream(IMAGE_UPLOAD_DIR + imgName);
            BufferedInputStream bin = new BufferedInputStream(fin);
            BufferedOutputStream bout = new BufferedOutputStream(resp.getOutputStream());
            int ch = 0;
            while ((ch = bin.read()) != -1) {
                bout.write(ch);
            }

    }
}
