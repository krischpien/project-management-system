<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<h1>Index</h1>

    <fmt:formatDate value="${sessionScope.lastLogin}" pattern="dd. MM. yyyy" var="formatedLastDate"/>
    <p>Přihlášený uživatel <b>${username}</b> </p>
    <p>Uživatelské role:</p>
    <p>
        <c:forEach items="${authorities}" var="authority" varStatus="loop">
            <em>${authority}${loop.last? '':','}</em>
        </c:forEach>
    </p>
    <p>Poslední přihlášení proběhlo: <b>${formatedLastDate}</b>, z IP adresy <b>${sessionScope.lastIp}</b></p>

    <div id="eventsArea">
        
    <h2>Události:</h2>
    <c:if test="${empty events}">
        <p>Žádné nové události</p>
    </c:if>
    <c:if test="${!empty events}">
    <table id="events">
        <thead>
            <tr>
                <th>Datum</th>
                <th>Zpráva</th>
                <th>&nbsp;</th>
            </tr>
        </thead>
    <c:forEach items="${events}" var="event">
        <tr class="row">
        <fmt:formatDate value="${event.dateEvent}" pattern="dd. MM. yyyy (HH:mm)" var="formatedEventDate"/>
        <td>${formatedEventDate}</td>
        <td><a href="<s:url value="${event.link}"/>" title="Přejít k události"/>${event.description}</a></td>
        <td><a href="<s:url value="ajax/readEvent"/>" class="read" id="${event.id}" title="Odstranit oznámení">odstranit</a></td>
        </tr>
    </c:forEach>
    </table>
        <a id="readAllEvents" href="<s:url value="/ajax/readAllEvents"/>" title="Odstranit všechna oznámení">odstranit vše</a>
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
                alert("Ajax chyba: " + e.message)
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
                $("#eventsArea").html("<p>Žádné nové události</p>")
            },
            error: function(e){
                alert("Ajax chyba: " + e.message)
            }
        });
        })
        
    </script>