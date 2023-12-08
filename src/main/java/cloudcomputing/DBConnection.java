package cloudcomputing;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

	@WebServlet(urlPatterns = { "/count" })
public class DBConnection extends HttpServlet{
		
		  private static final long serialVersionUID = 1L;

		  @Override
		  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
		    throws ServletException, IOException {
		    BufferedWriter writer = new BufferedWriter(
		      new OutputStreamWriter(resp.getOutputStream(), "UTF-8")
		    );
		    
		 
		    try {
		      Connection connection = RDSConnectionRole.getDBConnectionUsingIamRole();
		      //verify the connection is successful
		        Statement stmt= connection.createStatement();
		        ResultSet rs=stmt.executeQuery("SELECT 'Success!' FROM DUAL;");
		        resp.setContentType("text/plain");
			      resp.setStatus(200);
			      StringBuilder report = new StringBuilder();
		        while (rs.next()) {
		        	
		        	    String id = rs.getString(1);
		        	    writer.write("Success");
		  		      writer.flush();
		           
		        }
		        resp.setContentType("text/plain");
	            resp.setStatus(200);
	            writer.write(report.toString());
	            writer.flush();	     
		     
		      writer.close();
		    } catch (ClassNotFoundException | SQLException e) {
		    	writer.write(e.toString());
	            writer.flush();
	            writer.close();
			      
		    } catch (Exception e) {
				// TODO Auto-generated catch block
		    	writer.write(e.toString());
	            writer.flush();
	            writer.close();
			}
		  }
		}
	
