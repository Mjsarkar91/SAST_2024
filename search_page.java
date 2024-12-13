import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class SearchServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the search query parameter from the request
        String searchQuery = request.getParameter("search");

        // Create the response content type and output
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><title>Search Results</title></head>");
        out.println("<body>");
        
        // Step 1: Reflection of user input without sanitization
        out.println("<h1>Search Results for: " + searchQuery + "</h1>");
        
        // Imagine the results of the search would be here (e.g., querying a database)
        
        out.println("</body>");
        out.println("</html>");
    }
}
