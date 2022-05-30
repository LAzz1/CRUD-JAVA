import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import functions.*;

public class Client {
    public void comunicarServidor() throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress address = InetAddress.getByName("localhost");
        List<Employee> arrEmployees = new ArrayList<Employee>();
        int port = 5252;

        String texto = "";
        Scanner entrada = new Scanner(System.in);

        while (!texto.trim().equalsIgnoreCase("fim")) {
            Crud.showMenu();
            texto = entrada.nextLine();
            byte saida[];
            saida = texto.getBytes();
            try {
                int ch = Integer.parseInt(texto);
                switch (ch) {
                    case 1:
                        Crud.addEmployee();
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
                        Crud.addEmployee();
                        break;
                    default:
                        System.out.println("Insira um numero dentre as opções do Menu");
                        comunicarServidor();
                        break;
                }
            } catch (Exception e) {
                System.out.println("Error, digite apenas numeros entre 0 e 5");
            }
        }
    }

    public static void main(String[] args) {
        try {
            Client cliente = new Client();
            cliente.comunicarServidor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
