package crudJava;
import java.util.*;

public interface Crud {
    public static Employee addEmployee(){
        Scanner scann2 = new Scanner(System.in);
    
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
}
