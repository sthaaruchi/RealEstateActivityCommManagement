<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>

<div class="container">

 <div class="panel panel-primary">
  <div class="panel-heading">
   <h3>List of Events</h3>
  </div>
  <div class="panel-body">
   <table class="table table-striped">
    <thead>
     <tr>
      <th width="30%">Title</th>
      <th width="30%">Location</th>
      <th width="20%">Event Date</th>
      <th width="20%"></th>
     </tr>
    </thead>
    <tbody>
     <c:forEach items="${events}" var="event">
      <tr>
       <td><a href="/viewEvent?id=${event.eventId}">${event.title}</a></td>
       <td>${event.location}</td>
       <td><fmt:formatDate value="${event.eventDate}"
         pattern="dd/MM/yyyy" /></td>
       
      </tr>
     </c:forEach>
    </tbody>
   </table>
  </div>
 </div>

</div>
<%@ include file="common/footer.jspf"%>