<!DOCTYPE html>
<html>
<head>
    <title>Welcome to Kendo UI!</title>
    <link href="${pageContext.request.contextPath}/resources/css/kendo/kendo.common.min.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/resources/css/kendo/kendo.default.min.css" rel="stylesheet" />
    <script src="${pageContext.request.contextPath}/resources/js/jquery-1.11.2.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/kendo/kendo.all.min.js"></script>
</head>
<body>
<input id="datepicker" />
<script>
    $(function() {
        $("#datepicker").kendoDatePicker();
    });
</script>
</body>
</html>