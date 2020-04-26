<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>
<div class="container">
 <div class="row">
  <div class="col-md-6 col-md-offset-3 ">
   <div class="panel panel-primary">
    <div class="panel-heading">Event Details</div>
    <div class="panel-body">
       <p><b>Title:</b> ${event.title}</p><br>
       <p><b>Details:</b> ${event.details}</p><br>
       <p><b>Location:</b> ${event.location}</p><br>
       <p><b>Event Date:</b> ${event.eventDate}</p><br>
       <p><b>Event Duration:</b> ${event.duration}</p><br>
       <p><b>Invitation for Residents:</b></p>
       <ul>
       <c:forEach items="${event.buildings}" var="building" >
			<li>${building.name}</li>
		</c:forEach>
       </ul>
       <c:if test="${!event.residents.contains(user)}">  
       	<a href="/joinEvent?id=${event.eventId}"><button type="submit" class="btn btn-success" id="joinEvent">Join Event</button></a>
       </c:if>
       <c:if test="${event.residents.contains(user)}">  
       	<a href="/cancelJoinEvent?id=${event.eventId}"><button type="submit" class="btn btn-success" id="cancelJoinEvent">Cancel Join</button></a>
       </c:if>
    </div>
   </div>
  </div>
 </div>
 
 
 <div class="row">
 <div class="post-comments">

    <form action="/addComment?">
      <div class="form-group">
        <label for="comment">Your Comment</label>
        <textarea name="comment" class="form-control" rows="3"></textarea>
        <input name="eventId" type="hidden" value="${event.eventId}"/>
      </div>
      <button type="submit" class="btn btn-default" id="postComment">Post</button>
    </form>
   </div>
 </div>
 
   <div class="row">
   <c:forEach items="${event.comments}" var="comment" >
      <div class="media">
          <div class="media-body">
           
	           <p>Added by: ${comment.createdBy} | ${comment.creationDate}</p>
	           <form action="/updateComment?">
			      <div class="form-group">
			        <textarea name="comment" class="form-control" id="comment_${comment.commentId}" rows="3" disabled>${comment.comment}</textarea>
			        <input name="eventId" type="hidden" value="${event.eventId}"/>
			        <input name="commentId" type="hidden" value="${comment.commentId}"/>
			      </div>
			      <button type="submit" class="btn btn-success" id="button_${comment.commentId}" disabled>Save</button>
			      
			    </form>
			    <c:if test="${comment.createdBy == username}">
					<button class="btn btn-success" id="editButton" onClick="enableComment(${comment.commentId})">Edit</button>
		            <a href="/deleteComment?commentId=${comment.commentId}&eventId=${event.eventId}">
		              	<button class="btn btn-success" id="deleteComment">Delete</button>
		            </a>
           		</c:if>
          </div>
       </div>
    </c:forEach>
    </div>
   
    
</div>
	<br>
	<br>
<%@ include file="common/footer.jspf"%>