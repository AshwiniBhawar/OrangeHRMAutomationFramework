package com.qa.orangehrm.constants;

import java.util.List;

public class AppConstants {
	public static final int DEFAULT_SHORT_WAIT=5;
	public static final int DEFAULT_MEDIUM_WAIT=10;
	public static final int DEFAULT_LONG_WAIT=20;
	
	public static final String LOGIN_PAGE_TITLE = "OrangeHRM";
	public static final String LOGIN_PAGE_FRACTION_URL = "auth/login";
	
	public static final String DASHBOARD_PAGE_FRACTION_URL= "dashboard/index";
	

	public static final String DASHBOARD_MODULE_HEADER_TEXT="Dashboard";
	public static final String PIM_MODULE_HEADER_TEXT="PIM";
	public static final String DELETE_EMPLOYEE_SUCCESS_MSG="Success\n"+"Successfully Deleted";
	public static final String ADD_EMPLOYEE_SUCCESS_MSG="Success\n"+"Successfully Saved";
	public static final String NO_EMPLOYEE_RECORD_FOUND_TEST="Info\n"+"No Records Found";
	
	public static final List<String> CONFIGURATION_DROPDOWN_LIST= List.of("Optional Fields", "Custom Fields", "Data Import",
			"Reporting Methods", "Termination Reasons");
	
	public static final List<String> PIM_HEADERS_LIST= List.of("Configuration", "Employee List", "Add Employee", "Reports");
}
