package functions;

import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface Crud {

    public static void showMenu() {
        threadAux p1 = new threadAux();
        new Thread(p1).start();
    }

    public static int addID(Scanner scannAdd) {
        // Caso o codigo quebre, redeclarar as variaveis eNum, eName e eSalary nas
        // funcoes "Add"
        int eNum;
        try {
            System.out.printf("Insira o ID do funcionário: ");
            eNum = Integer.parseInt(scannAdd.nextLine());
            return eNum;
        } catch (NumberFormatException e) {
            System.out.println("\nO ID deve conter apenas numeros. Tente um valor valido.");
            return addID(scannAdd);
        }
    }

    public static String addName(Scanner scannAdd) {
        String eName;
        System.out.printf("Insira o nome do funcionário: ");
        eName = scannAdd.nextLine();
        Pattern pattern = Pattern.compile("[0-9]", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(eName);
        boolean matchFound = matcher.find();
        if (matchFound) {
            System.out.println("\nO nome dos funcionários não devem conter números.");
            return addName(scannAdd);
        } else {
            return eName;
        }
    }

    public static float addSalary(Scanner scannAdd) {
        float eSalary;
        try {
            System.out.printf("Insira o valor mensal pago ao funcionário: ");
            eSalary = Float.parseFloat(scannAdd.nextLine());
            if (eSalary < 810) {
                System.out.println("\nPor favor insira um valor correspondente ou maior ao minimo mensal (R$810).");
                return addSalary(scannAdd);
            }
            return eSalary;
        } catch (NumberFormatException e) {
            System.out.println("\nPara o salário é valido apenas números. Tente novamente.");
            return addSalary(scannAdd);
        }
    }

    public static int searchEmployee(Scanner scannSearch) {

        scannSearch = new Scanner(System.in);
        try {
            System.out.printf("Insira o ID do funcionário: ");
            int empID = Integer.parseInt(scannSearch.nextLine());
            return empID;
        } catch (NumberFormatException e) {
            System.out.println("\nOcorreu um erro na validação do ID. Tente novamente apenas com numeros.");
            return searchEmployee(scannSearch);
        }
    }

    public static class threadAux implements Runnable {
        private final AtomicBoolean running = new AtomicBoolean(false);

        private void stop(){
            running.set(false);
        }

        @Override
        public void run() {
            running.set(true);
            try {
                Thread.sleep(500); System.out.printf("\n . ");
                Thread.sleep(500); System.out.printf("\t.");
                Thread.sleep(500); System.out.printf("\t.");
                Thread.sleep(500); System.out.printf("\t.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("\n");
            System.out.println("1.INSERIR NOVO FUNCIONARIO");
            System.out.println("2.EXIBIR FUNCIONARIO");
            System.out.println("3.PROCURAR FUNCIONARIO");
            System.out.println("4.DELETAR FUNCIONARIO");
            System.out.println("5.ATUALIZAR DADOS DO FUNCIONARIO");
            System.out.println("0.SAIR");
            System.out.printf("\nDigite o numero da opcao: ");
            this.stop();
        }

    }
}