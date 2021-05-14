$(document).ready(function() {

$("#alertSuccess").hide();
$("#alertError").hide();
});



// SAVE ============================================
$(document).on("click", "#btnSave", function(event) {
// Clear alerts---------------------
$("#alertSuccess").text("");
$("#alertSuccess").hide();
$("#alertError").text("");
$("#alertError").hide();



// Form validation-------------------
var status = validateProjectForm();
if (status != true) {
$("#alertError").text(status);
$("#alertError").show();
return;
}
// If valid-------------------------
var type = ($("#hidProjectIDSave").val() == "") ? "POST" : "PUT";



$.ajax(
{
url: "ProjectAPI",
type: type,
data: $("#formProject").serialize(),
dataType: "text",
complete: function(response, status) {
onProjectSaveComplete(response.responseText, status);

}
});
});


function onProjectSaveComplete(response, status) {
if (status == "success") {
var resultSet = JSON.parse(response);



if (resultSet.status.trim() == "success") {
$("#alertSuccess").text("Successfully saved.");
$("#alertSuccess").show();



$("#divProjectGrid").html(resultSet.data);
} else if (resultSet.status.trim() == "error")
{
$("#alertError").text(resultSet.data);
$("#alertError").show();
}
} else if (status == "error") {
$("#alertError").text("Error while saving.");
$("#alertError").show();
} else {
$("#alertError").text("Unknown error while saving..");
$("#alertError").show();
}
$("#hidProjectIDSave").val("");
$("#formProject")[0].reset();
}




// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event) {
$("#hidProjectIDSave").val($(this).closest("tr").find('#hidProjectIDUpdate').val());
$("#projectCode").val($(this).closest("tr").find('td:eq(0)').text());
$("#projectName").val($(this).closest("tr").find('td:eq(1)').text());
$("#projectPrice").val($(this).closest("tr").find('td:eq(2)').text());
$("#projectDescription").val($(this).closest("tr").find('td:eq(3)').text());

});




$(document).on("click", ".btnRemove", function(event) {
$.ajax(
{
url: "ProjectAPI",
type: "DELETE",
data: "projectId=" + $(this).data("projectid"),
dataType: "text",
complete: function(response, status) {
onProjectDeleteComplete(response.responseText, status);
}
});
});




function onProjectDeleteComplete(response, status) {
if (status == "success") {
var resultSet = JSON.parse(response);
if (resultSet.status.trim() == "success") {
$("#alertSuccess").text("Successfully deleted.");
$("#alertSuccess").show();
$("#divProjectGrid").html(resultSet.data);
} else if (resultSet.status.trim() == "error") {
$("#alertError").text(resultSet.data);
$("#alertError").show();
}
} else if (status == "error") {
$("#alertError").text("Error while deleting.");
$("#alertError").show();
} else {
$("#alertError").text("Unknown error while deleting..");
$("#alertError").show();
}
}






// CLIENT-MODEL================================================================
function validateProjectForm() {
// Name
if ($("#projectCode").val().trim() == "") {
return "Insert Funder Name.";
}
// Research
if ($("#projectName").val().trim() == "") {
return "Insert Funding Research.";
}


// PRICE-------------------------------
if ($("#projectPrice").val().trim() == "") {
return "Insert Fund Amount.";
}
// is numerical value
var tmpPrice = $("#projectPrice").val().trim();
if (!$.isNumeric(tmpPrice)) {
return "Insert a numerical value for Funding Amount.";
}
// convert to decimal price
$("#projectPrice").val(parseFloat(tmpPrice).toFixed(2));

// DESCRIPTION------------------------
if ($("#projectDescription").val().trim() == "") {
return "Insert Description.";
}




return true;
}