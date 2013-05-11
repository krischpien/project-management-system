<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1>Formulář projektu</h1>
<sf:form commandName="project" method="POST" id="projectForm">
    <sf:hidden path="id"/>
    <label for="phase">Fáze: </label><span class="phase"><c:if test="${empty project.phase}">Nový projekt</c:if>${project.phase.name}</span><br/>
    <label for="name">Název:</label><sf:input path="name"/><br/>
    <label for="content">Obsah:</label><br/>
    <sf:textarea path="content" id="projectContent" cssClass="projectContent"/><br/>
    <div id="budgetSlider">
    <label for="budget">Rozpočet:</label><sf:input path="budget" id="budget"/><br/>
    </div>
    
    <label for="dateDeadline">Termín:</label><sf:input path="dateDeadline" id="date" readonly="true"/><br/>
    <label for="note">Poznámka:</label><sf:textarea path="note"/><br/>
    <h2>Oprávnění k projektu:</h2>
    <div id="authorizedUsers">
        <sf:select path="authorizedUsers" items="${allUsers}" itemValue="id" itemLabel="name" />
    </div>
    <input type="submit" value="potvrdit"/>
</sf:form>
    
<script>
$(function() {
$( "#date" ).datepicker({
    dateFormat: "mm-dd-yy"
    });
});

</script>