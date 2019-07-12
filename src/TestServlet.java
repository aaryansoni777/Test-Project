import org.json.JSONObject;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.zip.GZIPInputStream;

@WebServlet(name = "TestServlet")
public class TestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String query = req.getParameter("query");
        req.setAttribute("query", query);

        if(query !=  null){
            HttpURLConnection conn = (HttpURLConnection) (new URL("http://api.stackexchange.com/2.2/search?order=desc&sort=activity&site=stackoverflow&intitle=" + URLEncoder.encode(query, "UTF-8"))).openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(new GZIPInputStream(conn.getInputStream())));

            String inputLine;
            StringBuffer rvalue = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                rvalue.append(inputLine);
            }
            in.close();

            JSONObject o = new JSONObject(rvalue.toString());

            req.setAttribute("data", o);
        }

        RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
        rd.forward(req, resp);
    }
}
