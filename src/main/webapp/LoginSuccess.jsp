<%@page import="eduguru.model.Students"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Success</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/style/overview.css">
</head>
<body>
	<%
	//allow access only if session exists
	String user = null;
	Students student = null;
	if (session.getAttribute("student") == null) {
		String encodedURL = response.encodeRedirectUrl("StudentLogin.jsp");
		response.sendRedirect(encodedURL);
	} else
		student = (Students) session.getAttribute("student");
	user = (String) session.getAttribute("username");
	String userName = null;
	String sessionID = null;
	Cookie[] cookies = request.getCookies();
	if (cookies != null) {
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("username"))
		userName = cookie.getValue();
			if (cookie.getName().equals("sessionID"))
		sessionID = cookie.getValue();
		}
	} else {
		sessionID = session.getId();
	}
	%>

	<div class="container">
		<img src="<%=request.getContextPath()%>/logo.png" alt="Logo">
<%-- 		<h3>
			Welcome,
			<%=userName%></h3> --%>
		<h1>Account Overview</h1>

		<ul>
			<li><label>UserName: </label> <span>${student.getUsername()}</span>
			</li>
			<li><label>Email: </label> <span>${student.getEmail()}</span></li>
			<li><label>Display Name: </label> <span>${student.getDisplayName()}</span>
			</li>

		</ul>

		<div>
			<a
				href="FindCategory?username=<c:out value="${student.getUsername()}"/>"
				class="btn">Discover Courses</a> <a
				href="findsubscribes?username=<c:out value="${student.getUsername()}"/>"
				class="btn">Subscribes</a> <a
				href="studentupdate?username=<c:out value="${student.getUsername()}"/>"
				class="btn">Update Email</a> <a
				href="studentdelete?username=<c:out value="${student.getUsername()}"/>"
				class="btn">Delete Account</a>

			<form action="<%=response.encodeURL("LogoutServlet")%>" method="post"
				class="btn">
				<input type="submit" value="Logout">
			</form>

		</div>

		<%-- 
		<table border="1">
			<tr>
				<th>UserName</th>
				<th>Email</th>
				<th>Display Name</th>

			</tr>
			<tr>

				<td>${student.getUsername()}</td>
				<td>${student.getEmail()}</td>
				<td>${student.getDisplayName()}</td>


				<td><a
					href="studentupdate?username=<c:out value="${student.getUsername()}"/>">Update
						Email</a></td>
				<td><a
					href="studentdelete?username=<c:out value="${student.getUsername()}"/>">Delete
						Account</a></td>

				<td><a
					href="findsubscribes?username=<c:out value="${student.getUsername()}"/>">Subscribes</a></td>

			</tr>
		</table>

		<h1>
			<a
				href="FindCategory?username=<c:out value="${student.getUsername()}"/>">Discover
				Courses</a>
		</h1>

		<form action="<%=response.encodeURL("LogoutServlet")%>" method="post">
			<input type="submit" value="Logout">
		</form> --%>
	</div>

</body>

</html>