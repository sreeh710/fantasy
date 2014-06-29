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
 * Servlet implementation class RemovePlayer
 */
@WebServlet("/RemovePlayer")
public class RemovePlayer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemovePlayer() {
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
    				"update player set team_id = null where p_id="+pid);  
    		String sql1 = "select value from player where p_id ="+pid;
    		Statement s = conn.prepareStatement(sql1);
    		java.sql.ResultSet rs = s.executeQuery(sql1);
    		int val = 0;
    		while(rs.next())
    		{
    			val=rs.getInt(1);
    			System.out.println("Value = "+val);
    		}
    		
    		String sql2 = "select amount from user where username ='"+user+"'";
    		Statement s1 = conn.prepareStatement(sql2);
    		java.sql.ResultSet rs1 = s1.executeQuery(sql2);
    		int amount = 0;
    		while(rs1.next())
    		{
    			amount=rs1.getInt(1);
    			System.out.println("Amount = "+amount);
    		}
    		
    		PreparedStatement ps1=conn.prepareStatement(
    				"update user set amount = amount + "+val+" where username = '"+user+"'");
    		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!");
    		System.out.println(pid);
    		System.out.println(user);
    		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!");
    	    int i=ps.executeUpdate();
    	    int j=ps1.executeUpdate();
    	    if(i>0 && j>0)
    	    {
    	    	response.sendRedirect("maketeam.jsp");
    	    }
    	    else
    	    {
    	    	out.println("Error connecting to database");
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
