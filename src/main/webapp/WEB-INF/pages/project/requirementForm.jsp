<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>

<sf:form commandName="requirement" method="POST">
    <sf:hidden path="id"/>
    Název: <sf:input path="name"/><br/>
    Obsah: <sf:textarea path="content" id="requirementContent"/><br/>
    <input type="submit" value="Uložit"/>
</sf:form>

