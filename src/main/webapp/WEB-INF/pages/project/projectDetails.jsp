<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<s:url value="/project/edit/addComment.do" var="addCommentUrl"/>

<h1>Projekt <b>${project.name}</b></h1>
<p>${project.content}</p>
<a href="<s:url value="/project/edit/edit.do?pid=${project.id}"/>">Editace</a>
<h2>Komentáře:</h2>

<sf:form commandName="comment" method="POST" action="${addCommentUrl}" id="comment">
    <input type="hidden" name="projectId" value="${project.id}"/>
    <label for="commentContent">Komentář: </label><br/>
    <sf:textarea id="commentContent" path="content" cols="80" rows="4"/><br/>
    <input type="submit" value="Komentuj"/>
</sf:form>
    
<ul>
<c:forEach items="${project.comments}" var="comment">
    <fmt:formatDate value="${comment.date}" pattern="dd. MM. yyyy" var="formatedDate"/>
    <li>${comment.content} (${formatedDate})</li>
</c:forEach>
</ul>