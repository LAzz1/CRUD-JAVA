package functions;
public class Employee {
    private int empID;
    private String employeeName;
    private float salary;

    Employee(int empID, String employeeName, float salary) {
        this.empID = empID;
        this.employeeName = employeeName;
        this.salary = salary;
    }

    public int getEmpID() {
        return empID;
    }

    public float getSalary() {
        return salary;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmpID(int empID) {
        this.empID = empID;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public void setEmployeeName(String name) {
        this.employeeName = name;
    }

    public String toString() {
        return empID + "" + employeeName + "" + salary;
    }
}