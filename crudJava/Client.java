package crudJava;

import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import functions.*;

public class Client implements Crud {
    Socket socket;

    public void comunicarComServidor() throws UnknownHostException, IOException, InterruptedException {
        String textoRecebido = "";
        int ch = 0;

        do {
            socket = new Socket("localhost", 9600);

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

            System.out.println("\nResposta do Servidor:\n" + textoRecebido);

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
                    this.receiveEmployees(textoRecebido);
                    break;

                case 3:
                    Conexao.enviarInt(socket, Crud.searchEmployee(input));
                    try {
                        textoRecebido = Conexao.receber(socket);
                        this.receiveEmployees(textoRecebido);
                    } catch (EOFException e) {
                        System.out.println("-----------------------------------");
                        System.out.println("ID inválido. Tente novamente");
                        System.out.println("-----------------------------------");
                    }
                    break;

                case 4:
                    Conexao.enviarInt(socket, Crud.searchEmployee(input));
                    textoRecebido = Conexao.receber(socket);
                    System.out.println(textoRecebido);
                    break;

                case 5:
                    Conexao.enviarInt(socket, Crud.addID(input));
                    Conexao.enviar(socket, Crud.addName(input));
                    Conexao.enviarFloat(socket, Crud.addSalary(input));
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

    private void receiveEmployees(String textoRecebido) {
        System.out.println("-----------------------------------");
        System.out.println("ID\tName\t\tSalary");
        String textoSplit[] = textoRecebido.split("\\[|,|\\]");
        for (String t : textoSplit) {
            System.out.println(t.trim());
        }
        System.out.println("-----------------------------------");
    }

}
