package com.sample.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AddPlayer
 */
@WebServlet("/lineupconfirm")
public class Lineupconfirm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Lineupconfirm () {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");  
		PrintWriter out = response.getWriter();  
		          
		String ps1=request.getParameter("p1"); 
		int p1 = Integer.parseInt(ps1);
		System.out.println(p1);
		String ps2=request.getParameter("p2"); 
		int p2 = Integer.parseInt(ps2);
		System.out.println(p2);
		String ps3=request.getParameter("p3");  
		int p3 = Integer.parseInt(ps3);
		System.out.println(p3);
		String ps4=request.getParameter("p4"); 
		int p4 = Integer.parseInt(ps4);
		System.out.println(p4);
		String ps5=request.getParameter("p5"); 
		int p5 = Integer.parseInt(ps5);
		System.out.println(p5);
		
		
		String pls1=request.getParameter("pl1"); 
		int pl1 = Integer.parseInt(pls1);
		System.out.println(p1);
		String pls2=request.getParameter("pl2"); 
		int pl2 = Integer.parseInt(pls2);
		System.out.println(p2);
		String pls3=request.getParameter("pl3");  
		int pl3 = Integer.parseInt(pls3);
		System.out.println(p3);
		String pls4=request.getParameter("pl4"); 
		int pl4 = Integer.parseInt(pls4);
		System.out.println(p4);
		String pls5=request.getParameter("pl5"); 
		int pl5 = Integer.parseInt(pls5);
		System.out.println(p5);
		
		int plo1=0, plo2=0, plo3=0, plo4=0, plo5=0;
		int po1=0, po2=0, po3=0, po4=0, po5=0;
		
		
		
		try{  
		Class.forName("com.mysql.jdbc.Driver");  
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test",
                "root", ""); 
		
		 Statement statement = connection.createStatement() ;
         ResultSet resultset = 
             statement.executeQuery("select team_id from player where P_id ='"+p1+"'") ; 
         
         int tid=resultset.getInt(1);
          
		 Statement statement2 = connection.createStatement() ;
         ResultSet resultset2= 
             statement2.executeQuery("select match_id,team1_conf,team2_conf,team2id_ from match where team1_id='"+tid+"'");
         
         int mid=0;
         int t1c=0;
         int t2c=0;
         int t1id=0;
         int t2id=0;
         
         if(resultset2.next())
         {
        	   mid=resultset2.getInt(1);
               t1c=resultset2.getInt(2);
               t2c=resultset2.getInt(3);
               t2id=resultset2.getInt(4);
               t1id=tid;
               Statement statement4 = connection.createStatement();
		        statement4.executeQuery("update match set team1_conf='1'  where team1_id ='"+tid+"'");
		        
		        if(t2c!=0)
		        {
		        	 Statement statement5 = connection.createStatement() ;
		             ResultSet resultset5= 
		                 statement5.executeQuery("select *  from batting where match_id='"+mid+" and team2_id='"+t2id+"'");
		             
		             Statement statement6 = connection.createStatement() ;
		             ResultSet resultset6= 
		                 statement6.executeQuery("select *  from bowling where match_id='"+mid+" and team2_id='"+t2id+"'");
		             
		             plo1=resultset5.getInt(3);
		             plo2=resultset5.getInt(4);
		             plo3=resultset5.getInt(5);
		             plo4=resultset5.getInt(6);
		             plo5=resultset5.getInt(7);
		             
		             
		             po1=resultset6.getInt(3);
		             po2=resultset6.getInt(4);
		             po3=resultset6.getInt(5);
		             po4=resultset6.getInt(6);
		             po5=resultset6.getInt(7);
		             
		             
		        }
		        
		   		
		         
         }
         
         else
         {
        	   
    		 Statement statement4= connection.createStatement() ;
             ResultSet resultset4= 
                 statement4.executeQuery("select match_id,team1_conf,team2_conf,team1_idfrom match where team2_id='"+tid+"'");
             
             mid=resultset4.getInt(1);
             t1c=resultset4.getInt(2);
             t2c=resultset4.getInt(3);
             t1id=resultset4.getInt(4);
             t2id=tid;
             
             Statement statement6 = connection.createStatement();
		        statement6.executeQuery("update match set team1_conf='1'  where team2_id ='"+tid+"'") ; 
		        
		        if(t1c!=0)
		        {
		        	 Statement statement5 = connection.createStatement() ;
		             ResultSet resultset5= 
		                 statement5.executeQuery("select *  from batting where match_id='"+mid+" and team2_id='"+t1id+"'");
		             
		             Statement statement7 = connection.createStatement() ;
		             ResultSet resultset7= 
		                 statement7.executeQuery("select *  from bowling where match_id='"+mid+" and team2_id='"+t1id+"'");
		             
		             plo1=resultset5.getInt(3);
		             plo2=resultset5.getInt(4);
		             plo3=resultset5.getInt(5);
		             plo4=resultset5.getInt(6);
		             plo5=resultset5.getInt(7);
		             
		             
		             po1=resultset7.getInt(3);
		             po2=resultset7.getInt(4);
		             po3=resultset7.getInt(5);
		             po4=resultset7.getInt(6);
		             po5=resultset7.getInt(7);
		             
		             
		        }
		        
		         
         }
         
       
        
		PreparedStatement ps=connection.prepareStatement(  
		"insert into bowling values(?,?,?,?,?,?,?)");  
		
		ps.setInt(1,mid);  
		ps.setInt(2,tid);  
		ps.setInt(3,p1);  
		ps.setInt(4,p2);  
		ps.setInt(5,p3);  
		ps.setInt(6,p4);
		ps.setInt(7,p5); 
		          
		int i=ps.executeUpdate();
		
		
		PreparedStatement pds=connection.prepareStatement(  
				"insert into batting values(?,?,?,?,?,?,?)");  
				
				pds.setInt(1,mid);  
				pds.setInt(2,tid);  
				pds.setInt(3,pl1);  
				pds.setInt(4,pl2);  
				pds.setInt(5,pl3);  
				pds.setInt(6,pl4);
				pds.setInt(7,pl5); 
				          
				int j=pds.executeUpdate();
				
				if((t1c==0)||(t2c==0))
				{
 
					 response.sendRedirect("wait.jsp");          
				         
				}
				
				else
				{
					String status="started";
					 Statement statement7 = connection.createStatement();
				        statement7.executeQuery("update match set status='"+status+"' where team2_id ='"+tid+"'") ;
				        
				        
				        response.sendRedirect("match.jsp");
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

