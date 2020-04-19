
<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>

<div class="container">
	<div class="row">
		<div class="col-md-6 col-md-offset-3 ">
			<div class="panel panel-primary">
				<div class="panel-heading">
					Add Announcement
				</div>
				<div class="panel-body">
					<form:form method="post" modelAttribute="announcement">

						<form:hidden path="announcementId" />
						<fieldset class="form-group">
							<form:label path="title">Title</form:label>
							<form:input path="title" type="text" class="form-control"
								required="required" />
						</fieldset>

						<fieldset class="form-group">
							<form:label path="description">Description</form:label>
							<form:input path="description" type="text" class="form-control"
								required="required" />
						</fieldset>

						<fieldset class="form-group">
							<form:label path="publishAnnouncementDate">Publish Date</form:label>
							<form:input path="publishAnnouncementDate" type="datetime-local"
								class="form-control" required="" />
						</fieldset>

						<fieldset class="form-group">
							<form:label path="buildings">Announcement for Residents:</form:label>
							<form:select path="buildings" class="multiselect-ui form-control"
								multiple="multiple">
								<c:forEach items="${buildings}" var="building">
									<c:if test="${announcement.buildings.contains(building)}">
										<form:option value="${building.buildingId}"
											selected="selected">${building.name}</form:option>
									</c:if>
									<c:if test="${!announcement.buildings.contains(building)}">
										<form:option value="${building.buildingId}">${building.name}</form:option>
									</c:if>

								</c:forEach>
							</form:select>
						</fieldset>

						<button type="submit" class="btn btn-success">Save</button>
					</form:form>
				</div>
			</div>
		</div>
	</div>
</div>
<%@ include file="common/footer.jspf"%>
<script type="text/javascript">
	$(".form_datetime").datetimepicker({
		format : "dd/MM/yyyy - hh:ii"
	});
</script>
