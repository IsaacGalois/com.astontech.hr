<%@include file="../../includes/header.jsp" %>
<%@include file="../../includes/navbar.jsp" %>
<%@include file="../../includes/subnav_admin.jsp" %>

<div class="wrapper">

    <%--SIDEBAR--%>
    <%@include file="../vehicle/vehicle_sidebar.jsp" %>

    <div id="main-wrapper" class="col-sm-10">
        <div class="col-sm-8">
            <form:form cssClass="form-horizontal" modelAttribute="vehicleMake" action="/admin/vehicleMake/add" method="post">
                <fieldset>
                    <legend>Vehicle Make Management</legend>

                        <%--VEHICLE MAKE NAME--%>
                    <div class="form-group">
                        <label for="inputVehicleMakeName" class="col-lg-2 control-label">Vehicle Make Name</label>
                        <div class="col-lg-10">
                            <form:input path="vehicleMakeName" type="text" cssClass="form-control" id="inputVehicleMakeName" placeholder="Vehicle Make Name"></form:input>
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
                    <strong>Vehicle Make Successfully Added to the database!</strong>
                </div>
            </div>

            <%--WARNING ALERT--%>
            <div class="${warningAlert == null ? 'hidden': warningAlert}" id = "warningAlert">
                <div class="alert alert-dismissible alert-warning">
                    <button type="button" class="close" data-dismiss="alert">&times;</button>
                    <strong>To add an Vehicle Make:</strong>
                    <p>Fill out Vehicle Make Name, this field is required.</p>
                </div>
            </div>

            <%--ERROR ALERT--%>
            <div class="${errorAlert == null ? 'hidden': errorAlert}" id = "errorAlert">
                <div class="alert alert-dismissible alert-danger">
                    <button type="button" class="close" data-dismiss="alert">&times;</button>
                    <strong>ERROR: Vehicle Make could not be added to database.</strong>
                    <p>Make sure Vehicle Make Name field has been filled out.</p>
                </div>
            </div>

        </div>
    </div>
</div>

<%@include file="../../includes/footer.jsp" %>