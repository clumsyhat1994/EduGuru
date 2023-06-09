<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Student login</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/style/login.css">
</head>
<body>
	
	<form action="studentLogin" method="post">
		<img src="<%=request.getContextPath()%>/logo.png" alt="Logo">
		<h1>${messages.title}</h1>
		<h2>Student Log in</h2>
		<p>
		<div
			<c:if test="${messages.disableSubmit}">style="display:none"</c:if>>
			<label for="username">UserName</label> <input id="username"
				name="username" type="text" value="${fn:escapeXml(param.username)}">
		</div>
		</p>
		<div
			<c:if test="${messages.disableSubmit}">style="display:none"</c:if>>
			<label for="password">Password</label> <input id="password"
				name="password" type="password" value="${fn:escapeXml(param.password)}">
		</div>
		<p>
			<span id="submitButton"
				<c:if test="${messages.disableSubmit}">style="display:none"</c:if>>
				<input type="submit" value="log in">
			</span>
		</p>
		<br />
		<div id="studentCreate">
			<a href="studentcreate">Create an account</a>
		</div>
		<br />

	</form>

</body>
</html>
