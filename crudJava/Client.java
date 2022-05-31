package crudJava;

import java.io.IOException;
import java.lang.Runnable;
import java.net.Socket;
import java.util.Scanner;
import functions.*;

public class Client implements Crud{
    Socket socket;

    public void comunicarComServidor() throws Exception {
        String textoRecebido = "";
        int ch = 10;

        do {
            socket = new Socket("localhost", 9600);
            Employee emp;

            Scanner input = new Scanner(System.in);

            Crud.showMenu();

            try {
                ch = Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("\nYou should only input numbers!");
            }

            // Enviar mensagem para o servidor
            Conexao.enviarInt(socket, ch);

            // Receber mensagem do servidor
            textoRecebido = Conexao.receber(socket);

            System.out.println("Resposta do Servidor:\n" + textoRecebido);

            switch (ch) {
                case 0:
                    System.out.println("Exitting...");
                    break;
                case 1:
                    Conexao.enviarInt(socket, Crud.addID(input));
                    Conexao.enviar(socket, Crud.addName(input));
                    Conexao.enviarFloat(socket, Crud.addSalary(input));
                    break;
                case 2:
                    textoRecebido = Conexao.receber(socket);
                    System.out.println("------------------------");
                    System.out.println(textoRecebido);
                    System.out.println("------------------------");
                    break;
                default:
                    break;
            }
        } while (ch != 0);

    }

    public static void main(String[] args) {
        try {
            Client cliente = new Client();
            cliente.comunicarComServidor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
