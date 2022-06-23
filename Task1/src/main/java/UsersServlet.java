import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession; 

@WebServlet("/UsersServlet")
public class UsersServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	/*private static Logger logger = Logger.getLogger(UsersServlet.class.getName());
	static {
	try {
	SimpleLayout layout = new SimpleLayout();
	ConsoleAppender appender =new ConsoleAppender(layout);
	logger.addAppender(appender);
	logger.setLevel(Level.DEBUG);
	logger.info("UsersServlet::log4jsetup is ready");}
	
	 catch(Exception e) {			
		e.printStackTrace();
		logger.fatal("UsersServlet::problem while setting up log4j");
	}}*/
	
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException,ServletException {
		
		//logger.debug("UsersServlet::start of doPost method(-)");
		
		res.setContentType("application/json");
		res.setCharacterEncoding("UTF-8");
		
		String userName=req.getParameter("userName");
		String password= req.getParameter("password");
	
		
		PrintWriter out= res.getWriter();
		Users u= new Users();
		Connection c=null;
	    
		
		 try {
			 if(u.validate(userName, password)) {
				Class.forName("com.mysql.jdbc.Driver");
				
				//logger.debug("UsersServlet::JDBC driver class is loaded");
				
			 c=DriverManager.getConnection("jdbc:mysql://dev-ws.bajajfinservsecurities.in:8444/SESSION_DATABASE", "platformwrite", "bfslwrite");
			 
			 //logger.info("UsersServlet::JDBC connection is established");
			 out.println("logged in successfully");
			
			 HttpSession s=req.getSession();  
		     s.setAttribute("userName",userName);
			
		 }else {out.println("please enter the currect username and password");
			 }}
		 catch(Exception e) {			
				e.printStackTrace();
				//logger.fatal("UserServlet::unknown db problem"+e.getMessage());
			}
			 finally{
				 //logger.debug("UserServlet::clossing JDBC objects");
				
				 try {
					 if( c!= null)
					 c.close();
					// logger.debug("UsersServlet::connection object is closed");
				 }
				 catch(SQLException se) {
					 se.printStackTrace();
					 //logger.error("UsersServlet:: failed to close connection object"+se.getMessage());					 
				 }
				 }
			
			// logger.debug("UsersServlet::end of the doPost method");
			 	}
			 	}
