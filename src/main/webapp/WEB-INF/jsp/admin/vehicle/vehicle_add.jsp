<%@include file="../../includes/header.jsp" %>
<%@include file="../../includes/navbar.jsp" %>
<%@include file="../../includes/subnav_admin.jsp" %>

<script>
    $(document).ready(function() {
        $("#successAlert").delay(8000).fadeOut(2000);
        $("#warningAlert").delay(10000).fadeOut(2000);
        $("#errorAlert").delay(8000).fadeOut(2000);

        //
        $('.cascade').each(function() {
            $(this).hide();
        });

        $('#defaultModel').show();

        //cascading select bars (on select Vehicle Make change)
        $('#cascade-make-selector').on('change', function() {
            //reset all Vehicle Model options to be visible
            $('#cascading-model-selector').children('option').show();
            //save selected Vehicle Model as var 'selection'
            var selection = $('#cascade-make-selector option:selected').val();
            //loop through each select Vehicle Model option
            var firstBool = true;
            $('.cascade').each(function() {
                if(!$(this).hasClass(selection)) {
                    $(this).hide();
                } else {
                    if(firstBool) {
                        $('#cascading-model-selector').val($(this).val());
                        firstBool = false;
                    }
                }
            });
            $('#defaultModel').remove();
            $('#defaultMake').remove();
        });

    });
</script>

<div class="wrapper">

    <%--SIDEBAR--%>
    <%@include file="vehicle_sidebar.jsp" %>

    <div id="main-wrapper" class="col-sm-10">
        <div class="col-sm-8">
            <form:form cssClass="form-horizontal" modelAttribute="vehicleVO" action="/admin/vehicle/add" method="post">
                <fieldset>
                    <legend>Vehicle Management</legend>

                    <%--VEHICLE YEAR--%>
                    <div class="form-group">
                        <label for="inputNewYear" class="col-lg-2 control-label">Vehicle Year</label>
                        <div class="col-lg-10">
                            <form:input path="newYear" type="number" cssClass="form-control" id="inputNewYear" placeholder="2000"></form:input>
                        </div>
                    </div>

                    <%--VEHICLE LICENSE PLATE--%>
                    <div class="form-group">
                        <label for="inputNewLicensePlate" class="col-lg-2 control-label">License Plate</label>
                        <div class="col-lg-10">
                            <form:input path="newLicensePlate" type="text" cssClass="form-control" id="inputNewLicensePlate" placeholder="License Plate Number"></form:input>
                        </div>
                    </div>

                    <%--VEHICLE VIN--%>
                    <div class="form-group">
                        <label for="inputNewVin" class="col-lg-2 control-label">VIN</label>
                        <div class="col-lg-10">
                            <form:input path="newVin" type="text" cssClass="form-control" id="inputNewVin" placeholder="VIN Number"></form:input>
                        </div>
                    </div>

                    <%--VEHICLE COLOR--%>
                    <div class="form-group">
                        <label for="inputNewColor" class="col-lg-2 control-label">Color</label>
                        <div class="col-lg-10">
                            <form:input path="newColor" type="text" cssClass="form-control" id="inputNewColor" placeholder="Vehicle Color"></form:input>
                        </div>
                    </div>

                    <%--VEHICLE MAKE--%>
                    <div class="form-group">
                        <label for="cascade-make-selector" class="col-lg-2 control-label">Vehicle Make</label>
                        <div class="col-lg-10">
                            <form:select path="newVehicleMakeId" name="addVehicleMakeSelection" class="form-control" id="cascade-make-selector">
                                <option selected value='0' id="defaultMake">Select Vehicle Make</option>
                                <c:forEach var="vehicleMake" items="${vehicleMakeList}">
                                    <option value="${vehicleMake.id}">
                                            ${vehicleMake.vehicleMakeName}
                                    </option>
                                </c:forEach>
                            </form:select>
                        </div>
                    </div>

                    <%--VEHICLE MODEL--%>
                    <div class="form-group">
                        <label for="cascading-model-selector" class="col-lg-2 control-label">Vehicle Model</label>
                        <div class="col-lg-10">
                            <form:select path="newVehicleModelId" name="addVehicleModelSelection" class="form-control" id="cascading-model-selector">
                                <option selected value='0' id="defaultModel">Select Vehicle Model</option>
                                <c:forEach var="vehicleModel" items="${vehicleModelList}">
                                    <option value="${vehicleModel.id}" name="${vehicleModel.vehicleModelName}" class="${vehicleModel.vehicleMake.id} cascade" id="model${vehicleModel.id}">
                                            ${vehicleModel.vehicleModelName}
                                    </option>
                                </c:forEach>
                            </form:select>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-lg-10 col-lg-offset-2">
                            <form:button type="reset" value="cancel" class="btn btn-default">Cancel</form:button>
                            <form:button type="submit" value="save" class="btn btn-primary">Save</form:button>
                        </div>
                    </div>

                </fieldset>
            </form:form>
        </div>
        <div class="col-sm-4">

            <%--ALERTS--%>

            <%--SUCCESS ALERT--%>
            <div class="${successAlert == null ? 'hidden': successAlert}" id = "successAlert">
                <div class="alert alert-dismissible alert-success">
                    <button type="button" class="close" data-dismiss="alert">&times;</button>
                    <strong>Vehicle Successfully Added to the database!</strong>
                </div>
            </div>

            <%--WARNING ALERT--%>
            <div class="${warningAlert == null ? 'hidden': warningAlert}" id = "warningAlert">
                <div class="alert alert-dismissible alert-warning">
                    <button type="button" class="close" data-dismiss="alert">&times;</button>
                    <strong>To add an Vehicle:</strong>
                    <p>Fill out desired fields. Vehicle Year, Vehicle Make, and Vehicle Model fields required. (Vehicle Make and Model Choices must be in database already)</p>
                </div>
            </div>

            <%--ERROR ALERT--%>
            <div class="${errorAlert == null ? 'hidden': errorAlert}" id = "errorAlert">
                <div class="alert alert-dismissible alert-danger">
                    <button type="button" class="close" data-dismiss="alert">&times;</button>
                    <strong>ERROR: Vehicle could not be added to database.</strong>
                    <p>Make sure all required fields have been filled out.</p>
                </div>
            </div>

        </div>
    </div>
</div>

<%@include file="../../includes/footer.jsp" %>