<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<!--form-->
<label for="dateCreate">Vytvořeno:</label><sf:input path="dateCreate" id="dateCreate" readonly="true" cssClass="readonly"/><br/> <!--readOnly-->
<label for="dateDeadline">Termín:</label><sf:input path="dateDeadline" id="dateDeadline" readonly="true" cssClass="readonly"/><br/> <!--readOnly-->
<label for="projectContent">Obsah:</label><br/> <sf:textarea path="content" id="projectContent" cssClass="readonly" readonly="true"/><br/> <!--readOnly-->
<label for="note">Poznámka:</label><sf:textarea path="note" readonly="true" cssClass="readonly"/><br/> <!--readOnly-->
<label for="budget">Rozpočet:</label><sf:input path="budget" id="budget" cssClass="readonly" readonly="true"/><br/><!--readOnly-->

<div class="fillingPart">
<sec:authorize ifAllGranted="ROLE_PROVIDER, ROLE_MANAGER">
    
<!--    <input type="submit" name="formAction" value="Odeslat zpět k realizaci"/>
    <input type="submit" name="formAction" value="Zamítnutí reklamace"/>-->

<label for="formAction">Akce: </label>
<select name="formAction">
    <option value="reject_complaint">Zamítnout</option>    
    <option value="accept_complaint">Zpět k realizaci</option>    
</select>
<!--<br/>-->
    
<input type="submit" value="Potvrdit"/>

</sec:authorize>
    
<sec:authorize access="!hasRole('ROLE_PROVIDER') || !hasRole('ROLE_MANAGER')">
    <p>Čeká se na vyjádření k reklamaci od managera dodavatele.</p>
</sec:authorize> 
</div>
