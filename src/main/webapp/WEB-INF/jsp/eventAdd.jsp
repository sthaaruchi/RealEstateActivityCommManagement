<%@ include file="common/header.jspf"%>
<%@ include file="common/navigationEvent.jspf"%>
<div class="container">
 <div class="row">
  <div class="col-md-6 col-md-offset-3 ">
   <div class="panel panel-primary">
    <div class="panel-heading"><fmt:message key="label.addevent" /></div>
    <div class="panel-body">
     <form:form method="post" modelAttribute="event">
      <form:hidden path="eventId" />
      <form:hidden path="comments" />
      <form:hidden path="announcements" />
      <fieldset class="form-group">
       <form:label path="title"><fmt:message key="label.event.title" /></form:label>
       <form:input path="title" type="text" class="form-control"
        required="required" />
       
      </fieldset>
      
      <fieldset class="form-group">
       <form:label path="details"><fmt:message key="label.event.details" /></form:label>
       <form:input path="details" type="text" class="form-control"
        required="required" />
       
      </fieldset>
      
      <fieldset class="form-group">
       <form:label path="location"><fmt:message key="label.event.location" /></form:label>
       <form:input path="location" type="text" class="form-control"
        required="required" />
       
      </fieldset>
      
      <fieldset class="form-group">
       <form:label path="duration"><fmt:message key="label.event.duration" /></form:label>
       <form:input path="duration" type="text" class="form-control"
        required="required" />
       
      </fieldset>

      <fieldset class="form-group">
       <form:label path="eventDate"><fmt:message key="label.event.eventdate" /></form:label>
       <form:input path="eventDate" type="datetime-local" class="form-control"
        required="required" />

      </fieldset>
      
      <fieldset class="form-group">
       <form:label path="eventPublishDate">Date to publish event</form:label>
       <form:input path="eventPublishDate" type="datetime-local" class="form-control"
        required="required" />

      </fieldset>
      
      <fieldset class="form-group">
       <form:label path="buildings">Invitation for Residents:</form:label>
	  		<form:select path="buildings" class="multiselect-ui form-control" multiple="multiple">
				<c:forEach items="${allBuildings}" var="building" >
					<c:if test="${event.buildings.contains(building)}">
						<form:option value="${building.buildingId}" id="${building.buildingId}" selected="selected">${building.name}</form:option>
					</c:if>
					<c:if test="${!event.buildings.contains(building)}">
						<form:option value="${building.buildingId}" id="${building.buildingId}">${building.name}</form:option>
					</c:if>
					
				</c:forEach>
			</form:select>
	  </fieldset>

      <button type="submit" class="btn btn-success" id="saveEvent">Save</button>
     </form:form>
    </div>
   </div>
  </div>
 </div>
</div>
	<br>
	<br>
<%@ include file="common/footer.jspf"%>