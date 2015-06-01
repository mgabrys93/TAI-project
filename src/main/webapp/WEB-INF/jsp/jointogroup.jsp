<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
		
		<sec:authorize access="isAuthenticated()">
			<a class="login" href="<c:url value='/j_spring_security_logout'/>">Log out</a>
		</sec:authorize>
	</div>
	
	<div class="content">
		<h1>Select group to join</h1>
		
		<sf:form id="details" method="post"
			commandName="group">
			<tr>
				<td class="label">Select group:</td>
				<td>
					<sf:select path="groupname"  items="${groups}">
							
					</sf:select>
					<div class="error">
						${message}
						<sf:errors path="groupname"></sf:errors>
					</div>
				</td>
			</tr>
			<tr>
				<td class="label"/>
				<td>
					<input class="control" value="Join to group" type="submit"> 
				</td>
				
				
			</tr>
			
		</sf:form>
				
	</div>

</body>
</html>