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
	%>
<div class="container">
  <jsp:include page="Navigator.jsp" />
	<div class="content">
		<h1>Popular Course Categories</h1>
		<table border="1">
			<tr>
				<th>Course Category</th>
			</tr>
			<c:forEach items="${courseCategories}" var="courseCategory">
				<tr>

					<td>${courseCategory.getCourseCategory()}</td>
					<c:url value="findcourses" var="coursesurl">
						<c:param name="category"
							value="${courseCategory.getCourseCategory()}" />
					</c:url>

					<c:url value="FindInstructors" var="instructorsurl">
						<c:param name="category"
							value="${courseCategory.getCourseCategory()}" />
					</c:url>
					<td><a href="${coursesurl}">Top Courses</a></td>
					<td><a href="${instructorsurl}">Top Instructors</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>

</div>
</body>
</html>