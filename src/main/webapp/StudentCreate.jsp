<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create a User</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/style/login.css">
</head>
<body>

	<form action="studentcreate" method="post">
		<img src="<%=request.getContextPath()%>/logo.png" alt="Logo">
		<h1>Create a student account</h1>
		<p>
			<label for="username">UserName</label> <input id="username"
				name="username" value="">
		</p>
		<p>
			<label for="password">Password</label> <input id="password"
				name="password" value="">
		</p>
		<p>
			<label for="email">Email</label> <input id="email" name="email"
				value="">
		</p>
		<p>
			<label for="displayName">Display Name</label> <input id="displayName"
				name="displayName" value="">
		</p>
		<p>
			<input type="submit" value="Create Acount">
		</p>
		<p>
			<a href="studentLogin">Log in</a>
		</p>
		<p>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>

	</form>



</body>
</html>