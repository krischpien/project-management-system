<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<c:set var="_projectPhase" value="${project.phase.id}"/>

<h1>Formulář projektu</h1>

<sf:form commandName="project" method="POST" id="projectForm">
    <sf:hidden path="id"/>
    <sf:hidden path="dateCreate"/>
    <p>Fáze: <span>${project.phase.description}</span></p>
    
    
     <c:choose>
        <c:when test="${_projectPhase == 2}">
            <!--placed-->
            <tiles:insertAttribute name="appraise"/>
        </c:when>
            
        <c:when test="${_projectPhase == 3}">
            <!--appraised-->
        </c:when>
        
        <c:when test="${_projectPhase == 4}">
            <!--approved-->
        </c:when>
            
        <c:when test="${_projectPhase == 5}">
            <!--realisation-->
        </c:when>
        
        <c:when test="${_projectPhase == 6}">
            <!--testing-->
        </c:when>
        
        <c:when test="${_projectPhase == 7}">
            <!--tested-->
        </c:when>
        
        <c:when test="${_projectPhase == 8}">
            <!--deployed-->
        </c:when>
        
        <c:when test="${_projectPhase == 9}">
            <!--complaint-->
        </c:when>
    </c:choose>    
    
    <sec:authorize access="hasRole('ROLE_ADMIN')">
        <c:if test="${projetPhase == null}">
            <tiles:insertAttribute name="create"/>
        </c:if>
    </sec:authorize>

    
    <sec:authorize access="hasRole('ROLE_OWNER')">
        <c:if test="${projectPhase == 'PHASE_NEW'}">
            <tiles:insertAttribute name="place"/>
        </c:if>
    </sec:authorize>

    
        
    <sec:authorize ifAllGranted="ROLE_PROVIDER, ROLE_MANAGER">
<!--FÁZE ZADÁNO ROLE:[PROVIDER+MANAGER]-->                
        <c:if test="${projectPhase == 'PHASE_PLACED'}">
            <tiles:insertAttribute name="appraise"/>
        </c:if>
<!--FÁZE SCHVÁLENO ROLE:[PROVIDER+MANAGER]-->                
        <c:if test="${projectPhase == 'PHASE_APPROVED'}">
            <tiles:insertAttribute name="realise"/>
        </c:if>
<!--FÁZE OTESTOVÁNO ROLE:[PROVIDER+MANAGER]-->                
        <c:if test="${projectPhase == 'PHASE_TESTED'}">
            <tiles:insertAttribute name="deploy"/>
        </c:if>
    </sec:authorize>

<!--FÁZE OCENĚNO ROLE:[PROVIDER+DEVELOPER]-->        
    <c:if test="${projectPhase == 'PHASE_APPRAISED'}">
        <sec:authorize ifAllGranted="ROLE_CUSTOMER, ROLE_MANAGER">
            <tiles:insertAttribute name="approve"/>
        </sec:authorize>    
    </c:if>
            
<!--FÁZE REALIZACE ROLE:[PROVIDER+DEVELOPER]-->    
    <c:if test="${projectPhase == 'PHASE_REALISATION'}">
        <sec:authorize ifAllGranted="ROLE_PROVIDER, ROLE_DEVELOPER">
            <tiles:insertAttribute name="test"/>
        </sec:authorize>
    </c:if>
        
<!--FÁZE TESTOVÁNÍ; ROLE:[TESTER+DEVELOPER]-->
    <c:if test="${projectPhase == 'PHASE_TESTING'}">
        <sec:authorize access="hasRole('ROLE_TESTER')">
            <sec:authorize access="hasRole('ROLE_DEVELOPER')">
                <tiles:insertAttribute name="tested"/>
            </sec:authorize>
        </sec:authorize>
    </c:if>

    <sec:authorize access="hasRole('ROLE_ADMIN')">
        <h3>Oprávnění na projekt</h3>
        <sf:select path="authorizedUsers" items="${allUsers}" itemValue="id" itemLabel="name" /><br/>
    </sec:authorize>
    <sec:authorize ifNotGranted="ROLE_ADMIN">
        <c:forEach items="${project.authorizedUsers}" var="user" varStatus="loop">
            <p title="${user.email}">${user.name}</p>
        </c:forEach>
    </sec:authorize>

        
</sf:form>
    
<script>
$(function() {
$( "#date" ).datepicker({
    dateFormat: "mm-dd-yy"
    });
});
</script>
