<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<!--form-->
<label for="dateCreate">Vytvořeno:</label><sf:input path="dateCreate" id="dateCreate" readonly="true" cssClass="readonly"/><br/> <!--readOnly-->
<label for="dateDeadline">Termín:</label><sf:input path="dateDeadline" id="dateDeadline" readonly="true" cssClass="readonly"/><br/> <!--readOnly-->
<label for="projectContent">Obsah:</label><br/> <sf:textarea path="content" id="projectContent" cssClass="readonly" readonly="true"/><br/> <!--readOnly-->
<label for="note">Poznámka:</label><sf:textarea path="note"/><br/> <!--readOnly-->
<label for="budget">Rozpočet:</label><sf:input path="budget" id="budget" readonly="true" cssClass="readonly" /><br/><!--readOnly-->

<div class="fillingPart">
<sec:authorize ifAllGranted="ROLE_PROVIDER, ROLE_MANAGER">
<label for="formAction">Akce: </label>
    <select name="formAction" id="formAction">
        <option value="deploy">Nasadit</option>
        <option value="deploy_to_realised" class="noteNeeded">Poslat zpět k testování</option>
        <option value="deploy_to_approved" class="noteNeeded">Poslat zpět k přepracování</option>
    </select>
<!--<br/>-->
    <input type="submit" value="Potvrdit"/>
</sec:authorize>
    
<sec:authorize access="!hasRole('ROLE_PROVIDER') || !hasRole('ROLE_MANAGER')">
    <p>Čeká se na schválení nasazení od managera ze strany dodavatele.</p>
</sec:authorize>    
</div>
<!--/form-->


