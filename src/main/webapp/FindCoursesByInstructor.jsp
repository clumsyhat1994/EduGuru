<%@page import="eduguru.model.Instructors"%>
<%@page import="eduguru.model.Courses.CourseCategory"%>
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
<title>Find Courses By Instructor</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/style/navigator.css">
</head>
<body>
	<%
	Students student = (Students) session.getAttribute("student");
	Instructors instructor = (Instructors) request.getAttribute("instructor");
	%>
	<div class="container">
		<jsp:include page="Navigator.jsp" />
		<div class="content">

			<h1>Courses taught by ${instructor.getFullName()}</h1>
			<br /> <br />
			<table border="1">
				<tr>
					<th>Title</th>
					<th>Price</th>
					<th>Average Rating</th>
					<th>Published Time</th>
					<th>Category</th>
					<th>Subscribe</th>
				</tr>
				<c:forEach items="${courses}" var="course">
					<tr>
						<td><c:out value="${course.getTitle()}" /></td>
						<td><c:out value="${course.getPrice()}" /></td>
						<td><c:out value="${course.getAvgRating()}" /></td>
						<td><c:out value="${course.getPublishedTime()}" /></td>
						<td><c:out value="${course.getCourseCategory()}" /></td>

						<td><a
							href="addSubscribe?courseId=<c:out value="${course.getCourseId()}"/>">Subscribe</a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>