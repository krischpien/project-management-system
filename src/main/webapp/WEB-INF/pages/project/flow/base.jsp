<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="_projectPhase" value="${project.phase.id}"/>
<s:url value="/project/edit/addComment.do" var="addCommentUrl"/>


<h1>Formulář projektu</h1>
<sf:form commandName="project" method="POST" id="projectForm">
    
    
    
    <div id="phasesBlock">
        <ul>
            <c:forEach items="${allPhases}" var="phase">
                <li class="phase 
                    ${phase.id == _projectPhase? "current":""} 
                    ${phase.id==_projectPhase && _projectPhase==8? "complaint":""}
                    ${phase.id==_projectPhase && _projectPhase==7? "deployed":""}
                    ">
                    ${phase.description} 
                    <c:if test="${phase.id == _projectPhase}">
                        <c:forEach items="${project.projectHistory}" var="history" varStatus="loop">
                            <b>${loop.last? history.dateChange :""}</b>
                        </c:forEach>
                    </c:if>
                    </li>
            </c:forEach>
        </ul>
    </div>

    <div id="projectBlock" class="phase ${_projectPhase==8? "complaint":""} ${_projectPhase==7?"deployed":""}">
        <sf:hidden path="id"/>
        <sf:hidden path="dateCreate"/>
        <input type="hidden" name="phase" value="${project.phase.id}"/>
        <label for="name">Název:</label><sf:input path="name" id="name" readonly="true" class="readonly"/><br/>

        <h3>Oprávnění na projekt</h3>    
        <sec:authorize access="hasRole('ROLE_ADMIN')">
            <sf:select path="authorizedUsers" items="${allUsers}" itemValue="id" itemLabel="name" /><br/>
        </sec:authorize>
        <sec:authorize ifNotGranted="ROLE_ADMIN">
            <ul>
            <c:forEach items="${project.authorizedUsers}" var="user" varStatus="loop">
                <li title="${user.email}">${user.name}${!loop.last? ',':''}</li>
            </c:forEach>
            </ul>

        <sf:select path="authorizedUsers" items="${allUsers}" itemValue="id" itemLabel="name" style="display:none"/><br/>

        </sec:authorize>
        <br/>
        
        <c:choose>
            <c:when test="${_projectPhase == 1}">
               <!--created-->
               <tiles:insertAttribute name="place"/>
           </c:when>

           <c:when test="${_projectPhase == 2}">
               <!--placed-->
               <tiles:insertAttribute name="appraise"/>
           </c:when>

           <c:when test="${_projectPhase == 3}">
               <!--appraised-->
               <tiles:insertAttribute name="approve"/>
           </c:when>

           <c:when test="${_projectPhase == 4}">
               <!--approved-->
               <tiles:insertAttribute name="realise"/>
           </c:when>

           <c:when test="${_projectPhase == 5}">
               <!--realisation-->
               <tiles:insertAttribute name="tested"/>
           </c:when>

           <c:when test="${_projectPhase == 6}">
               <!--testing-->
               <tiles:insertAttribute name="deploy"/>
           </c:when>

           <c:when test="${_projectPhase == 7}">
               <!--complaint-->
               <tiles:insertAttribute name="complaint"/>
           </c:when>

           <c:when test="${_projectPhase == 8}">
               <!--complained-->
                <tiles:insertAttribute name="complained"/>
           </c:when>

        </c:choose> 

    
    </div>
    
</sf:form>
<br class="clear"/>
<div id="commentsBlock">
<h2><fmt:message key="project.comment.title"/>:</h2>
<sf:form commandName="newComment" method="POST" action="${addCommentUrl}" id="comment">
    <input type="hidden" name="projectId" value="${project.id}"/>
    <label for="commentContent"><fmt:message key="project.comment.subject"/>: </label><sf:input path="subject"/><sf:errors path="subject" cssClass="errorMessage"/><br/>
    <sf:textarea id="commentContent" path="content" cols="80" rows="4"/><br/>
    <sf:errors path="content" cssClass="errorMessage"/><br/>
    <input type="submit" value="<fmt:message key="project.comment.submit"/>"/><br class="clear"/>
</sf:form>


    <div id="commentList">
<c:if test="${!empty project.comments}">
    <c:forEach items="${project.comments}" var="com" varStatus="loop">
        <div class="comment">
        <fmt:formatDate value="${com.dateCreate}" pattern="dd. MM. yyyy" var="formatedDate"/>

        <h3><b>${com.subject}</b> (${com.author.name})</h3>
        <p>${com.content}</p>
        <h4 class="bottomLine">datum: ${formatedDate}</h4>
        </div>
        <c:if test="${loop.last}"><c:set value="${loop.index}" var="count" scope="page"/></c:if>
    </c:forEach>
    <h3><fmt:message key="project.comment.count"/>: ${count +1} </h3>
</c:if>
    <c:if test="${empty project.comments}"><h3><fmt:message key="project.comment.null"/>.</h3></c:if>
    </div>
</div>