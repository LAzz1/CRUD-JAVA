import java.util.*;

public class Main implements Crud {
    
    public static void main(String[] args) {

        List<Employee> arrEmployees = new ArrayList<Employee>();
        Scanner scann = new Scanner(System.in);
        int ch = 0;
        do {
            
            Crud.showMenu();
            try {
                ch = Integer.parseInt(scann.nextLine());
            } catch (Exception e) {
                ch = 10;
                System.out.println("\nVocê não digitou um número válido. Tente novamente!");
            }

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

        scann.close();
    }
}