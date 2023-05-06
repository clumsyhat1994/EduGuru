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
	href="<%=request.getContextPath()%>/style/navigator.css">
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

		<div class="navbar">
			<p><%=userName%></p>

			<ul>
				<li><input type="button" value="Home" name="LoginSuccess"
					onclick="window.location='LoginSuccess.jsp'" /></li>
				<li><form action="<%=response.encodeURL("LogoutServlet")%>"
						method="post">
						<input type="submit" value="Logout">
					</form></li>
			</ul>
		</div>
	</div>
</body>

</html>