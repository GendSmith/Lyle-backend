import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class user extends HttpServlet{
    private int id = 0;

    @Override
    public void init() throws ServletException{
        id = 100;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException,IOException{
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        out.println("<h1>" + id + "</h1>");
    }

    @Override
    public void destroy(){
        super.destroy();
    }
}
