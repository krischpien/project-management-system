<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>



<!--form-->
<label for="dateCreate">Vytvořeno:</label><sf:input path="dateCreate" id="dateCreate" readonly="true" cssClass="readonly"/><br/> <!--readOnly-->
<label for="dateDeadline">Termín:</label><sf:input path="dateDeadline" id="dateDeadline" readonly="true" cssClass="readonly"/><br/> <!--readOnly-->
<label for="projectContent">Obsah:</label><br/> <sf:textarea path="content" id="projectContent" cssClass="projectContent" readonly="true" /><br/> <!--readOnly-->
<label for="note">Poznámka:</label><sf:textarea path="note" readonly="true" cssClass="readonly"/><br/> <!--readOnly-->
<label for="budget">Rozpočet:</label><sf:input path="budget" id="budget" readonly="true" cssClass="readonly"/><br/><!--readOnly-->
<div class="fillingPart">
<sec:authorize ifAllGranted="ROLE_PROVIDER, ROLE_DEVELOPER">
    <input type="hidden" name="formAction" value="realise"/>
    <input type="submit" value="Odeslat k testování"/>
</sec:authorize>
    
<sec:authorize access="!hasRole('ROLE_PROVIDER') || !hasRole('ROLE_DEVELOPER')">
    <p>Čeká se na realizaci ze strany vývojářů dodavatele.</p>
</sec:authorize>    
</div>
<!--/form-->
