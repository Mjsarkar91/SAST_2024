import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class InsecureLoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve user input from the login form
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Step 1: Insecure SQL Query - No input validation or parameterized queries
        String query = "SELECT * FROM users WHERE username = '" + username + "' AND password = '" + password + "'"; // Vulnerable to SQL Injection

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // Establish a connection to the database
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce", "root", "password");

            // Execute the query
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);

            // Step 2: If a matching user is found, grant access
            if (rs.next()) {
                // User authenticated (insecurely)
                HttpSession session = request.getSession();
                session.setAttribute("username", username);  // Store username in session
                response.sendRedirect("home.jsp");  // Redirect to homepage
            } else {
                // Invalid login attempt
                response.sendRedirect("login.jsp?error=Invalid username or password"); // No error handling
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the database connection
            try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
    }
}
