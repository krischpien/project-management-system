<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<h1>Projekty</h1>
<s:url value="/project/edit/newProject.do" var="newProjectUrl"/>
<s:url value="/project/list" var="projectListUrl"/>

<a href="${newProjectUrl}">Nový projekt</a><br/>
<a href="${projectListUrl}">Výpis projektů</a><br/>