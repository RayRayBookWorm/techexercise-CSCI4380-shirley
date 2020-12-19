

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SimpleFormUpdate
 */
@WebServlet("/SimpleFormUpdate")
public class SimpleFormUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SimpleFormUpdate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
	      String completion = request.getParameter("completion");
	      //String phone = request.getParameter("phone");
	      //String address = request.getParameter("address");

	      Connection connection = null;
	      String updateSql = " UPDATE toDoList SET COMPLETED = 'request' WHERE TODOACTION = 'action'";

	      try {
	         DBConnection.getDBConnection(getServletContext());
	         connection = DBConnection.connection;
	         PreparedStatement preparedStmt = connection.prepareStatement(updateSql);
	         preparedStmt.setString(1, action);
	         preparedStmt.setString(2, completion);
	         //preparedStmt.setString(3, phone);
	         //preparedStmt.setNString(4, address);
	         preparedStmt.execute();
	         connection.close();
	      } catch (Exception e) {
	         e.printStackTrace();
	      }

	      // Set response content type
	      response.setContentType("text/html");
	      PrintWriter out = response.getWriter();
	      String title = "Updated Data to techExDB";
	      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
	      out.println(docType + //
	            "<html>\n" + //
	            "<head><title>" + title + "</title></head>\n" + //
	            "<body bgcolor=\"#f0f0f0\">\n" + //
	            "<h2 align=\"center\">" + title + "</h2>\n" + //
	            "<ul>\n" + //

	            "  <li><b>To Do</b>: " + action + " " + //
	            "  <li><b>Completion</b>: " + completion + "\n" + //

	            "</ul>\n");

	      out.println("<a href=search.html>Search Data</a> <br>");
	      out.println("<a href=Todo.html>Insert Data</a> <br>");
	      out.println("<a href=update.html>Update Data</a> <br>");
	      out.println("</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
