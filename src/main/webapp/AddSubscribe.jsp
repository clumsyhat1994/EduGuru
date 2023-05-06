<%@page import="eduguru.model.Students"%>
<%@page import="eduguru.model.Courses"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Subscribe a course</title>
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
	String courseTitle = null;
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
	Students reqStudent = (Students) request.getAttribute("student");
	Courses course = (Courses) request.getAttribute("course");
	%>
	<div class="container">
		<jsp:include page="Navigator.jsp" />
		<div class="content">
		<h1>${messages.title}</h1>
			<form action="addSubscribe" method="post">

				<p>
				<div
					<c:if test="${messages.disableSubmit}">style="display:none"</c:if>>

					<label for="username">UserName</label> <input id="username"
						name="username" value="${student.getUsername()}"> <label
						for="courseId">CourseId</label> <input id="courseId"
						name="courseId" value="${fn:escapeXml(param.courseId)}">
				</div>
				</p>

				<p>
					<span id="submitButton"> <input type="submit"
						value="Confirm Subscribe">
					</span>
				</p>
				<a
					href="findsubscribes?username=<c:out value="${student.getUsername()}"/>">Check
					out subscribed courses</a>
			</form>
		</div>
	</div>
</html>