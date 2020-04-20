<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>

<div class="container">
	<div class="container">
		<div class="col-md-7 col-md-offset-1 ">
			<input class="form-control mb-12" id="tableSearchForEvent" type="text"
				placeholder="search event">
		</div>
		<c:if test="${role == 'ROLE_MANAGER'}">
			<a type="button" class="btn btn-primary btn-md" href="/addEvent">Create
				New Event</a>
		</c:if>
	</div>
	<br>
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
				<tbody id="eventTable">
					<c:forEach items="${events}" var="event">
						<tr>
							<td>${event.title}</td>
							<td>${event.location}</td>
							<td><fmt:formatDate value="${event.eventDate}"
									pattern="dd/MM/yyyy" /></td>
							<c:if test="${role == 'ROLE_MANAGER'}">
								<td><a type="button" class="btn btn-success"
									href="/updateEvent?id=${event.eventId}">Update</a> <a
									type="button" class="btn btn-warning"
									href="/deleteEvent?id=${event.eventId}">Delete</a></td>
							</c:if>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>

</div>
<%@ include file="common/footer.jspf"%>