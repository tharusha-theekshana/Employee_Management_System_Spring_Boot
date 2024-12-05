package com.employee_management.employee_mn_system.Utils;

public class ResponseConstants {

    public static final String MESSAGE = "Message";
    public static final String SOMETHING_WENT_WRONG = "Something Went Wrong ... !";
    public static final String EMPLOYEE_SAVED_SUCCESSFULLY = "Employee Saved Successfully ... !";
    public static final String INVALID_DEPARTMENT = "Department is not Exist ... !";
    public static final String INVALID_DATA = "{" +
            " \"Invalid Data. All Data is Required !\",\n" +
            "  \t\t\t\"data\": {\n" +
            "    \t\t\t\t\"firstName\": Required !\"\",\n" +
            "    \t\t\t\t\"lastName\": Required ! \"\",\n" +
            "    \t\t\t\t\"email\": Required ! \"\",\n" +
            "    \t\t\t\t\"phone\": Required !\"\",\n" +
            "    \t\t\t\t\"departmentId\": Required !\"\"\n \t\t\t\t\t" +
            "} \n" +
            "\t\t\t}";

    public static final String EMPLOYEE_EMAIL_EXISTS = "Email Already Exist ... !";
    public static final String FETCH_DATA_SUCCESSFULLY = "Fetch Data Successfully ... !";
    public static final String EMPLOYEE_NOT_EXISTS = "Employee is not Exist ... !";
    public static final String EMPLOYEE_UPDATED_SUCCESSFULLY = "Employee Updated Successfully ... !";
    public static final String EMPLOYEE_DELETED_SUCCESSFULLY = "Employee Deleted Successfully ... !";
    public static final String DEPARTMENT_SAVED_SUCCESSFULLY = "Department Saved Successfully ... !";
    public static final String DEPARTMENT_NOT_EXISTS = "Department is not Exist ... !";
    public static final String DEPARTMENT_UPDATED_SUCCESSFULLY = "Department Updated Successfully ... !";
    public static final String DEPARTMENT_DELETED_SUCCESSFULLY = "Department Deleted Successfully ... !";

    public static final String INVALID_DATA_DEP = "{" +
            " \"Invalid Data. All Data is Required !\",\n" +
            "  \t\t\t\"data\": {\n" +
            "    \t\t\t\t\"name\": Required !\"\",\n" +
            "\t\t\t}";

}
