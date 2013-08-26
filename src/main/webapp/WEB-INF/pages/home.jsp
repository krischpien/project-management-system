<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<h1><fmt:message key="index.header"/></h1>


    <fmt:formatDate value="${sessionScope.lastLogin}" pattern="dd. MM. yyyy" var="formatedLastDate"/>
    <fmt:formatDate value="${sessionScope.lastLogin}" pattern="HH:mm" var="formatedLastTime"/>
    <p><fmt:message key="index.loggedUser"/>: <b>${username}</b> </p>
    <p><fmt:message key="index.userRoles"/>:</p>
    <p>
        <c:forEach items="${authorities}" var="authority" varStatus="loop">
            <em>${authority}${loop.last? '':','}</em>
        </c:forEach>
    </p>
    <p><fmt:message key="index.lastLoginDate"/>: <b title="${formatedLastTime}">${formatedLastDate}</b>, <fmt:message key="index.lastLoginIp"/> <b>${sessionScope.lastIp}</b></p>


    <div id="eventsArea">
<h2><fmt:message key="index.events"/>:</h2>
    
    <c:if test="${empty events}">
        <p><fmt:message key="index.events.null"/></p>
    </c:if>
    <c:if test="${!empty events}">
    <table id="events">
        <thead>
            <tr>
                <th><fmt:message key="index.events.date"/></th>
                <th><fmt:message key="index.events.message"/></th>
                <th>&nbsp;</th>
            </tr>
        </thead>
    <c:forEach items="${events}" var="event">
        <tr class="row">
        <fmt:formatDate value="${event.dateEvent}" pattern="dd. MM. yyyy (HH:mm)" var="formatedEventDate"/>
        <td>${formatedEventDate}</td>
        <td><a href="<s:url value="${event.link}"/>"/>${event.description}</a></td>
        <td class="action"><a href="<s:url value="ajax/readEvent"/>" class="read" id="${event.id}"><fmt:message key="index.events.action.read"/></a></td>
        </tr>
    </c:forEach>
    </table>
        <a id="readAllEvents" href="<s:url value="/ajax/readAllEvents"/>"><fmt:message key="index.events.action.readAll"/></a>
    </div>
    </c:if>
    
    <script>
        $("#events a.read").click(function(e){
            e.preventDefault();
            var eventId = $(this).attr("id");
            var parentRow = $(this).parent().parent();
            $.ajax({
            type: "POST",
            url: "<s:url value="/ajax/readEvent"/>",
            data:"eventId="+eventId,
            success: function(response){
                parentRow.remove();
            },
            error: function(e){
                alert("Ajax error: " + e.message)
            }
        });
        })
        
        $("a#readAllEvents").click(function(e){
            e.preventDefault();
            var tableContent = $("table#events");
            var thisAnchor = $(this);
            $.ajax({
            type: "POST",
            url: "<s:url value="/ajax/readAllEvents"/>",
            success: function(response){
                tableContent.remove();
                thisAnchor.remove();
                $("#eventsArea").html("<h2><fmt:message key="index.events"/>:</h2><fmt:message key="index.events.null"/>")
            },
            error: function(e){
                alert("Ajax error: " + e.message)
            }
        });
        })
        
    </script>