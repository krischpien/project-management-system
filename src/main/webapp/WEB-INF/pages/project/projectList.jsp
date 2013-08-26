<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<h1><fmt:message key="projects.header"/></h1>
<s:url value="/project/edit/newProject.do" var="newProjectUrl"/>
<c:if test="${empty projectList}">
    <p><fmt:message key="projects.null"/>.</p>
</c:if>

<ul class="simpleList">
    <c:forEach items="${projectList}" var="project">
        <li><span class="inline-icon ui-icon ui-icon-folder-collapsed"></span>
           <a href="<s:url value="/project/edit/edit.do?pid=${project.id}"/>">${project.name}</a>
           <sec:authorize ifAnyGranted="ROLE_ADMIN">
                <form action="<s:url value="/project/edit/deleteProject.do"/>" method="POST" class="miniActionForm" title="<fmt:message key="projects.action.delete"/> ${project.name}">
                    <input type="hidden" value="${project.id}" name="deletedProjectId"/>
                    <button type="submit"><span class="ui-icon ui-icon-trash"></span></button>
                </form>
            </sec:authorize>
                <form method="GET" action="<s:url value='/project/edit/edit.do'/>" class="miniActionForm" title="<fmt:message key="projects.action.edit"/> ${project.name}">
                    <input type="hidden" name="pid" value="${project.id}"/>
                    
                    <button type="submit"><span class="ui-icon ui-icon-pencil"></span></button>
                </form>
        </li>
    </c:forEach>
</ul>

<sec:authorize ifAnyGranted="ROLE_ADMIN">
    <a class="actionButton" href="${newProjectUrl}"><fmt:message key="projects.action.newProject"/></a>
</sec:authorize>

<script>
    $("button").button();
</script>