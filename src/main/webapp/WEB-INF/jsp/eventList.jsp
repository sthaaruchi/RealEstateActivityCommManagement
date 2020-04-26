<%@ include file="common/header.jspf"%>
<%@ include file="common/navigationEvent.jspf"%>

<div class="container">
	<input class="form-control mb-3" id="tableSearchForEvent" type="text"
		placeholder="Search event"> <br>
	<div class="panel panel-primary">
		<div class="panel-heading">
			<h3>List of Events</h3>
		</div>
		<div class="panel-body">
			<table class="table table-striped">
				<thead>
					<tr>
						<th width="20%">Title</th>
						<th width="20%">Location</th>
						<th width="20%">Event Date</th>
						<th width="20%">Duration</th>
						<th width="20%"></th>
					</tr>
				</thead>
				<tbody id="eventTable">
					<c:forEach items="${events}" var="event">
						<tr>
							<td><a href="/viewEvent?id=${event.eventId}">${event.title}</a></td>
							<td>${event.location}</td>
							<td><fmt:formatDate value="${event.eventDate}"
									pattern="dd/MM/yyyy HH:mm" /></td>
							<td>${event.duration}</td>

						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>

</div>
<%@ include file="common/footer.jspf"%>