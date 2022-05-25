import java.util.*;

public class Main implements Crud {
    public static void main(String[] args) {

        List<Employee> arrEmployees = new ArrayList<Employee>();
        Scanner scann = new Scanner(System.in);
        int ch;
        do {
            System.out.println("\n");
            System.out.println("1.INSERIR NOVO FUNCIONARIO");
            System.out.println("2.EXIBIR FUNCIONARIO");
            System.out.println("3.PROCURAR FUNCIONARIO");
            System.out.println("4.DELETAR FUNCIONARIO");
            System.out.println("5.ATUALIZAR DADOS DO FUNCIONARIO");
            System.out.println("\nDigite o numero da opção: ");
            ch = Integer.parseInt(scann.nextLine());

            switch (ch) {

                case 1:
                    arrEmployees.add(Crud.addEmployee());
                    break;

                case 2:
                    Crud.readEmployees(arrEmployees);
                    break;

                case 3:
                    Crud.searchEmployee(arrEmployees);
                    break;

                case 4:
                    Crud.deleteEmployee(arrEmployees);
                    break;

                case 5:
                    Crud.updateEmployee(arrEmployees);
                    break;

            }
        } while (ch != 0);

        //scann.close();
    }
}