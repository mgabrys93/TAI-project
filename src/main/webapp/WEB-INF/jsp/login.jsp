<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
</head>
<body onload='document.login_form.j_username.focus();'>

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
	
	<div id="login-box">
		<h1>Login with Username and Password</h1>
		
		<c:if test="${param.error != null}">
			<p class="error"> Login failed. Check that your username and password are correct.</p>
		</c:if>
		
		<form name='loginForm'
			action="<c:url value='/j_spring_security_check'/>" method='POST'>
		
			<table class="formTable">
				<tr>
					<td>User:</td>
					<td><input type='text' name='j_username' value=''></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type='password' name='j_password'></td>
				</tr> 
				<tr>
					<td>Remember me:</td>
					<td><input type='checkbox' name='_spring_security_remember_me' 
						checked="checked"></td>
				</tr>
				<tr>
					<td colspan="2"><input name="submit" type="submit" value="Login"></td>
				</tr>
			</table>
			
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
		
		</form>
		
		<p><a href="<c:url value="/newaccount"/>">Create new account</a></p>
	</div>

</body>
</html>