<%@include file="../../includes/header.jsp" %>
<%@include file="../../includes/navbar.jsp" %>
<%@include file="../../includes/subnav_admin.jsp" %>

<script>
    $(document).ready(function() {
        $("#successAlert").delay(8000).fadeOut(2000);
        $("#warningAlert").delay(10000).fadeOut(2000);
        $("#errorAlert").delay(8000).fadeOut(2000);

        $('#cascade-make-selector').on('change', function() {
            $('#defaultModel').remove();
        });
    });
</script>

<div class="wrapper">

    <%--SIDEBAR--%>
    <%@include file="../vehicle/vehicle_sidebar.jsp" %>

    <div id="main-wrapper" class="col-sm-10">
        <div class="col-sm-8">
            <form:form cssClass="form-horizontal" modelAttribute="vehicleModelVO" action="/admin/vehicleModel/add" method="post">
                <fieldset>
                    <legend>Vehicle Model Management</legend>

                        <%--VEHICLE MODEL NAME--%>
                    <div class="form-group">
                        <label for="inputNewVehicleModelName" class="col-lg-2 control-label">Vehicle Model Name</label>
                        <div class="col-lg-10">
                            <form:input path="newVehicleModelName" type="text" cssClass="form-control" id="inputNewVehicleModelName" placeholder="Vehicle Model Name"></form:input>
                        </div>
                    </div>

                        <%--VEHICLE MAKE--%>
                    <div class="form-group">
                        <label for="cascade-make-selector" class="col-lg-2 control-label">Vehicle Make</label>
                        <div class="col-lg-10">
                            <form:select path="newVehicleMakeId" name="addVehicleMakeSelection" class="form-control" id="cascade-make-selector">
                                <option selected value='0' id="defaultModel">Select Vehicle Make</option>
                                <c:forEach var="vehicleMake" items="${vehicleMakeList}">
                                    <option value="${vehicleMake.id}">
                                            ${vehicleMake.vehicleMakeName}
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
                    <strong>Vehicle Model Successfully Added to the database!</strong>
                </div>
            </div>

            <%--WARNING ALERT--%>
            <div class="${warningAlert == null ? 'hidden': warningAlert}" id = "warningAlert">
                <div class="alert alert-dismissible alert-warning">
                    <button type="button" class="close" data-dismiss="alert">&times;</button>
                    <strong>To add an Vehicle Model:</strong>
                    <p>Fill out Vehicle Model Name and Vehicle Make fields, these are both required. (Vehicle Make Choice must be in database already)</p>
                </div>
            </div>

            <%--ERROR ALERT--%>
            <div class="${errorAlert == null ? 'hidden': errorAlert}" id = "errorAlert">
                <div class="alert alert-dismissible alert-danger">
                    <button type="button" class="close" data-dismiss="alert">&times;</button>
                    <strong>ERROR: Vehicle Model could not be added to database.</strong>
                    <p>Make sure both Vehicle Model Name and Vehicle Make fields have been filled out.</p>
                </div>
            </div>

        </div>
    </div>
</div>

<%@include file="../../includes/footer.jsp" %>