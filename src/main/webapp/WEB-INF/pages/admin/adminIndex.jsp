<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>


<h1>Administrace systému</h1>
<div id="services">
<h2>Nastavení služeb:</h2>
<div class="service">
    <h3>Notifikace mailem:
    <c:choose>
        <c:when test="${mailActive}"><span class="on">zapnuto</span></c:when>
        <c:otherwise><span class="off">vypnuto</span></c:otherwise>
    </c:choose> </h3>
    <a href="<s:url value="/admin/service/mail/on"/>">zapnout</a> | <a href="<s:url value="/admin/service/mail/off"/>">vypnout</a>
</div>


<div class="service">
<h3>Upozorňování na nesplacené zálohy:
    <c:choose>
        <c:when test="${cronActive}"><span class="on">zapnuto</span></c:when>
        <c:otherwise><span class="off">vypnuto</span></c:otherwise>
    </c:choose> 
        </h3>
    <a href="<s:url value="/admin/service/cron/on"/>">zapnout</a> | <a href="<s:url value="/admin/service/cron/off"/>">vypnout</a>
</div>


</div>

    <c:if test="${!empty loggingEvents}">
        <ul>
        <c:forEach items="${loggingEvents}" var="loggingEvent">
            <li title="${loggingEvent.loggerName}">${loggingEvent.timestmp} : ${loggingEvent.formattedMessage}</li>
        </c:forEach>
        </ul>
    </c:if>