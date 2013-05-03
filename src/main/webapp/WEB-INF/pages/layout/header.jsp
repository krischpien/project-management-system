<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authorize access="isAuthenticated()">
    <sec:authentication property="principal.username" var="username"/>
    <sec:authentication property="authorities" var="authorities"/>
</sec:authorize>

<div id="header">
    <div id="app"><p>Project Management System</p><img src="<s:url value="/resources/img/logo_vsmie_small.gif"/>" alt="logo"/></div>
    <div id="userInfo">
        Přihlášen uživatel <b>${username}</b> (<a href="<s:url value="/logout"/>" title="odhlásit uživatele">odhlásit</a>) 
       <sec:authorize access="hasRole('ROLE_ADMIN')">
        <div id="adminPanel">
            <a href="<s:url value="/admin/uzivatel/list"/>">Uživatelé</a>            
            <a href="aaa">Log</a>
        </div>
        </sec:authorize>
        <div id="userRoles">
            <p>Role:</p>
            <ul>
            <c:forEach items="${authorities}" var="a">
                <li>${a}</li>
            </c:forEach>
            </ul>
        </div>
        
    </div>
    <div class="clearer"></div>
    <div id="menu" class="pageEdge">
        <ul>
            <li><a href="<s:url value="/"/>" ${page == "a"? "class='active'" :""}>Domů</a></li>
            <li><a href="<s:url value="/roleList"/>">Výpis rolí</a></li>
            <li><a href="<s:url value="/userList"/>">Výpis uživatelů</a></li>
            <li><a href="<s:url value="/user/new"/>">Vytvoř uživatele</a></li>
            
            <li><a href="<s:url value="/restricted" />" ${page == "b"? "class='active'" :""}>Události</a></li>
            <li><a href="<s:url value="/projekt/list"/>">Projekty</a></li>
            <li><a href="">Informace</a></li>
        </ul>
    </div>
</div>