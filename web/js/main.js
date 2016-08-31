$(document).ready(function () {
    var today = new Date();
    $("#datepicker").datepicker({
        format: " yyyy",
        viewMode: "years",
        minViewMode: "years",
        endDate: today.getFullYear().toString()
    });
});
