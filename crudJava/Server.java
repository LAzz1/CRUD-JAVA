package crudJava;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

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
        boolean found = false;
        int escolhaRecebida; int empNum;
        // Scanner input = new Scanner(System.in);

        serversocket = new ServerSocket(9600);
        System.out.println("Servidor iniciado!");

        while (true) {
            if (connect()) {
                try {
                    escolhaRecebida = Conexao.receberInt(socketClient);
                    System.out.println("Cliente enviou: " + escolhaRecebida);
                    switch (escolhaRecebida) {
                        case 0:
                            Conexao.enviar(socketClient, "Voce escolheu sair do programa\n");
                            socketClient.close();
                            break;

                        case 1:
                            Conexao.enviar(socketClient, "Voce escolheu adicionar um novo funcionario\n");
                            arrEmployees.add(new Employee(Conexao.receberInt(socketClient),Conexao.receber(socketClient), Conexao.receberFloat(socketClient)));
                            break;

                        case 2:
                            Conexao.enviar(socketClient, "Voce escolheu visualizar os funcionarios\n");
                            Conexao.enviar(socketClient, arrEmployees.toString());
                            break;

                        case 3:
                            Conexao.enviar(socketClient, "Voce escolheu procurar por um funcionario\n");
                            empNum = Conexao.receberInt(socketClient);
                            for (Iterator<Employee> i = arrEmployees.iterator(); i.hasNext(); ) {
                                Employee e = i.next();
                                if (e.getEmpID() == empNum) {
                                    Conexao.enviar(socketClient, e.toString());
                                    found = true;
                                }
                            }
                            break;
                        case 4:
                            Conexao.enviar(socketClient, "Voce escolheu deletar um funcionario\n");
                            empNum = Conexao.receberInt(socketClient); 
                            for (Iterator<Employee> i = arrEmployees.iterator(); i.hasNext(); ) {
                                Employee e = i.next();
                                if (e.getEmpID() == empNum) {
                                    i.remove();
                                    found = true;
                                }
                            }
                            if (!found) {
                                Conexao.enviar(socketClient,"\nFuncionario não encontrado");
                            } else {
                                Conexao.enviar(socketClient,"\nFuncionario deletado com sucesso");
                            } 
                            //Conexao.enviar(socketClient,"Funcionario deletado com sucesso");
                            break;

                        case 5:
                            Conexao.enviar(socketClient, "Voce escolheu atualizar um funcionario\n");
                            empNum = Conexao.receberInt(socketClient); 
                            for (ListIterator<Employee> li = arrEmployees.listIterator(); li.hasNext(); ) {
                                Employee e = li.next();
                                if (e.getEmpID() == empNum) {
                                    li.set(new Employee(empNum,Conexao.receber(socketClient), Conexao.receberFloat(socketClient)));
                                    found = true;
                                }
                            }
                            if (!found) {
                                Conexao.enviar(socketClient,"Funcionario não encontrado");
                            } else {
                                //arrEmployees.add(new Employee(empNum,Conexao.receber(socketClient), Conexao.receberFloat(socketClient)));
                                Conexao.enviar(socketClient,"Funcionario atualizado com sucesso");
                            }
                            break;

                        default:
                            Conexao.enviar(socketClient, "Opcao invalida, escolha um valor entre 0 e 5\n");
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

    /* public static class searchThread implements Runnable {
        private final Socket socketClient;
        private List<Employee> arrEmployees;

        public searchThread(Socket socketClient, List<Employee> arrEmployees) {
            this.socketClient = socketClient;
            this.arrEmployees = arrEmployees;
        }

        @Override
        public void run() {

        }

    } */
}
