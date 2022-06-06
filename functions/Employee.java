package functions;
public class Employee {
    private int empID;
    private String empName;
    private float empSalary;

    public Employee(int empID, String empName, float empSalary) {
        this.empID = empID;
        this.empName = empName;
        this.empSalary = empSalary;
    }

    public int getEmpID() {
        return empID;
    }

    public float getEmpSalary() {
        return empSalary;
    }

    public String getEmpName() {
        return empName;
    }

    public String toString() {
        return String.format("%d\t%s\t   R$%.2f",this.empID,this.empName,this.empSalary);
    }
}