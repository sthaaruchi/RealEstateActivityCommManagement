<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>
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
					<p>
						<b>Announcement for User Group:</b>
					</p>
					<ul>
						<c:forEach items="${announcement.userGroup}" var="user_group">
							<li>${announcement.userGroup}</li>
						</c:forEach>
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
				</div>
			</div>
		</div>
	</div>
</div>
<br>
<br>
<%@ include file="common/footer.jspf"%>