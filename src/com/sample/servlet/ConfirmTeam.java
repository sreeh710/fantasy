package com.sample.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ConfirmTeam
 */
@WebServlet("/ConfirmTeam")
public class ConfirmTeam extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfirmTeam() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
        try{  
    		Class.forName("com.mysql.jdbc.Driver");  
    		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test",
                    "root", ""); 
    		String user = null;
    		HttpSession session = request.getSession();
    	      if(session.getAttribute("user") == null){
    	          response.sendRedirect("login.html");
    	      }else user = (String) session.getAttribute("user");
    	      String sql = "select count(*) from player where team_id = (select team_id from team where username ='"+user+"')";
    	    PreparedStatement ps1=conn.prepareStatement(sql);
    	    java.sql.ResultSet rs = ps1.executeQuery(sql);
    	    int count1;
    	    while(rs.next())
    	    {
    	    	count1=rs.getInt(1);
    	    	if(count1==7)
    	    	{
    	    		String sql3 = "update team set confirmed = 1 where username = '"+user+"'";
    	    		PreparedStatement ps2=conn.prepareStatement(sql3);
    	    	    int k = ps1.executeUpdate(sql3);
    	    	    if(k>0)
    	    	    {
    	    	    	String message4 = "You have successfuly registered your team";
                		request.setAttribute("message4", message4); // This will be available as ${message}
                        request.getRequestDispatcher("/user_profile.jsp").forward(request, response);
    	    	    }
    	    	    
    	    	}
    	    	else
    	    	{
    	    		String message4 = "You need to have exactly 7 players in your team";
            		request.setAttribute("message4", message4); // This will be available as ${message}
                    request.getRequestDispatcher("/user_profile.jsp").forward(request, response);
    	    	}
    	    }
    		}catch (Exception e2) {System.out.println(e2);}           
    		out.close();  
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
