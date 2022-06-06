package crudJava;

import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import functions.Crud;

public class Client implements Crud {
    Socket socket;

    public void comunicarComServidor() throws UnknownHostException, IOException, InterruptedException {
        String textoRecebido = "";
        int ch;
        threadAux p1 = new threadAux();

        do {
            // Valor padrão para impedir o loop de 'continuar' na mesma opção escolhida anteriormente
            ch = 10;

            socket = new Socket("localhost", 9600);

            Scanner input = new Scanner(System.in);

            Crud.showMenu(p1);

            try {
                ch = Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("\nVocê só pode inserir números! (0 -> 6)");
            }

            Conexao.enviarInt(socket, ch);

            textoRecebido = Conexao.receber(socket);

            System.out.println("\nResposta do Servidor:\n" + textoRecebido);

            switch (ch) {
                case 0:
                    System.out.println("Saindo...");
                    break;

                case 1:
                    Conexao.enviarInt(socket, Crud.addID(input));
                    Conexao.enviar(socket, Crud.addName(input));
                    Conexao.enviarFloat(socket, Crud.addSalary(input));
                    try {
                        textoRecebido = Conexao.receber(socket);
                        System.out.println(textoRecebido);
                        /*
                         * Como se não houver erros ao criar um funcionário o Servidor não mandará
                         * mensagens, teremos um EOFException neste ponto, por isso este "catch()"
                         */
                    } catch (EOFException e) {
                        System.out.println("\nFuncionário cadastrado com sucesso");
                    }
                    break;

                case 2:
                    textoRecebido = Conexao.receber(socket);
                    Crud.receiveEmployees(textoRecebido);
                    break;

                case 3:
                    Conexao.enviarInt(socket, Crud.searchEmployee(input));
                    try {
                        textoRecebido = Conexao.receber(socket);
                        Crud.receiveEmployees(textoRecebido);
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

                case 6:
                    Conexao.enviarInt(socket, Crud.sendInt(input));
                    try {
                        textoRecebido = Conexao.receber(socket);
                        Crud.receiveEmployees(textoRecebido);
                    } catch (EOFException e) {
                        System.out.println("-----------------------------------");
                        System.out.println("Opção inválida. Tente novamente");
                        System.out.println("-----------------------------------");
                    }
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
