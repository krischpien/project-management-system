<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<label for="name">Název:</label><sf:input path="name" readonly="true"/><br/>
<label for="dateDeadline">Termín:</label><sf:input path="dateDeadline" id="date" readonly="true"/><br/>
<label for="content">Obsah:</label><br/>
<sf:textarea path="content" id="projectContent" cssClass="projectContent"/><br/>
<label for="note">Poznámka:</label><sf:textarea path="note"/><br/>
<input type="submit" name="formAction" value="Odeslat"/>
