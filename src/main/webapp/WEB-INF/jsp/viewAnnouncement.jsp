<%@ include file="common/header.jspf"%>
<%@ include file="common/navigationAnnouncement.jspf"%>
<div class="container">
	<div class="row">
		<div class="col-md-6 col-md-offset-3 ">
			<div class="panel panel-primary">
				<div class="panel-heading">Announcement Details</div>
				<div class="panel-body">
					<p>
						<b>Title:</b> ${announcement.title}
					</p>
					<br>
					<p>
						<b>Description:</b> ${announcement.description}
					</p>
					<br>
					<p>
						<b>Publish Date:</b> ${announcement.publishAnnouncementDate}
					</p>
					<br>
					<c:if test="${announcement.event.eventId !=null }">
						<p>
							<b>Announcement for Event</b>
						</p>
						<ul>
							See Event
							<a href="/viewEvent?id=${announcement.event.eventId}">${announcement.event.title }</a>
						</ul>
						<br>
					</c:if>
					<c:if test="${announcement.event.eventId ==null }">
						<p>
							<b>Announcement for User Group:</b>
						</p>
						<ul>
							<c:choose>
								<c:when test="${announcement.userGroup != null }">
        							<li>${announcement.userGroup}</li>
    							</c:when>
								<c:otherwise>
       	 							<li>All</li>
    							</c:otherwise>
							</c:choose>
						</ul>
						<br>
						<p>
							<b>Announcement for Residents:</b>
						</p>
						<ul>
							<c:forEach items="${announcement.buildings}" var="building">
								<li>${building.name}</li>
							</c:forEach>
						</ul>
					</c:if>
				</div>
			</div>
		</div>
	</div>
</div>
<br>
<br>
<%@ include file="common/footer.jspf"%>