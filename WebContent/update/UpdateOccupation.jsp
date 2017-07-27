<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

Please re-enter your name, and enter a new occupation:
<form action="/JoshWebProject/UpdateOccupation" method="post">

Name: <input type="text" name="name" /><br> <% //Really not the best way to do this %>
Surname: <input type="text" name="surname" /><br>
New Occupation: <input type="text" name="newoccupation" /><br/>

<input type="submit" value="Update" />

</form>

</body>
</html>