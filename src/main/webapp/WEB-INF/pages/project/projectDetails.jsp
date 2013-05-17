<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<s:url value="/project/edit/addComment.do" var="addCommentUrl"/>

<h1>Projekty &raquo; Projekt <b>'${project.name}'</b></h1>
<div id="leftBlock">
<h2>Popis</h2>
<table id="projectDetails">
    <thead>
        <tr>
            <th id="column1"></th>
            <th id="column2"></th>
        </tr>
            
    </thead>
    <tr>
        <td>Stav:</td>
        <td>${project.phase.description}</td>
    </tr>
    <tr>
        <td>Založen:</td>
        <td><p><fmt:formatDate value="${project.dateCreate}" pattern="dd. MM. yyyy"/></p></td>
    </tr>
    
    <tr>
        <td>Popis projektu (obsah):</td>
        <td><p>${project.content}</p></td>
    </tr>
    
    <tr>
        <td>Termín projektu:</td>
        <td><p><fmt:formatDate value="${project.dateDeadline}" pattern="dd. MM. yyyy"/></p></td>
    </tr>
    
    <tr>
        <td>Rozpočet:</td>
        <td>${project.budget} Kč</td>
    </tr>
    
    <tr>
        <td>Uživatelé projektu:</td>
        <td>
            <ul>
            <c:forEach items="${project.authorizedUsers}" var="user" varStatus="loop">
                <li title="${user.email}">${user.name}${!loop.last? ',':''}</li>
            </c:forEach>
            </ul>
        </td>
    </tr>
</table>


<a href="<s:url value="/project/edit/edit.do?pid=${project.id}"/>">Editace</a><br/>


<h2>Komentáře:</h2>
<sf:form commandName="comment" method="POST" action="${addCommentUrl}" id="comment">
    <input type="hidden" name="projectId" value="${project.id}"/>
    <label for="commentContent">Předmět: </label><sf:input path="subject"/><sf:errors path="subject" cssClass="errorMessage"/><br/>
    <sf:textarea id="commentContent" path="content" cols="80" rows="4"/><br/>
    <sf:errors path="content" cssClass="errorMessage"/><br/>
    <input type="submit" value="komentovat"/><br class="clear"/>
</sf:form>


    <div id="commentList">
<c:if test="${!empty project.comments}">
    <c:forEach items="${project.comments}" var="comment" varStatus="loop">
        <div class="comment">
        <fmt:formatDate value="${comment.dateCreate}" pattern="dd. MM. yyyy" var="formatedDate"/>

        <h3><b>${comment.subject}</b> (${comment.author.name})</h3>
        <p>${comment.content}</p>
        <h4 class="bottomLine">datum: ${formatedDate}</h4>
        </div>
        <c:if test="${loop.last}"><c:set value="${loop.index}" var="count" scope="page"/></c:if>
    </c:forEach>
    <h3>Počet komentářů: ${count +1} </h3>
</c:if>
    <c:if test="${empty project.comments}"><h3>Žádné komentáře.</h3></c:if>
    </div>
</div>

<div id="rightBlock">
<h2>Požadavky</h2>
<%-- Když je seznam požadavků prázdný --%>
<c:if test="${empty project.requirements}">
    <p>Žádné požadavky k projektu</p>
</c:if>
<%-- Když seznam požadavků obsahuje prvky: --%>

<div id="requirementList">
    <c:forEach items="${project.requirements}" var="requirement" varStatus="loop">
        <div class="requirement">
            <h3 class="headline"><span class="counter">#${loop.index +1}</span> <a href=""><b>${requirement.name}</b></a></h3>
            <h3><b>Stav:</b> ${requirement.phase.description}</h3>
            <h3>Vytvořeno: <fmt:formatDate value="${requirement.dateCreate}" pattern="dd. MM. yyyy"/></h3>
            <h3><b>Popis:</b></h3>
            <p>${requirement.content}</p>
        </div>
    </c:forEach>
</div>

    <a href="<s:url value="/project/${project.id}-${project.name}/requirement/newRequirement.do"/>">Nový požadavek</a>
</div>
<br class="clear"/>