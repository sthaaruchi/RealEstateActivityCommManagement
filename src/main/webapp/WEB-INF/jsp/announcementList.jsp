<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>

<div class="container">
 <br>
 <div class="panel panel-primary">
  <div class="panel-heading">
   <h3>List of Announcements </h3>
  </div>
  <div class="panel-body">
   <table class="table table-striped">
    <thead>
     <tr>
      <th width="30%">Title</th>
      <th width="30%">Description</th>
      <th width="20%">Publish Date</th>
      <th width="20%"></th>
     </tr>
    </thead>
    <tbody>
     <c:forEach items="${announcements}" var="announcement">
      <tr>
       <td><a href="/viewAnnouncement?id=${announcement.announcementId}">${announcement.title}</a></td>
       <td>${announcement.description}</td>
       <td><fmt:formatDate value="${announcement.publishAnnouncementDate}"
         pattern="dd/MM/yyyy  hh/mm/ss" /></td>
       <td>
      </tr>
     </c:forEach>
    </tbody>
   </table>
  </div>
 </div>

</div>
<%@ include file="common/footer.jspf"%>