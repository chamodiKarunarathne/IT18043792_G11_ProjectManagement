package com;

import java.sql.*;

public class Project {

	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gadgetbadgetdb", "root", "aloka");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

//------------------------------------------------Insert---------------------------------------------------------

	public String insertProject(String projectCode, String projectName, String projectPrice, String projectDescription) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into project(`projectId`,`projectCode`,`projectName`,`projectPrice`,`projectDescription`)"
					+ " values (?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, projectCode);
			preparedStmt.setString(3, projectName);
			preparedStmt.setDouble(4, Double.parseDouble(projectPrice));
			preparedStmt.setString(5, projectDescription);
			
			// execute the statement

			preparedStmt.execute();
			con.close();
			
			String newProject = readProject();
			output = "{\"status\":\"success\", \"data\": \"" + newProject + "\"}";

		} catch (Exception e) {
			
			 output = "{\"status\":\"error\", \"data\": \"Error while inserting the Project.\"}";
			 System.err.println(e.getMessage()); 
			
		}

		return output;
	}

//----------------------------------------------------Read------------------------------------------------	

	public String readProject() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border='1' class='table table-bordered '><tr><th class='text-center'>Project Code</th><th class='text-center'>Project Name</th>" + "<th class='text-center'>Project Price</th>"
					+ "<th class='text-center'>Project Description</th>"
					+ "<th class='text-center'>Update</th><th class='text-center'>Remove</th></tr>";

			String query = "select * from project";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String projectId = Integer.toString(rs.getInt("projectId"));
				String projectCode = rs.getString("projectCode");
				String projectName = rs.getString("projectName");
				String projectPrice = Double.toString(rs.getDouble("projectPrice"));
				String projectDescription = rs.getString("projectDescription");
				

				// Add into the html table
				output += "<tr><td><input id='hidProjectIDUpdate' name='hidProjectIDUpdate' type='hidden' value= '" + projectId
						+ "'>" + projectCode + "</td>";
				output += "<td>" + projectName + "</td>";
				output += "<td>" + projectPrice + "</td>";
				output += "<td>" + projectDescription + "</td>";
				
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td> "
				        + "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-projectid='" 
				        + projectId + "'>"+ "</td></tr>" ;
						
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the Research.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	
	public String updateProject(String projectId, String projectCode, String projectName, String projectPrice, String projectDescription)

			{
			String output = "";
			try {
			Connection con = connect();
			if (con == null) {
			return "Error while connecting to the database for updating.";
			}
			
			// create a prepared statement
			String query = "UPDATE project SET projectCode=?,projectName=?,projectPrice=?,projectDescription=? WHERE projectId=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values with database



			preparedStmt.setString(1, projectCode);
			preparedStmt.setString(2, projectName);
			preparedStmt.setDouble(3, Double.parseDouble(projectPrice));
			preparedStmt.setString(4, projectDescription);
			preparedStmt.setInt(5, Integer.parseInt(projectId));
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newProject = readProject();
			output = "{\"status\":\"success\", \"data\": \"" + newProject + "\"}"; 



			} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\": \"Error while inserting the project.\"}";
			System.err.println(e.getMessage());
			}
			return output;
			}

	public String deleteProject(String projectId) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "delete from project where projectId=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(projectId));
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newProject = readProject();
			output = "{\"status\":\"success\", \"data\": \"" + newProject + "\"}";

		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\": \"Error while inserting the project.\"}";
			System.err.println(e.getMessage()); 
		}

		return output;
	}
}
