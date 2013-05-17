<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<h1>Projekty &raquo; Výpis projektů</h1>
<s:url value="/project/edit/newProject.do" var="newProjectUrl"/>
<c:if test="${empty projectList}">
    <p>Žádné projekty.</p>
</c:if>

<ul class="simpleList">
    <c:forEach items="${projectList}" var="project">
        <li><span class="inline-icon ui-icon ui-icon-folder-collapsed"></span>
           <a href="<s:url value="/project/details/${project.id}-${project.name}"/>">${project.name}</a>
           
                <form action="<s:url value="/project/edit/deleteProject.do"/>" method="POST" class="miniActionForm" title="Odstranit projekt ${project.name}">
                    <input type="hidden" value="${project.id}" name="deletedProjectId"/>
                    <button type="submit"><span class="ui-icon ui-icon-trash"></span></button>
                </form>
                
                <form method="GET" action="<s:url value='/project/edit/edit.do'/>" class="miniActionForm" title="Editovat projekt ${project.name}">
                    <input type="hidden" name="pid" value="${project.id}"/>
                    
                    <button type="submit"><span class="ui-icon ui-icon-pencil"></span></button>
                </form>
        </li>
    </c:forEach>
</ul>

<a class="actionButton" href="${newProjectUrl}">Nový projekt</a>

<script>
    $("button").button();
</script>