<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.ResultSet" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Records</title>
</head>
<body>

<style>

table, th, td, tr {
	border: 1px solid black;
	border-collapse: collapse;
	text-align: left;
	vertical-align: middle;
}

td {
	width: 20%;
}

</style>

<%
	ResultSet rs = (ResultSet) request.getAttribute("records");
	if (!rs.next()) {
		%> Database is empty. <%
	} else {
		rs.beforeFirst();
		%>
		<table style="width:100%">
			<tr>
				<th>Name</th>
				<th>Surname</th>
				<th>Email</th>
				<th>Occupation</th>
			</tr>
		</table>
		<%
		while (rs.next()) {
			%>
			<table style="width:100%">
				<tr>
					<td><%= rs.getString(1) %></td>
					<td><%= rs.getString(2) %></td>
					<td><%= rs.getString(3) %></td>
					<td><%= rs.getString(4) %></td>
				</tr>
			</table>
			<%
		}
	}
%>

<br>
<a href='index.html'>Back</a>

</body>
</html>