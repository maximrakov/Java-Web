import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

public class SessionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if(session.getAttribute("count") == null){
            session.setAttribute("count", 1);
        }else{
            int curCountOfSession = (int) session.getAttribute("count");
            session.setAttribute("count", curCountOfSession + 1);
        }
        PrintWriter wrt = resp.getWriter();
        wrt.print((int)session.getAttribute("count"));
    }
}
