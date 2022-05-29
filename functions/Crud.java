package functions;
import java.util.Scanner;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface Crud {

    public static void showMenu() {
        System.out.println("\n");
        System.out.println("1.INSERIR NOVO FUNCIONARIO");
        System.out.println("2.EXIBIR FUNCIONARIO");
        System.out.println("3.PROCURAR FUNCIONARIO");
        System.out.println("4.DELETAR FUNCIONARIO");
        System.out.println("5.ATUALIZAR DADOS DO FUNCIONARIO");
        System.out.println("0.SAIR");
        System.out.printf("\nDigite o numero da opcao: ");
    }

    public static Employee addEmployee() {

        Scanner scannAdd = new Scanner(System.in);

        int eNum = 0;
        float eSalary = 0;
        String eName = "sample";

        return new Employee(addID(eNum, scannAdd), addName(eName, scannAdd), addSalary(eSalary, scannAdd));
    }

    private static int addID(int eNum, Scanner scannAdd) {
        try {
            System.out.printf("\nInsira o ID do funcionário: ");
            eNum = Integer.parseInt(scannAdd.nextLine());
            return eNum;
        } catch (NumberFormatException e) {
            System.out.println("\nO ID deve conter apenas numeros. Tente um valor valido.");
            return addID(eNum, scannAdd);
        }
    }

    private static String addName(String eName, Scanner scannAdd) {
        System.out.printf("\nInsira o nome do funcionário: ");
        eName = scannAdd.nextLine();
        Pattern pattern = Pattern.compile("[0-9]", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(eName);
        boolean matchFound = matcher.find();
        if (matchFound) {
            System.out.println("\nO nome dos funcionários não devem conter números.");
            return addName(eName, scannAdd);
        } else {
            return eName;
        }
    }

    private static float addSalary(float eSalary, Scanner scannAdd) {
        try {
            System.out.printf("\nInsira o valor mensal pago ao funcionário: ");
            eSalary = Float.parseFloat(scannAdd.nextLine());
            if (eSalary < 810) {
                System.out.println("\nPor favor insira um valor correspondente ou maior ao minimo mensal (R$810).");
                return addSalary(eSalary, scannAdd);
            }
            return eSalary;
        } catch (NumberFormatException e) {
            System.out.println("\nPara o salário é valido apenas números. Tente novamente.");
            return addSalary(eSalary, scannAdd);
        }
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
        try {
            System.out.printf("Insira o ID de busca: ");
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
                System.out.println("\nFuncionario não encontrado");
            }
            System.out.println("_____________________________________");
        } catch (Exception e) {
            System.out.println("\nOcorreu um erro na validação do ID. Tente novamente apenas com numeros.");
            searchEmployee(arrEmployees);
        }
    }

    public static void deleteEmployee(List arrEmployees) {

        Scanner scannDelete = new Scanner(System.in);
        boolean found = false;
        try{
        System.out.printf("Insira o ID do funcionario: ");
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
        }catch (Exception e) {
            System.out.println("\nOcorreu um erro na validação do ID. Tente novamente apenas com numeros.");
            deleteEmployee(arrEmployees);
        }
    }

    public static void updateEmployee(List arrEmployees) {
        Scanner scannUpdate = new Scanner(System.in);
        boolean found = false;
        try{
        System.out.printf("Insira o ID do funcionario: ");
        int empNum = Integer.parseInt(scannUpdate.nextLine());
        System.out.println("_____________________________________");
        ListIterator<Employee> li = arrEmployees.listIterator();

        while (li.hasNext()) {
            Employee e = li.next();
            if (e.getEmpID() == empNum) {
                System.out.printf("Insira o novo nome do funcionario: ");
                String eName = scannUpdate.nextLine();
                System.out.printf("Insira o novo salário do funcionario: ");
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
    }catch (Exception e) {
        System.out.println("\nOcorreu um erro na validação do ID. Tente novamente apenas com numeros.");
        updateEmployee(arrEmployees);
    }
    }
}