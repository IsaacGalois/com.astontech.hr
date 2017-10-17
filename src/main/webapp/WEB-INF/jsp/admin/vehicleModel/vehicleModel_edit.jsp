<%@include file="../../includes/header.jsp" %>
<%@include file="../../includes/navbar.jsp" %>
<%@include file="../../includes/subnav_admin.jsp" %>

<script>
    $(document).ready(function() {
        $("#successAlert").delay(8000).fadeOut(2000);
        $("#warningAlert").delay(10000).fadeOut(2000);
        $("#errorAlert").delay(8000).fadeOut(2000);
    });
</script>

<div class="wrapper">

    <%--SIDEBAR--%>
    <%@include file="../vehicle/vehicle_sidebar.jsp" %>

    <div id="main-wrapper" class="col-sm-10">
        <div class="col-sm-8">

            <%--LIST OF EXISTING VehicleModels--%>
            <c:set var="idx" value="0" scope="page" />
            <form:form class="form-horizontal" modelAttribute="vehicleModel" action="/admin/vehicleModel/update" method="post">
                <form:hidden path="id" />
                <form:hidden path="version" />

                <%--VEHICLE YEAR--%>
                <div class="row">
                    <div class="form-group">
                        <label class="col-sm-2 control-label" for="inputVehicleModelName">Vehicle Model Name</label>
                        <div class="col-sm-8">
                            <form:input path="vehicleModelName" type="text" id="inputVehicleModelName" cssClass="form-control"></form:input>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group">
                        <label class="col-sm-2 control-label" for="inputVehicleMake">Vehicle Make</label>
                        <div class="col-sm-8">
                            <form:select path="vehicleMake" type="text" id="inputVehicleMake" cssClass="form-control">
                                <c:forEach var="vehicleMake" items="${vehicleMakeList}">
                                    <c:choose>
                                        <c:when test="${vehicleMake.id == vehicleModel.vehicleMake.id}">
                                            <option selected value="${vehicleMake.id}">
                                                    ${vehicleMake.vehicleMakeName}
                                            </option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${vehicleMake.id}">
                                                    ${vehicleMake.vehicleMakeName}
                                            </option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </form:select>
                        </div>
                    </div>
                </div>


                <div class="row">
                    <button class="btn btn-primary">Update</button>
                </div>


            </form:form>


        </div>
        <div class="col-sm-4">

            <%--SUCCESS ALERT--%>
            <div class="${successAlert == null ? 'hidden': successAlert}" id = "successAlert">
                <div class="alert alert-dismissible alert-success">
                    <button type="button" class="close" data-dismiss="alert">&times;</button>
                    <strong>Vehicle Model Successfully updated!</strong>
                </div>
            </div>

            <%--WARNING ALERT--%>
            <div class="${warningAlert == null ? 'hidden': warningAlert}" id = "warningAlert">
                <div class="alert alert-dismissible alert-warning">
                    <button type="button" class="close" data-dismiss="alert">&times;</button>
                    <strong>To Update the Vehicle Model:</strong>
                    <p>Change the fields below and hit the update button. (Vehicle Make field is required)</p>
                </div>
            </div>

            <%--ERROR ALERT--%>
            <div class="${errorAlert == null ? 'hidden': errorAlert}" id = "errorAlert">
                <div class="alert alert-dismissible alert-danger">
                    <button type="button" class="close" data-dismiss="alert">&times;</button>
                    <strong>Error:</strong>
                    <p>Please choose a Vehicle Make. (Must delete all Vehicles of a certain Vehicle Model before the Vehicle Model can be deleted)</p>
                </div>
            </div>
        </div>
    </div>
</div>

<%@include file="../../includes/footer.jsp" %>