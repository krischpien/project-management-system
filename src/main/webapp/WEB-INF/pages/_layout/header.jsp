<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<sec:authorize access="isAuthenticated()">
    <sec:authentication property="principal.username" var="username" scope="request"/>
    <sec:authentication property="authorities" var="authorities" scope="request"/>
</sec:authorize>

<div id="header">
    <div id="languages"><a href="<s:url value=""><s:param name="lang" value="cs_CZ"/></s:url>" class="cs"></a><a href="?lang=en_GB" class="en"></a></div>
    <div id="appInfo"><p><span>Project Management</span> System</p></div>
    
    <div id="principalInfo">
    <sec:authorize access="isAuthenticated()">
        
            <span class="inline-icon ui-icon ui-icon-person white"></span>
            <b>${username}</b> 
            (<a href="<s:url value="/logout"/>" title="odhlásit uživatele"><fmt:message key="menu.signout"/></a>) 
            <br/>
            <span class="inline-icon ui-icon ui-icon-calculator white"></span>
            ${sessionScope.lastIp}

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
    
    
        
    <br class="clear"/>
    
    <div id="menu">
        <ul>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
            <li><a href="<s:url value="/admin/index"/>" ${activePage == "admin" ? "class='active'" :""} style="color:red; font-weight: bold;"><fmt:message key="menu.system"/></a></li>
            <li><a href="<s:url value="/admin/user/"/>" ${activePage == "users" ? "class='active'" :""} style="color:red; font-weight: bold;"><fmt:message key="menu.users"/></a></li>
            </sec:authorize>
            
            <li><a href="<s:url value="/"/>" ${activePage == "index" ? "class='active'" :""}><fmt:message key="menu.index"/></a></li>
            <li><a href="<s:url value="/project"/>" ${activePage == "projects" ? "class='active'" :""}><fmt:message key="menu.projects"/></a></li>
            <li><a href="<s:url value="/info"/>" ${activePage == "info" ? "class='active'" :""}><fmt:message key="menu.info"/></a></li>
            
        </ul>
        
    </div>
        <div id="infoZone">
            <a class="notice comNotice" title="${commentCount} <fmt:message key="info.comments"/>">${commentCount}</a>
            <a class="notice prjNotice" title="${projectCount} <fmt:message key="info.projects"/>">${projectCount}</a>
            <a class="notice reqNotice" title="${requirementCount} <fmt:message key="info.requirements"/>">${requirementCount}</a>
        </div>
        <br class="clear"/>
</div>
                        