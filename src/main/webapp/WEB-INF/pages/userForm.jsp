<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%--<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>--%>


<div id="content">
    <s:url value="/user/save" var="actionUrl"/>
    
    <sf:form method="post" commandName="user" action="${actionUrl}">
        <sf:errors path="*" cssClass="errorsBox"/><br/>
        Jméno: <sf:input path="name"/><br/>
        Email: <sf:input path="email"/><br/>
        Heslo: <sf:password path="password"/><br/>
        <input type="submit" value="uložit"/>
    </sf:form>
        <p>${message}</p>
    
</div>