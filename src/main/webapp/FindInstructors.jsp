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
<title>Find Courses</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/style/navigator.css">
</head>
<body>
	<%
	Students student = (Students) session.getAttribute("student");
	CourseCategory courseCategory = CourseCategory
			.fromString(request.getParameter("category"));
	%>
	<div class="container">
		<jsp:include page="Navigator.jsp" />
		<div class="content">
			<h1>Popular instructors in ${fn:escapeXml(param.category)}</h1>

			<br /> <br />

			<table border="1">
				<tr>
					<th>Instructor Name</th>
				</tr>
				<c:forEach items="${instructors}" var="instructor">
					<tr>
						<td><c:out value="${instructor.getFullName()}" /></td>
						<td><a
							href="FindCoursesByInstructor?instructor=<c:out value="${instructor.getUsername()}"/>">Courses</a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>

</body>
</html>