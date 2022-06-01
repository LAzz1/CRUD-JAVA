package crudJava;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import functions.*;

public class Server implements Crud {
    Socket socketClient;
    ServerSocket serversocket;

    private boolean connect() {
        try {
            socketClient = serversocket.accept(); // fase de conexao
            return true;
        } catch (IOException e) {
            System.err.println("Nao fez conexao" + e.getMessage());
            return false;
        }
    }

    public static void main(String[] args) {
        try {
            Server servidor = new Server();
            servidor.rodarServidor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void rodarServidor() throws Exception {
        List<Employee> arrEmployees = new ArrayList<Employee>();
        int escolhaRecebida;
        //Scanner input = new Scanner(System.in);

        serversocket = new ServerSocket(9600);
        System.out.println("Servidor iniciado!");

        while (true) {
            if (connect()) {
                try {
                    escolhaRecebida = Conexao.receberInt(socketClient);
                    System.out.println("Cliente enviou: " + escolhaRecebida);
                    switch (escolhaRecebida) {
                        case 0:
                            Conexao.enviar(socketClient, "Voce escolheu sair do programa");
                            socketClient.close();
                            break;

                        case 1:
                            Conexao.enviar(socketClient, "Voce escolheu adicionar um novo funcionario");
                            arrEmployees.add(new Employee(Conexao.receberInt(socketClient), Conexao.receber(socketClient), Conexao.receberFloat(socketClient)));
                            break;

                        case 2:
                            Conexao.enviar(socketClient, "Voce escolheu visualizar os funcionarios");
                            Conexao.enviar(socketClient, arrEmployees.toString());
                            break;

                        case 3:
                            Conexao.enviar(socketClient, "Voce escolheu procurar por um funcionario");
                            Crud.searchEmployee(arrEmployees);
                            break;

                        case 4:
                            Conexao.enviar(socketClient, "Voce escolheu deletar um funcionario");
                            Crud.deleteEmployee(arrEmployees);
                            break;

                        case 5:
                            Conexao.enviar(socketClient, "Voce escolheu atualizar um funcionario");
                            Crud.updateEmployee(arrEmployees);
                            break;
                        default:
                            Conexao.enviar(socketClient, "Opcao invalida, escolha um valor entre 0 e 5");
                            break;
                    }
                    socketClient.close();

                } catch (Exception e) {
                    String errorMsg = e.toString();
                    Conexao.enviar(socketClient, errorMsg);
                    socketClient.close();
                }
            }
        }
    }

}
