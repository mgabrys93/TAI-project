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
		<a class="title" href="<c:url value='/'/>">TAI</a>
		
		<sec:authorize access="isAuthenticated()"> 
			<a class="mygroups" href="<c:url value="/groups"/>">My groups</a>
		</sec:authorize>
		<sec:authorize access="isAuthenticated()"> 
			<a class="mygroups" href="<c:url value="/newgroup"/>">Create group</a>
		</sec:authorize>
		<sec:authorize access="isAuthenticated()"> 
			<a class="mygroups" href="<c:url value="/jointogroup"/>">Join to group</a>
		</sec:authorize>
		
		<sec:authorize access="isAuthenticated()">
			<a class="login" href="<c:url value='/j_spring_security_logout'/>">Log out</a>
		</sec:authorize>
	</div>
	
	<div class="content">
		<h2>New Event</h2>
		
		<sf:form id="details" method="post"
			commandName="event">
			
			<table class="formtable">
				<tr>
					<td class="label">Event name:</td>
					<td>
						<sf:input class="control" path="eventname"
							name="eventname" type="text"/>
						<div class="error">
							${eventname_message}
						</div>
					</td>
				</tr>
				<tr>
					<td class="label">Event description:</td>
					<td>
						<sf:input class="control" path="description"
							name="description" type="text"/>
						<div class="error">
							${description_message}
						</div>
					</td>
				</tr>
				<tr>
					<td class="label"/>
					<td>
						<input class="control" value="Create event." type="submit">
					</td>
				</tr>
			</table>
			
		</sf:form>
		
		<c:forEach var="eventItem" items="${eventList}" varStatus="loop">
			<div>
				<table>
					<tr>
						-----------------------------------------------
					</tr>
					<tr>
						<td class="label">Event author:</td>
						<td>
							${eventItem.author.username}
						</td>
					</tr>
					<tr>
						<td class="label">Event name:</td>
						<td>
							<c:url value="/event/${eventItem.eventId}/comment" var="eventPath"/>
							<a href="${eventPath}">${eventItem.eventname}</a>
						</td>
					</tr>
					<tr>
						<td class="label">Event description:</td>
						<td>
							${eventItem.description}
						</td>
					</tr>
				</table>
			</div>
		</c:forEach>
	</div>

</body>
</html>