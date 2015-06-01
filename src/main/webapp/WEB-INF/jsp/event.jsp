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
		<h2>New Comment</h2>
		
		<sf:form id="details" method="post"
			commandName="comment">
			
			<table class="formtable">
				<tr>
					<td class="label">Comment text:</td>
					<td>
						<sf:input class="control" path="text"
							name="text" type="text"/>
						<div class="error">
							${comment_message}
						</div>
					</td>
				</tr>
				<tr>
					<td class="label"/>
					<td>
						<input class="control" value="Create comment" type="submit">
					</td>
				</tr>
			</table>
			
		</sf:form>
		
		<c:forEach var="commentItem" items="${commentList}" varStatus="loop">
			<div>
				<table>
					<tr>
						-----------------------------------------------
					</tr>
					<tr>
						<td class="label">Comment author:</td>
						<td>
							${commentItem.author.username}
						</td>
					</tr>
					<tr>
						<td class="label">Comment:</td>
						<td>
							${commentItem.text}
						</td>
					</tr>
				</table>
			</div>
		</c:forEach>
	</div>

</body>
</html>