<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1><b>500</b> - Chyba při zpracování požadavku</h1>
<p><span class="inline-icon ui-icon ui-icon-alert red"></span>Při zpracování požadavku došlo k chybě.</p>
<p>V případě přetrvávání problému kontaktuje administrátora.</p>
<sec:authorize access="hasRole('ROLE_ADMIN')">
    <h2>CHYBA: ${pageContext.exception.message}</h2>
    <h3>Výpis stacku:</h3>
    <ul>
        <c:forEach items="${pageContext.exception.stackTrace}" var="trace">
            <li>${trace}</li>
        </c:forEach>
    </ul>
</sec:authorize>