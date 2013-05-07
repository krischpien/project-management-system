<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>--%>


<div id="content">
    <s:url value="" var="actionUrl"/>
    
    <sf:form method="post" commandName="user" action="${actionUrl}">
        
        <div id="mainRoles">
        <sf:select path="roles">
            <sf:options items="${mainRoles}" itemValue="id" itemLabel="description"/>
        </sf:select>
        </div>
        <sf:hidden path="id"/>
        Jméno: <sf:input path="name"/><br/>
        Email: <sf:input path="email"/><br/>
        Heslo: <sf:password path="password"/><br/>
        
        Role:  
        <div id="assignableRoles">
        <sf:checkboxes path="roles" items="${assignableRoles}" itemValue="id" itemLabel="description"/>
        </div>
        <br/>

        <input type="submit" value="uložit"/><br/>
        <sf:errors path="*" cssClass="errorsBox" /><br/>
    </sf:form>
        
    
</div>