<%@include file="../../includes/header.jsp" %>
<%@include file="../../includes/navbar.jsp" %>
<%@include file="../../includes/subnav_admin.jsp" %>

<div class="wrapper">

    <%--SIDEBAR--%>
    <%@include file="vehicle_sidebar.jsp" %>

    <div id="main-wrapper" class="col-sm-10">
        <div class="col-sm-8">

            <%--LIST OF EXISTING VEHICLES--%>
            <table class="table table-striped table-hover">
                <thead>
                <tr>
                    <th>Year</th>
                    <th>License Plate</th>
                    <th>VIN</th>
                    <th>Color</th>
                    <th>Vehicle Make</th>
                    <th>Vehicle Model</th>
                    <th>Edit</th>
                    <th>Delete</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="vehicle" items="${vehicleList}">
                    <tr>
                        <td>${vehicle.year}</td>
                        <td>${vehicle.licensePlate}</td>
                        <td>${vehicle.vin}</td>
                        <td>${vehicle.color}</td>
                        <td>${vehicle.vehicleModel.vehicleMake.vehicleMakeName}</td>
                        <td>${vehicle.vehicleModel.vehicleModelName}</td>
                        <td><a href="/admin/vehicle/edit/${vehicle.id}">Edit</a></td>
                        <td><a href="/admin/vehicle/delete/${vehicle.id}">Delete</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="col-sm-4">

            <%--SUCCESS ALERT--%>
            <div class="${successAlert == null ? 'hidden': successAlert}" id = "successAlert">
                <div class="alert alert-dismissible alert-success">
                    <button type="button" class="close" data-dismiss="alert">&times;</button>
                    <strong>Vehicle Successfully deleted!</strong>
                </div>
            </div>

            <%--WARNING ALERT--%>
            <div class="${warningAlert == null ? 'hidden': warningAlert}" id = "warningAlert">
                <div class="alert alert-dismissible alert-warning">
                    <button type="button" class="close" data-dismiss="alert">&times;</button>
                    <strong>To Update the Vehicle:</strong>
                    <p>Click the links to edit or delete a Vehicle.</p>
                </div>
            </div>

            <%--ERROR ALERT--%>
            <div class="${errorAlert == null ? 'hidden': errorAlert}" id = "errorAlert">
                <div class="alert alert-dismissible alert-danger">
                    <button type="button" class="close" data-dismiss="alert">&times;</button>
                    <strong>Error:</strong>
                    <p>Vehicle data may be out of date, please try again.</p>
                </div>
            </div>
        </div>
    </div>
</div>

<%@include file="../../includes/footer.jsp" %>