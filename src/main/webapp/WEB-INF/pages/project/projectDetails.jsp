<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<s:url value="/project/edit/addComment.do" var="addCommentUrl"/>


<h1><fmt:message key="project.title"/> <b>'${project.name}'</b></h1>
<div id="leftBlock">
<h2><fmt:message key="project.description"/></h2>

<table id="projectDetails">
    <thead>
        <tr>
            <th id="column1"></th>
            <th id="column2"></th>
        </tr>
            
    </thead>
    <tr>
        <td><fmt:message key="project.state"/>:</td>
        <td>${project.phase.description}</td>
    </tr>
    <tr>
        <td><fmt:message key="project.dateCreate"/>:</td>
        <td><p><fmt:formatDate value="${project.dateCreate}" pattern="dd. MM. yyyy"/></p></td>
    </tr>
    
   
            
            
    <tr>
        <td><fmt:message key="project.content"/>:</td>
        <td><p>${project.content}</p></td>
    </tr>
    
    <tr>
        <td><fmt:message key="project.dateDeadline"/>:</td>
        <td><p><fmt:formatDate value="${project.dateDeadline}" pattern="dd. MM. yyyy"/></p></td>
    </tr>
    
    <tr>
        <td><fmt:message key="project.budget"/>:</td>
        <td>${project.budget} Kč</td>
    </tr>
    
    <tr>
        <td><fmt:message key="project.authorizedUsers"/>:</td>
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


<h2><fmt:message key="project.comment.title"/>:</h2>
<sf:form commandName="comment" method="POST" action="${addCommentUrl}" id="comment">
    <input type="hidden" name="projectId" value="${project.id}"/>
    <label for="commentContent"><fmt:message key="project.comment.subject"/>: </label><sf:input path="subject"/><sf:errors path="subject" cssClass="errorMessage"/><br/>
    <sf:textarea id="commentContent" path="content" cols="80" rows="4"/><br/>
    <sf:errors path="content" cssClass="errorMessage"/><br/>
    <input type="submit" value="<fmt:message key="project.comment.submit"/>"/><br class="clear"/>
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
    <h3><fmt:message key="project.comment.count"/>: ${count +1} </h3>
</c:if>
    <c:if test="${empty project.comments}"><h3><fmt:message key="project.comment.null"/>.</h3></c:if>
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