<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>
<div class="container">
 <div class="row">
  <div class="col-md-6 col-md-offset-3 ">
   <div class="panel panel-primary">
    <div class="panel-heading"><fmt:message key="label.addevent" /></div>
    <div class="panel-body">
     <form:form method="post" modelAttribute="event">
      <form:hidden path="eventId" />
      <fieldset class="form-group">
       <form:label path="title">Title</form:label>
       <form:input path="title" type="text" class="form-control"
        required="required" />
       
      </fieldset>
      
      <fieldset class="form-group">
       <form:label path="details">Event Details</form:label>
       <form:input path="details" type="text" class="form-control"
        required="required" />
       
      </fieldset>
      
      <fieldset class="form-group">
       <form:label path="location">Event Location</form:label>
       <form:input path="location" type="text" class="form-control"
        required="required" />
       
      </fieldset>
      
      <fieldset class="form-group">
       <form:label path="duration">Event Duration</form:label>
       <form:input path="duration" type="text" class="form-control"
        required="required" />
       
      </fieldset>

      <fieldset class="form-group">
       <form:label path="eventDate">Event Date</form:label>
       <form:input path="eventDate" type="text" class="form-control"
        required="required" />

      </fieldset>
      
      <fieldset class="form-group">
       <form:label path="eventPublishDate">Date to publish event</form:label>
       <form:input path="eventPublishDate" type="text" class="form-control"
        required="required" />

      </fieldset>

      <button type="submit" class="btn btn-success">Save</button>
     </form:form>
    </div>
   </div>
  </div>
 </div>
</div>
<%@ include file="common/footer.jspf"%>