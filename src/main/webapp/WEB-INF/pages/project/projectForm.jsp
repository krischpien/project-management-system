<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<h1>Formulář projektu</h1>
<sf:form commandName="project" method="POST" id="projectForm">
    <sf:hidden path="id"/>
    <label for="phase">Fáze: </label><span class="phase"><c:if test="${empty project.phase}">Nový projekt</c:if>${project.phase.description}</span><br/>
    <label for="name">Název:</label><sf:input path="name"/><br/>
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
    
    <sec:authorize ifAnyGranted="ROLE_ADMIN, ROLE_OWNER">
        <input type="submit" name="formAction" value="Uložit"/>
    </sec:authorize>
</sf:form>
