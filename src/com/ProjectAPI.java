package com;

import java.io.IOException;
import java.util.HashMap; 
import java.util.Map; 
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Project;
/**
 * Servlet implementation class ProjectAPI
 */
@WebServlet("/ProjectAPI")
public class ProjectAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Project projectObj = new Project();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProjectAPI() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String output = projectObj.insertProject(request.getParameter("projectCode"),
				request.getParameter("projectName"), request.getParameter("projectPrice"),
				request.getParameter("projectDescription"));

		response.getWriter().write(output);
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map paras = getParasMap(request);
		String output = projectObj.updateProject(paras.get("hidProjectIDSave").toString(),
				paras.get("projectCode").toString(), paras.get("projectName").toString(),
				paras.get("projectPrice").toString(), paras.get("projectDescription").toString());
		response.getWriter().write(output);
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map paras = getParasMap(request);
		String output = projectObj.deleteProject(paras.get("projectId").toString());
		response.getWriter().write(output);
	}

	// Convert request parameters to a Map
	private static Map getParasMap(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();

		try {
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
			String queryString = scanner.hasNext() ? scanner.useDelimiter("\\A").next() : "";
			scanner.close();

			String[] params = queryString.split("&");
			for (String param : params) {

				String[] p = param.split("=");
				map.put(p[0], p[1]);
			}
		} catch (Exception e) {
		}
		return map;
	}

 }
	 

