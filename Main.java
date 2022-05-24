package crudJava;

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
                        System.out.println("FUNCIONARIO não encontrado");
                    }
                    System.out.println("_____________________________________");
                    break;

                case 4:
                    found = false;
                    System.out.println("Insira o ID do FUNCIONARIO: ");
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
                        System.out.println("FUNCIONARIO não encontrado");
                    } else {
                        System.out.println("FUNCIONARIO deletado com sucesso");
                    }
                    System.out.println("_____________________________________");
                    break;

                case 5:
                    found = false;
                    System.out.println("Insira o ID do FUNCIONARIO: ");
                    empNum = scann.nextInt();
                    System.out.println("_____________________________________");
                    ListIterator<Employee> li = arrEmployees.listIterator();
                    /*while (li.hasNext()) {
                        Employee e = li.next();
                        if (e.getEmpID() == empNum) {
                            System.out.println("Insira o novo nome do FUNCIONARIO: ");
                            eName = scannString.nextLine();
                            System.out.println("Insira o novo salário do FUNCIONARIO: ");
                            eSalary = scann.nextInt();

                            eNum = empNum;
                            li.set(new Employee(eNum, eName, eSalary));
                            found = true;
                        }
                    }*/
                    if (!found) {
                        System.out.println("FUNCIONARIO não encontrado");
                    } else {
                        System.out.println("FUNCIONARIO atualizado com sucesso");
                    }
                    System.out.println("_____________________________________");
                    break;

            }
        } while (ch != 0);

        scann.close();
    }
}
