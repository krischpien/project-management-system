<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>



<!--form-->
<label for="dateCreate">Vytvořeno:</label><sf:input path="dateCreate" id="dateCreate" readonly="true" cssClass="readonly"/><br/> <!--readOnly-->
<label for="dateDeadline">Termín:</label><sf:input path="dateDeadline" id="dateDeadline" readonly="true" cssClass="readonly"/><br/> <!--readOnly-->
<label for="projectContent">Obsah:</label><br/> <sf:textarea path="content" id="projectContent" cssClass="projectContent" readonly="true" /><br/> <!--readOnly-->
<label for="note">Poznámka:</label><sf:textarea path="note" readonly="true" cssClass="readonly"/><br/> <!--readOnly-->
<div class="fillingPart">
    <label for="budget">Rozpočet:</label><sf:input path="budget" id="budget" readonly="true" cssClass="readonly"/><br/>

<sec:authorize ifAllGranted="ROLE_CUSTOMER, ROLE_MANAGER">
    <select name="formAction">
        <option value="approve">Schválit</option>
        <option value="reprice">Vrátit k přecenění</option>
    </select>
    <input type="submit" value="Potvrdit"/>
</sec:authorize>
   
<sec:authorize access="!hasRole('ROLE_CUSTOMER') || !hasRole('ROLE_MANAGER')">
    <p>Čeká se na vyjádření (potvrzení) k ceně od managera ze strany zákazníka.</p>
</sec:authorize>    
</div>
<!--/form-->
