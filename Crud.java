import java.util.*;

public interface Crud {

    public static Employee addEmployee() {

        Scanner scannAdd = new Scanner(System.in);
        System.out.println("Insira o ID do funcionário: ");

        int eNum = Integer.parseInt(scannAdd.nextLine());
        System.out.println("Insira o nome do funcionário: ");
        String eName = scannAdd.nextLine();
        System.out.println("Insira o salário do funcionário: ");
        float eSalary = Float.parseFloat(scannAdd.nextLine());

        return new Employee(eNum, eName, eSalary);
        
    }

    public static void readEmployees(List arrEmployees) {

        Iterator<Employee> i = arrEmployees.iterator();

        System.out.println("_____________________________________");
        while (i.hasNext()) {
            Employee e = i.next();
            System.out.println(e);
        }
        System.out.println("_____________________________________");
    }

    public static void searchEmployee(List arrEmployees) {

        Iterator<Employee> i = arrEmployees.iterator();
        Scanner scannSearch = new Scanner(System.in);
        boolean found = false;

        System.out.println("Insira o ID de busca: ");
        int empNum = scannSearch.nextInt();
        System.out.println("_____________________________________");
        
        while (i.hasNext()) {
            Employee e = i.next();
            if (e.getEmpID() == empNum) {
                System.out.println(e);
                found = true;
            }
        }
        if (!found) {
            System.out.println("funcionario não encontrado");
        }
        System.out.println("_____________________________________");
    }

    public static void deleteEmployee(List arrEmployees) {

        Scanner scannDelete = new Scanner(System.in);
        boolean found = false;

        System.out.println("Insira o ID do funcionario: ");
        int empNum = Integer.parseInt(scannDelete.nextLine());
        System.out.println("_____________________________________");
        Iterator<Employee> i = arrEmployees.iterator();

        while (i.hasNext()) {
            Employee e = i.next();
            if (e.getEmpID() == empNum) {
                i.remove();
                found = true;
            }
        }
        if (!found) {
            System.out.println("Funcionario não encontrado");
        } else {
            System.out.println("Funcionario deletado com sucesso");
        }
        System.out.println("_____________________________________");
    }

    public static void updateEmployee(List arrEmployees) {
        Scanner scannUpdate = new Scanner(System.in);
        boolean found = false;

        System.out.println("Insira o ID do funcionario: ");
        int empNum = Integer.parseInt(scannUpdate.nextLine());
        System.out.println("_____________________________________");
        ListIterator<Employee> li = arrEmployees.listIterator();

        while (li.hasNext()) {
            Employee e = li.next();
            if (e.getEmpID() == empNum) {
                System.out.println("Insira o novo nome do funcionario: ");
                String eName = scannUpdate.nextLine();
                System.out.println("Insira o novo salário do funcionario: ");
                float eSalary = Float.parseFloat(scannUpdate.nextLine());

                int eNum = empNum;
                li.set(new Employee(eNum, eName, eSalary));
                found = true;
            }
        }
        if (!found) {
            System.out.println("funcionario não encontrado");
        } else {
            System.out.println("funcionario atualizado com sucesso");
        }
        System.out.println("_____________________________________");

    }
}