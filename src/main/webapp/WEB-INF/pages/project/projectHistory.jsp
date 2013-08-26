<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:forEach items="${projectHistory}" var="history">
    <p>${history.dateChange}:  ${history.note}</p>
</c:forEach>