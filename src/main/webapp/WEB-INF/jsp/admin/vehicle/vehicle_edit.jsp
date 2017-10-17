<%@include file="../../includes/header.jsp" %>
<%@include file="../../includes/navbar.jsp" %>
<%@include file="../../includes/subnav_admin.jsp" %>

<script>
    $(document).ready(function() {
        $("#successAlert").delay(8000).fadeOut(2000);
        $("#warningAlert").delay(10000).fadeOut(2000);
        $("#errorAlert").delay(8000).fadeOut(2000);

        //define vars and funcs
        var trimModels = function(afterPageLoad) {
            var selection = $('#inputVehicleMake option:selected').val();
            var firstBool = true;
            $('.cascade').each(function () {
                if (!$(this).hasClass(selection)) {
                    $(this).hide();
                } else if (afterPageLoad){
                    //set select Vehicle Model to default to first Model of selected Make
                    if(firstBool) {
                        $('#inputVehicleModel').val($(this).val());
                        firstBool = false;
                    }
                }
            });
        };

        //trim options on Vehicle Model select  on page load.
        trimModels(false);

        //cascading select bars (on select Vehicle Make change)
        $('#inputVehicleMake').on('change', function() {
            //reset all Vehicle Model options to be visible
            $('#inputVehicleModel').children('option').show();
            trimModels(true);
        });
    });
</script>

<div class="wrapper">

    <%--SIDEBAR--%>
    <%@include file="vehicle_sidebar.jsp" %>

    <div id="main-wrapper" class="col-sm-10">
        <div class="col-sm-8">

            <%--LIST OF EXISTING Vehicles--%>
            <c:set var="idx" value="0" scope="page" />
            <form:form class="form-horizontal" modelAttribute="vehicle" action="/admin/vehicle/update" method="post">
                <form:hidden path="id" />
                <form:hidden path="version" />

                <%--VEHICLE YEAR--%>
                <div class="row">
                    <div class="form-group">
                        <label class="col-sm-2 control-label" for="inputYear">Vehicle Year</label>
                        <div class="col-sm-8">
                            <form:input path="year" type="number" id="inputYear" cssClass="form-control"></form:input>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group">
                        <label class="col-sm-2 control-label" for="inputLicensePlate">Vehicle License Plate</label>
                        <div class="col-sm-8">
                            <form:input path="licensePlate" type="text" id="inputLicensePlate" cssClass="form-control"></form:input>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group">
                        <label class="col-sm-2 control-label" for="inputVin">Vehicle VIN</label>
                        <div class="col-sm-8">
                            <form:input path="vin" type="text" id="inputVin" cssClass="form-control"></form:input>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group">
                        <label class="col-sm-2 control-label" for="inputColor">Vehicle Color</label>
                        <div class="col-sm-8">
                            <form:input path="color" type="text" id="inputColor" cssClass="form-control"></form:input>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group">
                        <label class="col-sm-2 control-label" for="inputVehicleModel">Vehicle Model</label>
                        <div class="col-sm-8">
                            <form:select path="vehicleModel" type="text" id="inputVehicleModel" cssClass="form-control">
                                <c:forEach var="vehicleModel" items="${vehicleModelList}">
                                    <c:choose>
                                        <c:when test="${vehicleModel.id == vehicle.vehicleModel.id}">
                                            <option selected value="${vehicleModel.id}" name="${vehicleModel.vehicleModelName}" class="${vehicleModel.vehicleMake.id} cascade" id="model${vehicleModel.id}">
                                                    ${vehicleModel.vehicleModelName}
                                            </option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${vehicleModel.id}" name="${vehicleModel.vehicleModelName}" class="${vehicleModel.vehicleMake.id} cascade" id="model${vehicleModel.id}">
                                                    ${vehicleModel.vehicleModelName}
                                            </option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </form:select>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group">
                        <label class="col-sm-2 control-label" for="inputVehicleMake">Vehicle Make</label>
                        <div class="col-sm-8">
                            <form:select path="vehicleModel.vehicleMake" type="text" id="inputVehicleMake" cssClass="form-control">
                                <c:forEach var="vehicleMake" items="${vehicleMakeList}">
                                    <c:choose>
                                        <c:when test="${vehicleMake.id == vehicle.vehicleModel.vehicleMake.id}">
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
                    <strong>Vehicle Successfully updated!</strong>
                </div>
            </div>

            <%--WARNING ALERT--%>
            <div class="${warningAlert == null ? 'hidden': warningAlert}" id = "warningAlert">
                <div class="alert alert-dismissible alert-warning">
                    <button type="button" class="close" data-dismiss="alert">&times;</button>
                    <strong>To Update the Vehicle:</strong>
                    <p>Change the fields below and hit the update button. (Year entries must be positive. Vehicle Model field is required)</p>
                </div>
            </div>

            <%--ERROR ALERT--%>
            <div class="${errorAlert == null ? 'hidden': errorAlert}" id = "errorAlert">
                <div class="alert alert-dismissible alert-danger">
                    <button type="button" class="close" data-dismiss="alert">&times;</button>
                    <strong>Error:</strong>
                    <p>Please choose a Vehicle Model and/or input a valid (non-negative) year.</p>
                </div>
            </div>
        </div>
    </div>
</div>

<%@include file="../../includes/footer.jsp" %>