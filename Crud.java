package crudJava;
import java.util.*;

public interface Crud {
    Scanner scann2 = new Scanner(System.in);

    public static Employee addEmployee(){
        System.out.println("Insira o ID do funcionário: ");
        int eNum = Integer.parseInt(scann2.nextLine());
        System.out.println("Insira o nome do funcionário: ");
        String eName = scann2.nextLine();
        System.out.println("Insira o salário do funcionário: ");
        float eSalary = Float.parseFloat(scann2.nextLine());
    
        //Não pode
        //scann2.close();

        return new Employee(eNum, eName, eSalary);
    }

    public static void readEmployees(){
        System.out.println("_____________________________________");
        Iterator<Employee> i = arrEmployees.iterator();
        while (i.hasNext()) {
            Employee e = i.next();
            System.out.println(e);
        }
        System.out.println("_____________________________________");
    }

    public static void searchEmployee() {

        boolean found = false;
        System.out.println("Insira o ID de busca: ");
        int empNum = scann2.nextInt();
        System.out.println("_____________________________________");
        i = arrEmployees.iterator();
        while (i.hasNext()) {
            Employee e = i.next();
            if (e.getEmpID() == empNum) {
                System.out.println(e);
                found = true;
            }
        }
        if (!found) {
            System.out.println("FUNCIONARIO não encontrado");
        }
        System.out.println("_____________________________________");
    }
}
