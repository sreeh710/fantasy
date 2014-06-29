<!doctype html>
<%@ page import="java.sql.*" %>

<% Class.forName("com.mysql.jdbc.Driver"); %>
<html class="no-js" lang="en">
  <head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Fantasy Cricket</title>
    <link rel="stylesheet" href="css/foundation.min.css" />
    <script src="js/vendor/modernizr.js"></script>
  </head>
  <body>
  
    <nav class="top-bar" data-topbar>
  <ul class="title-area">
    <li class="name">
      <h1><a href="#"><img src="img/logo.png" height="50px" width="50px"></a></h1>
    </li>
  <section class="top-bar-section">
    <!-- Right Nav Section -->
    <ul class="right">
      <li><a href="#">
      <%
      String user = null;
      if(session.getAttribute("user") == null){
          response.sendRedirect("login.html");
      }else user = (String) session.getAttribute("user");
      String userName = null;
      String sessionID = null;
      Cookie[] cookies = request.getCookies();
      if(cookies !=null){
      for(Cookie cookie : cookies){
          if(cookie.getName().equals("user")) userName = cookie.getValue();
          if(cookie.getName().equals("JSESSIONID")) sessionID = cookie.getValue();
      }
      }
      
      %>
      
      
      
      Welcome <%=userName %>
      </a></li>
      <li class="has-dropdown">
        <a href="#">Options</a>
        <ul class="dropdown">
          <li><a href="user_profile.jsp">My Team</a></li>
		  <li><a href="fixtures.jsp">Fixtures</a></li>
		  <li><a href="#">My History</a></li>
        </ul>
      </li>
      <li><a href="Logout">Logout</a>
      </li>
    </ul>
    
    
    

    <!-- Left Nav Section -->
    <ul class="left">
      <li><a href="#">Fantasy Cricket</a></li>
    </ul>
  </section>
