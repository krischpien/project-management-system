<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<sec:authorize access="isAuthenticated()">
    <sec:authentication property="principal.username" var="username"/>
    <sec:authentication property="authorities" var="authorities"/>
</sec:authorize>

<div id="header">
    <div id="appInfo"><p><span>Project Management</span> System</p></div>
    
    <div id="principalInfo">
    <sec:authorize access="isAuthenticated()">
        
            <span class="inline-icon ui-icon ui-icon-person white"></span>
            <b>${username}</b> 
            (<a href="<s:url value="/logout"/>" title="odhlásit uživatele">odhlásit</a>) 
            <br/>
            <span class="inline-icon ui-icon ui-icon-calculator white"></span>
            ${sessionScope.lastIp}
           <sec:authorize access="hasRole('ROLE_ADMIN')">
               <div id="adminPanel" style="display:none">
                <a href="<s:url value="/admin/uzivatel/list"/>">Uživatelé</a>            
                <a href="aaa">Log</a>
            </div>
            </sec:authorize>
            <div id="userRoles" style="display:none">
                <p>Role:</p>
                <ul>
                <c:forEach items="${authorities}" var="a">
                    <li>${a}</li>
                </c:forEach>
                </ul>
            </div>
    </sec:authorize>
            <sec:authorize access="isAnonymous()"><p><a href="<s:url value="/loginout"/>">Přihlásit se</a></p></sec:authorize>
        </div>
    
        <s:url value="/admin" var="adminUrl"/>
    <s:url value="/admin/test" var="adminTestUrl"/>
    
        
    <br class="clear"/>
    
    <div id="menu">
        <ul>
            <li><a href="<s:url value="/admin/index"/>" ${activePage == "admin" ? "class='active'" :""} style="color:red;">ADMIN</a></li>
            <li><a href="<s:url value="/"/>" ${activePage == "index" ? "class='active'" :""}>Index</a></li>
            <li><a href="<s:url value="/roleList"/>">Výpis rolí</a></li>
            <li><a href="<s:url value="/admin/user/"/>">Uživatelé</a></li>
            <li><a href="<s:url value="/project"/>" ${activePage == "projects" ? "class='active'" :""}>Projekty</a></li>
            <li><a href="<s:url value="/info"/>" ${activePage == "info" ? "class='active'" :""}>Informace</a></li>
        </ul>
        
    </div>
</div>
            