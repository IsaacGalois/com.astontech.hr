<%@include file="../../includes/header.jsp" %>
<%@include file="../../includes/navbar.jsp" %>
<%@include file="../../includes/subnav_admin.jsp" %>

<div class="wrapper">

    <%--SIDEBAR--%>
    <%@include file="../vehicle/vehicle_sidebar.jsp" %>

    <div id="main-wrapper" class="col-sm-10">
        <div class="col-sm-8">

            <%--LIST OF EXISTING VEHICLE MODELS--%>
            <table class="table table-striped table-hover">
                <thead>
                <tr>
                    <th>Vehicle Model Name</th>
                    <th>Vehicle Make</th>
                    <th>Edit</th>
                    <th>Delete</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="vehicleModel" items="${vehicleModelList}">
                    <tr>
                        <td>${vehicleModel.vehicleModelName}</td>
                        <td>${vehicleModel.vehicleMake.vehicleMakeName}</td>
                        <td><a href="/admin/vehicleModel/edit/${vehicleModel.id}">Edit</a></td>
                        <td><a href="/admin/vehicleModel/delete/${vehicleModel.id}">Delete</a></td>
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
                    <strong>Vehicle Model Successfully deleted!</strong>
                </div>
            </div>

            <%--WARNING ALERT--%>
            <div class="${warningAlert == null ? 'hidden': warningAlert}" id = "warningAlert">
                <div class="alert alert-dismissible alert-warning">
                    <button type="button" class="close" data-dismiss="alert">&times;</button>
                    <strong>To Update the Vehicle Model:</strong>
                    <p>Click the links to edit or delete a Vehicle Model.</p>
                </div>
            </div>

            <%--ERROR ALERT--%>
            <div class="${errorAlert == null ? 'hidden': errorAlert}" id = "errorAlert">
                <div class="alert alert-dismissible alert-danger">
                    <button type="button" class="close" data-dismiss="alert">&times;</button>
                    <strong>Error:</strong>
                    <p>Vehicle Model data may be out of date, please try again.</p>
                    <p>NOTE: Must delete all Vehicles of a certain Vehicle Model before the Vehicle Model can be deleted</p>
                </div>
            </div>
        </div>
    </div>


</div>

<%@include file="../../includes/footer.jsp" %>