<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="test.ScoreOrder" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GoogleSearch</title>
</head>
<body>
<body background="https://scontent.ftpe2-2.fna.fbcdn.net/v/t1.0-9/49864163_1964106570374803_2660482835416088576_n.jpg?_nc_cat=101&_nc_ht=scontent.ftpe2-2.fna&oh=31ca393ee7072476822ce1d3b51ad417&oe=5CC89080">
<%
ScoreOrder orderList = (ScoreOrder)  request.getAttribute("so"); 
for(int i =0 ; i < orderList.getSList().size();i++){%>
 <a href='<%= orderList.getSList().get(i).urlstr %>'><%= orderList.getSList().get(i).title %></a><br><h style="font-size:5px ;"><%= orderList.getSList().get(i).urlstr %></h><br><br>
<%
}
%>
</body>
</html>