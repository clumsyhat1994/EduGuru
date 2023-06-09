<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update a User</title>
</head>
<body>

	<div class="container">
		<jsp:include page="Navigator.jsp" />
		<div class="content">

			<form action="studentupdate" method="post">

				<h1>Update Email</h1>
				<p>
					<label for="username">UserName</label> <input id="username"
						name="username" value="${fn:escapeXml(param.username)}">
				</p>
				<p>
					<label for="email">New Email</label> <input id="email" name="email"
						value="">
				</p>
				<p>
					<input type="submit" value="submit">
				</p>
			</form>
			<p>
				<span id="successMessage"><b>${messages.success}</b></span>
			</p>
		</div>
	</div>
</body>
</html>