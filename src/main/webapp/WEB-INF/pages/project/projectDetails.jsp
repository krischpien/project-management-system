<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<s:url value="/project/edit/addComment.do" var="addCommentUrl"/>

<h1>Projekty &raquo; Projekt <b>'${project.name}'</b></h1>
<h2>Založen: </h2>
<p>${project.dateCreate}</p>
<h2>Popis projektu (obsah):</h2>
<p>${project.content}</p>
<h2>Termín projektu:</h2>
<p>${project.dateDeadline}</p>
<a href="<s:url value="/project/edit/edit.do?pid=${project.id}"/>">Editace</a><br/>
<a href="<s:url value="/project/${project.id}-${project.name}/requirement/newRequirement.do"/>">Nový požadavek</a>
<h2>Požadavky</h2>
<%-- Když je seznam požadavků prázdný --%>
<c:if test="${empty project.requirements}">
    <p>Žádné požadavky k projektu</p>
</c:if>
<%-- Když seznam požadavků obsahuje prvky: --%>
<ul>
<c:forEach items="${project.requirements}" var="requirement">
    <li>${requirement.name}, </li>
</c:forEach>
</ul>

<h2>Komentáře:</h2>
<sf:form commandName="comment" method="POST" action="${addCommentUrl}" id="comment">
    <input type="hidden" name="projectId" value="${project.id}"/>
    <label for="commentContent">Předmět: </label><sf:input path="subject"/><br/>
    <sf:textarea id="commentContent" path="content" cols="80" rows="4"/><br/>
    <input type="submit" value="Komentuj"/>
</sf:form>
    
<ul>
<c:forEach items="${project.comments}" var="comment">
    <fmt:formatDate value="${comment.dateCreate}" pattern="dd. MM. yyyy" var="formatedDate"/>
    <li>${comment.author.name}: ${comment.content} (${formatedDate})</li>
</c:forEach>
</ul>