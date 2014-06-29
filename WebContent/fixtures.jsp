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
		  <li><a href="maketeam.jsp">Make Team</a></li>
		  <li><a href="fixtures.jsp">Fixtures</a></li>
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
      
      		
          String status="not-played";      		

            Statement statement2 = connection.createStatement() ;
            ResultSet resultset2 = 
                statement2.executeQuery("select * from match where status ='"+status+"'");
            
            %>

  </div>
  <div style="float:left;" width="40%">
  <% 
  if(resultset2.next()){
  %>
   <h2>Fixtures</h2>
   
  <TABLE>
            <TR>
                <TH>Team1</TH>
                <TH>Team2</TH>
                <TH>Time</TH>
            </TR>
            <% while(resultset2.next()){
            	
            	 Statement statement3 = connection.createStatement() ;
                 ResultSet resultset3 = 
                     statement3.executeQuery("select team_name from team where team_id = '"+resultset2.getInt(2)+"'");
                 
            	 Statement statement4 = connection.createStatement() ;
                 ResultSet resultset4 = statement4.executeQuery("select team_name from team where team_id = '"+resultset2.getInt(3)+"'");
            	
            %>
            <TR>
                <TD> <%= resultset3.getString(1) %></TD>
                <TD> <%= resultset4.getString(1) %></TD>
                <TD> <%= resultset2.getString(6) %></TD>
            </TR>
            </TR>
            <% } %>
        </TABLE> 
        <div>
        <form action="lineup.jsp">
         <input type="submit" value="play match">
        </form> 
        </div><% }else {%> <a href=maketeam.jsp><h2> PROCEED FOR SELECTING TEAM</h2></a> <% } %>

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