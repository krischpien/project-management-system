<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<h1>Výpis projektů</h1>
<ul>
<c:forEach items="${projectList}" var="project">
    <li><a href="<s:url value="/project/details/${project.id}-${project.name}"/>">${project.name}</a></li>
</c:forEach>
</ul>