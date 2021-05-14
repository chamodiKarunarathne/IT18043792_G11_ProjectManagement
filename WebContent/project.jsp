<%@page import="com.Project"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Project Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/Project.js"></script>
</head>
<body>	
	<div class="container">
		<div class="row">
			<div class="col-12">
			<center><h1  style="color:#0000ff;">Project Management</h1></center>
			<div class="offset-xl-3 col-xl-6">
				<form id="formProject" name="formProject" method="post" action="project.jsp" class="form-group mt-3">

					Project Code: <input id="projectCode" name="projectCode" type="text"
						class="form-control form-control-lg" ><br> Project Name:
					<input id="projectName" name="projectName" type="text"
						class="form-control form-control-lg"><br> Project Price:<input
						id="projectPrice" name="projectPrice" type="text"
						class="form-control form-control-lg"><br>

					Project Description: <input id="projectDescription"
						name="projectDescription" type="text"
						class="form-control form-control-lg"><br> <input
						id="btnSave" name="btnSave" type="button" value="Save" class="btn form-control btn-primary"
						class="btn btn-primary form-control-sm" > <input
						type="hidden" id="hidProjectIDSave" name="hidProjectIDSave"
						value="">

				</form>
				</div><center>
				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>
				<br>
				<div id="divProjectGrid">
					<%
						Project projectObj = new Project();
								out.print(projectObj.readProject());
					%>
				</div></center>
			</div>
		</div>
	</div>
</body>
</html>