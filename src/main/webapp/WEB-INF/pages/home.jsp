<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%--<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>--%>


<div id="content">
    <s:url value="/admin" var="adminUrl"/>
    <s:url value="/admin/test" var="adminTestUrl"/>
    
    <a href="${adminUrl}">admin</a>
    <a href="${adminTestUrl}">admin test</a>
    <fmt:formatDate value="${sessionScope.lastLogin}" pattern="dd. MM., yyyy" var="formatedLastDate"/>
    <p>Last login date: ${formatedLastDate}</p>
    <p>Last ip: ${sessionScope.lastIp}</p>
</div>

