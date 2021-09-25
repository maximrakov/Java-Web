import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class HelloWorldServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        File file = new File(getServletContext().getRealPath("/static"), req.getRequestURI());
        if(file.isFile()){
            resp.setContentType(getServletContext().getMimeType(file.getAbsolutePath()));
            try (ServletOutputStream outputStream = resp.getOutputStream()) {
                Files.copy(file.toPath(), outputStream);
            }
        }else{
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
