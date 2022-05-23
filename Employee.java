import java.util.*;

class Employee {
    private int empID;
    private String employeeName;
    private int salary;

    Employee(int empID, String employeeName, int salary) {
        this.empID = empID;
        this.employeeName = employeeName;
        this.salary = salary;
    }

    public int getEmpID() {
        return empID;
    }

    public int getSalary() {
        return salary;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public String toString() {
        return empID + "" + employeeName + "" + salary;
    }
}

class CRUD {
    public static void main(String[] args) {

        List<Employee> arrEmployees = new ArrayList<Employee>();
        Scanner scann = new Scanner(System.in);
        Scanner scannString = new Scanner(System.in);
        int ch;
        do {
            System.out.println("\n");
            System.out.println("1.INSERIR NOVO FUNCIONÁRIO");
            System.out.println("2.EXIBIR FUNCIONÁRIOS");
            System.out.println("3.PROCURAR FUNCIONÁRIO");
            System.out.println("4.DELETAR FUNCIONÁRIO");
            System.out.println("5.ATUALIZAR DADOS DO FUNCIONÁRIO");
            System.out.println("Digite o numero da opção: ");
            ch = scann.nextInt();

            switch (ch) {

                case 1:
                    System.out.println("Insira o ID do funcionário: ");
                    int eNum = scann.nextInt();
                    System.out.println("Insira o nome do funcionário: ");
                    String eName = scannString.nextLine();
                    System.out.println("Insira o salário do funcionário: ");
                    int eSalary = scann.nextInt();

                    arrEmployees.add(new Employee(eNum, eName, eSalary));
                    break;

                case 2:
                    System.out.println("_____________________________________");
                    Iterator<Employee> i = arrEmployees.iterator();
                    while (i.hasNext()) {
                        Employee e = i.next();
                        System.out.println(e);
                    }
                    System.out.println("_____________________________________");
                    break;

                case 3:
                    boolean found = false;
                    System.out.println("Insira o ID de busca: ");
                    int empNum = scann.nextInt();
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
                        System.out.println("Funcionário não encontrado");
                    }
                    System.out.println("_____________________________________");
                    break;

                case 4:
                    found = false;
                    System.out.println("Insira o ID do funcionário: ");
                    empNum = scann.nextInt();
                    System.out.println("_____________________________________");
                    i = arrEmployees.iterator();
                    while (i.hasNext()) {
                        Employee e = i.next();
                        if (e.getEmpID() == empNum) {
                            i.remove();
                            found = true;
                        }
                    }
                    if (!found) {
                        System.out.println("Funcionário não encontrado");
                    }
                    else{
                        System.out.println("Funcionário deletado com sucesso");
                    }
                    System.out.println("_____________________________________");
                    break;
                    
                    case 5:
                    found = false;
                    System.out.println("Insira o ID do funcionário: ");
                    empNum = scann.nextInt();
                    System.out.println("_____________________________________");
                    ListIterator<Employee>li= arrEmployees.listIterator();
                    while (li.hasNext()) {
                        Employee e = li.next();
                        if (e.getEmpID() == empNum) {
                            System.out.println("Insira o novo nome do funcionário: ");
                            eName = scannString.nextLine();
                            System.out.println("Insira o novo salário do funcionário: ");
                            eSalary = scann.nextInt();

                            eNum = empNum;
                            li.set(new Employee(eNum, eName, eSalary));
                            found = true;
                        }
                    }
                    if (!found) {
                        System.out.println("Funcionário não encontrado");
                    }
                    else{
                        System.out.println("Funcionário atualizado com sucesso");
                    }
                    System.out.println("_____________________________________");
                    break;

            }
        } while (ch != 0);
    }
}
