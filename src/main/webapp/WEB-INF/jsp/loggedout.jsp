<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
</head>
<body>
	
	<div class="header">
		<a class="title" href="<c:url value='/home'/>">TAI</a>
		
		<sec:authorize access="isAuthenticated()"> 
			<a class="mygroups" href="<c:url value="/groups"/>">My groups</a>
		</sec:authorize>
		
		<sec:authorize access="isAuthenticated()"> 
			<a class="mygroups" href="<c:url value="/jointogroup"/>">Join to group</a>
		</sec:authorize>
		
		<sec:authorize access="!isAuthenticated()">
			<a class="login" href="<c:url value='/login'/>">Log in</a> 
		</sec:authorize>
		
		<sec:authorize access="isAuthenticated()">
			<a class="login" href="<c:url value='/j_spring_security_logout'/>">Log out</a>
		</sec:authorize>
	</div>
	
	<div class="content">
		You have successfully logged out.
	</div>
	
</body>
</html>