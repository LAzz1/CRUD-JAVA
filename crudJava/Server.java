package crudJava;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import functions.CrudServer;
import functions.Employee;

public class Server implements CrudServer {
    Socket clientSocket;
    ServerSocket serverSocket;

    private boolean connect() {
        try {
            clientSocket = serverSocket.accept();
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
        boolean found = false;
        int escolhaRecebida; int empNum = 0;

        serverSocket = new ServerSocket(9600);
        System.out.println("Servidor iniciado!");

        while (true) {
            if (connect()) {
                try {
                    escolhaRecebida = Conexao.receberInt(clientSocket);
                    switch (escolhaRecebida) {
                        case 0:
                            System.out.println("Cliente enviou: " + escolhaRecebida + " - SAIR");
                            Conexao.enviar(clientSocket, "Você escolheu sair do programa\n");
                            clientSocket.close();
                            break;

                        case 1:
                            System.out.println("Cliente enviou: " + escolhaRecebida + " - ADICIONAR");
                            Conexao.enviar(clientSocket, "Você escolheu adicionar um novo funcionário\n");
                            Employee emp = new Employee(Conexao.receberInt(clientSocket), Conexao.receber(clientSocket), Conexao.receberFloat(clientSocket));
                            CrudServer.addEmployee(empNum, arrEmployees, emp, found, clientSocket);
                            break;

                        case 2:
                            System.out.println("Cliente enviou: " + escolhaRecebida + " - LISTAR");
                            Conexao.enviar(clientSocket, "Você escolheu visualizar os funcionários\n");
                            Conexao.enviar(clientSocket, arrEmployees.toString());
                            break;

                        case 3:
                            System.out.println("Cliente enviou: " + escolhaRecebida + " - PROCURAR");
                            Conexao.enviar(clientSocket, "Você escolheu procurar por um funcionário\n");
                            CrudServer.searchEmployee(empNum, arrEmployees, found, clientSocket);
                            break;

                        case 4:
                            System.out.println("Cliente enviou: " + escolhaRecebida + " - DELETAR");
                            Conexao.enviar(clientSocket, "Você escolheu deletar um funcionário\n");
                            CrudServer.deleteEmployee(empNum, arrEmployees, found, clientSocket);
                            break;

                        case 5:
                            System.out.println("Cliente enviou: " + escolhaRecebida + " - ATUALIZAR");
                            Conexao.enviar(clientSocket, "Você escolheu atualizar um funcionário\n");
                            CrudServer.updateEmployee(empNum, arrEmployees, found, clientSocket);
                            break;

                        case 6:
                            System.out.println("Cliente enviou: " + escolhaRecebida + " - PROCURAR SALÁRIO");
                            Conexao.enviar(clientSocket, "Você escolheu procurar funcionários por salário\n");
                            CrudServer.searchBySalary(empNum, arrEmployees, found, clientSocket);
                            break;

                        default:
                            Conexao.enviar(clientSocket, "Opção inválida, escolha um valor entre 0 e 6\n");
                            break;
                    }
                    clientSocket.close();

                } catch (Exception e) {
                    String errorMsg = e.toString();
                    Conexao.enviar(clientSocket, errorMsg);
                    clientSocket.close();
                }
            }
        }
    }

}
