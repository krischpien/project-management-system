<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<h1>Formulář projektu</h1>
<sf:form commandName="project" method="POST" id="projectForm">
    <sf:hidden path="id"/>
    <sf:hidden path="dateCreate"/>
    <label for="phase">Fáze: </label><span class="phase"><c:if test="${empty project.phase}">Nový projekt</c:if>${project.phase.description}</span><br/>
    <label for="name">Název:</label><sf:input path="name"/><br/>
    <label for="content">Obsah:</label><br/>
    <sf:textarea path="content" id="projectContent" cssClass="projectContent"/><br/>
    
    <label for="budget">Rozpočet:</label><sf:input path="budget" id="budget"/><br/>
    
    
    <label for="dateDeadline">Termín:</label><sf:input path="dateDeadline" id="date" readonly="true"/><br/>
    <label for="note">Poznámka:</label><sf:textarea path="note"/><br/>
    <h3>Oprávnění k projektu:</h3>
    <div id="authorizedUsers">
        <sec:authorize access="hasRole('ROLE_ADMIN')">
            <sf:select path="authorizedUsers" items="${allUsers}" itemValue="id" itemLabel="name" />
        </sec:authorize>
        <sec:authorize ifNotGranted="ROLE_ADMIN">
            <c:forEach items="${project.authorizedUsers}" var="user" varStatus="loop">
                <p title="${user.email}">${user.name}</p>
            </c:forEach>
        </sec:authorize>
    </div>
    <c:set value="${project.phase.name}" var="projectPhase"/>
    
    <sec:authorize ifAnyGranted="ROLE_ADMIN">
        <input type="submit" name="formAction" value="Uložit"/>
    </sec:authorize>
        
    <sec:authorize access="hasRole('ROLE_OWNER')">
        <c:if test="${projectPhase == 'PHASE_NEW'}">
            <input type="submit" name="formAction" value="Zadat"/>
        </c:if>
    </sec:authorize>
        
    <sec:authorize ifAllGranted="ROLE_PROVIDER, ROLE_MANAGER">
        <c:if test="${projectPhase == 'PHASE_PLACED'}">
            <input type="submit" name="formAction" value="Ocenit"/>
        </c:if>
        <c:if test="${projectPhase == 'PHASE_APPROVED'}">
            <input type="submit" name="formAction" value="Realizace" title="Odeslat projekt k realizaci vývojářům."/>
        </c:if>
        <c:if test="${projectPhase == 'PHASE_TESTED'}">
            <input type="submit" name="formAction" value="Nasadit" title="Nasadit projekt do provozu."/>
        </c:if>
    </sec:authorize>
    
    <sec:authorize ifAllGranted="ROLE_CUSTOMER, ROLE_MANAGER">
        <c:if test="${projectPhase == 'PHASE_APPRAISED'}">
            <input type="submit" name="formAction" value="Schválit"/>
            <input type="submit" name="formAction" value="Neschválit"/>
        </c:if>
    </sec:authorize>    
    
    <sec:authorize ifAllGranted="ROLE_PROVIDER, ROLE_DEVELOPER">
        <c:if test="${projectPhase == 'PHASE_REALISATION'}">
            <input type="submit" name="formAction" value="Otestovat" title="Odeslat projekt k testování."/>
        </c:if>
    </sec:authorize>
        
    <sec:authorize access="hasRole('ROLE_TESTER')">
        <sec:authorize access="hasRole('ROLE_DEVELOPER')">
            <input type="submit" name="formAction" value="Otestováno" title="Označit projekt jako otestovaný"/>
        </sec:authorize>
            <input type="submit" name="formAction" value="Nahlásit chybu"/>
    </sec:authorize>
        
</sf:form>
<script>
$(function() {
$( "#date" ).datepicker({
    dateFormat: "mm-dd-yy"
    });
});

</script>