package com.sample.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Cookie;

/**
 * Servlet implementation class Logout
 */
@WebServlet("/Logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Logout() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
        for(Cookie cookie : cookies){
            if(cookie.getName().equals("JSESSIONID")){
                System.out.println("JSESSIONID="+cookie.getValue());
                break;
            }
        }
        }  try
        {
    		Class.forName("com.mysql.jdbc.Driver");  
    		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test",
                    "root", ""); 
    		String user = null;
    		HttpSession session = request.getSession();
    	      if(session.getAttribute("user") == null){
    	          response.sendRedirect("login.html");
    	      }else user = (String) session.getAttribute("user");
        String sql3 = "select confirmed from team where username = '"+user+"'";
		PreparedStatement ps2=conn.prepareStatement(sql3);
	    
		java.sql.ResultSet rs = ps2.executeQuery(sql3);
        int selected = 1;
        while (rs.next()) {
        	selected = rs.getInt(1);
        	System.out.println("Selected is :"+selected);
        }
        if(selected == 0)
        {
        	String sql4 = "update player set team_id = null  where team_id = (select team_id from user where username ='"+user+"')";
        	PreparedStatement ps3=conn.prepareStatement(sql4);
        	ps3.executeUpdate(sql4);
        	String sql5 = "update user set amount = 60  where username ='"+user+"'";
        	PreparedStatement ps4=conn.prepareStatement(sql5);
        	ps4.executeUpdate(sql5);
        }
        }catch (Exception e2) {System.out.println(e2);}           
		 
        //invalidate the session if exists
        HttpSession session1 = request.getSession(false);
        System.out.println("User="+session1.getAttribute("user"));
        if(session1 != null){
            session1.invalidate();
        }
        response.sendRedirect("login.html");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
