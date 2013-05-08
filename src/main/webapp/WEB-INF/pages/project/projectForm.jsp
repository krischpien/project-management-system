<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<h1>Formulář projektu</h1>
<sf:form commandName="project" method="POST">
    <sf:hidden path="id"/>
    Název: <sf:input path="name"/><br/>
    Obsah: <sf:textarea path="content"/><br/>
    <input type="submit" value="potvrdit"/>
</sf:form>