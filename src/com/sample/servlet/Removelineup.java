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
 * Servlet implementation class AddPlayer
 */
@WebServlet("/Removelineup")
public class Removelineup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Removelineup() {
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
    		String playerid = request.getParameter("key");
    		System.out.println(playerid);
    		int pid = Integer.parseInt(playerid);
    		String user = null;
    		
    		HttpSession session = request.getSession();
    	      if(session.getAttribute("user") == null){
    	          response.sendRedirect("login.html");
    	      }else user = (String) session.getAttribute("user");
    	      
    	      
    		PreparedStatement ps=conn.prepareStatement(  
    				"update player set line_up='null'where p_id="+pid);  
    	
    		int i=ps.executeUpdate();
    		response.sendRedirect("lineup.jsp");
    	 
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

