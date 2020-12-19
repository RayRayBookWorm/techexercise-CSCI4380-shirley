

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Search
 */
@WebServlet("/Search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Search() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
        search(keyword, response);
     }

     void search(String keyword, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String title = "Database Result";
        String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + //
              "transitional//en\">\n"; //
        out.println(docType + //
              "<html>\n" + //
              "<head><title>" + title + "</title></head>\n" + //
              "<body bgcolor=\"#f0f0f0\">\n" + //
              "<h1 align=\"center\">" + title + "</h1>\n");

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
           DBConnection.getDBConnection(getServletContext());
           connection = DBConnection.connection;

           String selectSQL = "SELECT * FROM toDoList";
           preparedStatement = connection.prepareStatement(selectSQL);
           ResultSet rs = preparedStatement.executeQuery();

           while (rs.next()) {
              //int id = rs.getInt("id");
              String action = rs.getString("TODOACTION").trim();
              String completion = rs.getString("COMPLETED").trim();
              //String phone = rs.getString("phone").trim();

              out.println("To Do: " + action + ", ");
              out.println("Completed: " + completion + "<br>");
              //   out.println("Email: " + email + ", ");
                // out.println("Phone: " + phone + "<br>");
              
           }
           out.println("<a href=search.html>Search Data</a> <br>");
           out.println("<a href=Todo.html>Insert Data</a> <br>");
           out.println("<a href=update.html>Update Data</a> <br>");
           out.println("</body></html>");
           rs.close();
           preparedStatement.close();
           connection.close();
        } catch (SQLException se) {
           se.printStackTrace();
        } catch (Exception e) {
           e.printStackTrace();
        } finally {
           try {
              if (preparedStatement != null)
                 preparedStatement.close();
           } catch (SQLException se2) {
           }
           try {
              if (connection != null)
                 connection.close();
           } catch (SQLException se) {
              se.printStackTrace();
           }
        }
     }

     protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
     }

  }