</nav>



  <div style="padding:20px 50px 20px 50px;">
  <div style="float:right;" width="40%">
      <% 
            Connection connection = DriverManager.getConnection(
            		"jdbc:mysql://localhost:3306/test", "root", "");
      
      
      		 Statement statement = connection.createStatement() ;
            ResultSet resultset = 
                statement.executeQuery("select team_id from user where username = '"+userName+"'") ; 
			
            
            Statement statement1 = connection.createStatement() ;
            ResultSet resultset1 = 
                statement1.executeQuery("select * from player left join strengths ON player.skill_id = strengths.skill_id where player.team_id='"+resultset.getInt(1)+"' and line_up is null");
            
            Statement statement3 = connection.createStatement() ;
            ResultSet resultset3 = 
                statement3.executeQuery("select * from player left join strengths ON player.skill_id = strengths.skill_id where player.team_id ='"+resultset.getInt(1)+"' and line_up='1' ");
            
            Statement statement4 = connection.createStatement() ;
            ResultSet resultset4 = 
                statement4.executeQuery("select count(*) from player  where player team_id='"+resultset.getInt(1)+"' and line_up is null");
            
            int count=resultset4.getInt(1);
            if(count>2)
            {
            %>
            <h2>choose lineup of your team in batting order</h2>
       
      <TABLE>
            <TR>
                <TH>Player Id</TH>
                <TH>Player Name</TH>
                <TH>Bat-Spin</TH>
                <TH>Bat-Pace</TH>
                <TH>Bowl-Spin</TH>
                <TH>Bowl-Pace</TH>
                <TH>Price</TH>
                <TH>Select</TH>
            </TR>
            <% while(resultset1.next()){ %>
            <TR>
                <TD> <%= resultset1.getInt(1) %></TD>
                <TD> <%= resultset1.getString(2) %></TD>
                <TD> <%= resultset1.getString(7) %></TD>
                <TD> <%= resultset1.getString(8) %></TD>
                <TD> <%= resultset1.getString(9) %></TD>
                <TD> <%= resultset1.getString(10) %></td>
                <TD> <%= resultset1.getString(4) %></td>
                <TD><form><input type="button" value="select" id="<% resultset.getInt(1); %>" name="select" onclick="add(<%= resultset.getInt(1) %>)"></form></td>
            </TR>
            <% } %>
        </TABLE> 
        <script type="text/javascript">
            function add(pid)
            {
            	 document.location.href="/fantasy/Addtolineup?key="+pid;
            	/* var key = pid;
                var request=new XMLHttpRequest();
                request.open("GET", "http://localhost/fantasy/AddPlayer", true);
                request.send(key); */
            }
        </script>
  </div>
  <div style="float:left;" width="40%">
  
  <h2>your team</h2>
  <TABLE>
            <TR>
                <TH>Player Id</TH>
                <TH>Player Name</TH>
                <TH>Bat-Spin</TH>
                <TH>Bat-Pace</TH>
                <TH>Bowl-Spin</TH>
                <TH>Bowl-Pace</TH>
                <TH>Price</TH>
            </TR>
            <% while(resultset3.next()){ %>
            <TR>
                <TD> <%= resultset3.getInt(1) %></TD>
                <TD> <%= resultset3.getString(2) %></TD>
                <TD> <%= resultset3.getString(7) %></TD>
                <TD> <%= resultset3.getString(8) %></TD>
                <TD> <%= resultset3.getString(9) %></TD>
                <TD> <%= resultset3.getString(10) %></td>
                <TD> <%= resultset3.getString(4) %></td>
                <TD><form><input type="button" value="Remove" id="<% resultset3.getInt(1); %>" name="select" onclick="rem(<%= resultset3.getInt(1) %>)"></form></td>
            </TR>
            </TR>
            <% } %>
        </TABLE> 
        <script type="text/javascript">
            function rem(pid)
            { 
            	document.location.href="/fantasy/Removelineup?key="+pid;
            	/* var key = pid;
                var request=new XMLHttpRequest();
                request.open("GET", "http://localhost/fantasy/AddPlayer", true);
                request.send(key); */
            }
        </script>
    <% } else {%>
    
  </div>
  
  <div style="float:left;" width="40%">
  
  <h2>your team</h2>
  <TABLE>
            <TR>
                <TH>Player Id</TH>
                <TH>Player Name</TH>
                <TH>Bat-Spin</TH>
                <TH>Bat-Pace</TH>
                <TH>Bowl-Spin</TH>
                <TH>Bowl-Pace</TH>
                <TH>Price</TH>
            </TR>
            <% while(resultset3.next()){ %>
            <TR>
                <TD> <%= resultset3.getInt(1) %></TD>
                <TD> <%= resultset3.getString(2) %></TD>
                <TD> <%= resultset3.getString(7) %></TD>
                <TD> <%= resultset3.getString(8) %></TD>
                <TD> <%= resultset3.getString(9) %></TD>
                <TD> <%= resultset3.getString(10) %></td>
                <TD> <%= resultset3.getString(4) %></td>
            </TR>
            </TR>
            <% } %>
        </TABLE> 
            
       <h2>Extra players</h2>     
      <TABLE>
            <TR>
                <TH>Player Id</TH>
                <TH>Player Name</TH>
                <TH>Bat-Spin</TH>
                <TH>Bat-Pace</TH>
                <TH>Bowl-Spin</TH>
                <TH>Bowl-Pace</TH>
                <TH>Price</TH>
                <TH>Select</TH>
            </TR>
            <% while(resultset1.next()){ %>
            <TR>
                <TD> <%= resultset1.getInt(1) %></TD>
                <TD> <%= resultset1.getString(2) %></TD>
                <TD> <%= resultset1.getString(7) %></TD>
                <TD> <%= resultset1.getString(8) %></TD>
                <TD> <%= resultset1.getString(9) %></TD>
                <TD> <%= resultset1.getString(10) %></td>
                <TD> <%= resultset1.getString(4) %></td>
                
            </TR>
            <% } %>
        </TABLE> 
     <div>
  
     <form data-abide action="lineupconfirm">
     
      <h2>Select your Batting order(enter player ids)</h2>
  	 <div class="name-field" >
     <label>Batsman-1
      <input type="text" name="pl1" >
     </label>
     <small class="error">Bowler for 1st not selected</small>
     </div>
     
      <div class="name-field">
     <label>Batsman-2
      <input type="text" name="pl2" >
     </label>
     <small class="error">Bowler for 2nd not selected</small>
     </div>
     
      <div class="name-field">
     <label>Batsman-3
      <input type="text" name="pl3" >
     </label>
     <small class="error">Bowler for 3rd not selected</small>
     </div>
     
      <div class="name-field">
     <label>Batsman-4
      <input type="text" name="pl4" >
     </label>
     <small class="error">Bowler for 4th not selected</small>
     </div>
     
      <div class="name-field">
     <label>Batsman-5
      <input type="text" name="pl5" >
     </label>
     <small class="error">Bowler for 5th not selected</small>
     </div>
     
      <h2>Select your Bowling(enter player ids)</h2>
  	 <div class="name-field" >
     <label>Over-1
      <input type="text" name="p1" >
     </label>
     <small class="error">Bowler for 1st not selected</small>
     </div>
     
      <div class="name-field">
     <label>Over-2
      <input type="text" name="p2" >
     </label>
     <small class="error">Bowler for 2nd not selected</small>
     </div>
     
      <div class="name-field">
     <label>Over-3
      <input type="text" name="p3" >
     </label>
     <small class="error">Bowler for 3rd not selected</small>
     </div>
     
      <div class="name-field">
     <label>Over-4
      <input type="text" name="p4" >
     </label>
     <small class="error">Bowler for 4th not selected</small>
     </div>
     
      <div class="name-field">
     <label>Over-5</label>
      <input type="text" name="p5" >
     
     <small class="error">Bowler for 5th not selected</small>
     </div>
   
<button type="submit">Confirm and Play match</button>
  
</form>
 </div>
    <%} %>
    
    

 <div style="color:red;">${message1}  ${message4}</div>
  </div>
  </div>
    <script src="js/vendor/jquery.js"></script>
    <script src="js/foundation.min.js"></script>
    <script>
      $(document).foundation();
    </script>
  </body>
</html>