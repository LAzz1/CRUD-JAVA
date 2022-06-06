package functions;

import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface Crud {

    public static void showMenu(threadAux p1) {
        new Thread(p1).start();
    }

    private static void showEmpTypes() {
        System.out.println("1. Estagiários [R$ 810,00   - R$ 1.400,00]");
        System.out.println("2. Júnior      [R$ 2.000,00 - R$ 3.500,00]");
        System.out.println("3. Pleno       [R$ 4.000,00 - R$ 5.000,00]");
        System.out.println("4. Sênior      [R$ 5.500,00   em   diante]");
        System.out.printf("\nDigite o número da opção: ");
    }

    public static void receiveEmployees(String textoRecebido) {
        System.out.println("-----------------------------------");
        System.out.println("ID\tName\t\tSalary");
        String textoSplit[] = textoRecebido.split("\\[|,|\\]");
        for (String t : textoSplit) {
            System.out.println(t.trim());
        }
        System.out.println("-----------------------------------");
    }

    public static int addID(Scanner scannAdd) {
        int eNum;
        try {
            System.out.printf("Insira o ID do funcionário: ");
            eNum = Integer.parseInt(scannAdd.nextLine());
            return eNum;
        } catch (NumberFormatException e) {
            System.out.println("\nO ID deve conter apenas números. Tente um valor válido.");
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
                System.out.println("\nPor favor insira um valor correspondente ou maior ao mínimo mensal (R$810).");
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

    public static int sendInt(Scanner scannSend) {
        Crud.showEmpTypes();
        scannSend = new Scanner(System.in);
        try {
            int choice = Integer.parseInt(scannSend.nextLine());
            return choice;
        } catch (NumberFormatException e) {
            System.out.println("\nInsira apenas números: [ 1 -> 4]\n");
            return sendInt(scannSend);
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
            System.out.println("1. INSERIR NOVO FUNCIONÁRIO");
            System.out.println("2. EXIBIR FUNCIONÁRIO");
            System.out.println("3. PROCURAR FUNCIONÁRIO");
            System.out.println("4. DELETAR FUNCIONÁRIO");
            System.out.println("5. ATUALIZAR DADOS DO FUNCIONÁRIO");
            System.out.println("6. PROCURAR FUNCIONÁRIO POR SALÁRIO");
            System.out.println("0. SAIR");
            System.out.printf("\nDigite o número da opção: ");
            this.stop();
        }

    }
}