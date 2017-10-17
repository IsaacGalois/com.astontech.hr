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

            <%--LIST OF EXISTING VehicleMake--%>
            <c:set var="idx" value="0" scope="page" />
            <form:form class="form-horizontal" modelAttribute="vehicleMake" action="/admin/vehicleMake/update" method="post">
                <form:hidden path="id" />
                <form:hidden path="version" />

                <%--VEHICLE YEAR--%>
                <div class="row">
                    <div class="form-group">
                        <label class="col-sm-2 control-label" for="inputVehicleMakeName">Vehicle Make Name</label>
                        <div class="col-sm-8">
                            <form:input path="vehicleMakeName" type="text" id="inputVehicleMakeName" cssClass="form-control"></form:input>
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
                    <strong>Vehicle Make Successfully updated!</strong>
                </div>
            </div>

            <%--WARNING ALERT--%>
            <div class="${warningAlert == null ? 'hidden': warningAlert}" id = "warningAlert">
                <div class="alert alert-dismissible alert-warning">
                    <button type="button" class="close" data-dismiss="alert">&times;</button>
                    <strong>To Update the Vehicle Make:</strong>
                    <p>Change the Vehicle Make Name field below and hit the update button.</p>
                </div>
            </div>

            <%--ERROR ALERT--%>
            <div class="${errorAlert == null ? 'hidden': errorAlert}" id = "errorAlert">
                <div class="alert alert-dismissible alert-danger">
                    <button type="button" class="close" data-dismiss="alert">&times;</button>
                    <strong>Error:</strong>
                    <p>Must delete all Vehicle Models of a certain Vehicle Make before the Vehicle Make can be deleted.</p>
                </div>
            </div>
        </div>
    </div>
</div>

<%@include file="../../includes/footer.jsp" %>