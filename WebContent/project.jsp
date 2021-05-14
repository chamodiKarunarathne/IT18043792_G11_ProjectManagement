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
<body>	<center>
	<div class="container">
		<div class="row">
			<div class="col-6">
			<h1>Project Management</h1>
				<form id="formProject" name="formProject" method="post" action="project.jsp">

					Project Code: <input id="projectCode" name="projectCode" type="text"
						class="form-control form-control-lg"><br> Project Name:
					<input id="projectName" name="projectName" type="text"
						class="form-control form-control-lg"><br> Project Price:<input
						id="projectPrice" name="projectPrice" type="text"
						class="form-control form-control-lg"><br>

					Project Description: <input id="projectDescription"
						name="projectDescription" type="text"
						class="form-control form-control-lg"><br> <input
						id="btnSave" name="btnSave" type="button" value="Save"
						class="btn btn-primary form-control-sm"> <input
						type="hidden" id="hidProjectIDSave" name="hidProjectIDSave"
						value="">

				</form>

				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>
				<br>
				<div id="divProjectGrid">
					<%
						Project projectObj = new Project();
								out.print(projectObj.readProject());
					%>
				</div>
			</div>
		</div>
	</div></center>
</body>
</html>