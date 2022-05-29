import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import functions.*;

public class Server {
    Socket socketClient;
    ServerSocket serversocket;

    public boolean connect() {
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

    public void rodarServidor() throws Exception {
        List<Employee> arrEmployees = new ArrayList<Employee>();
        String textoRecebido = "";
        String textoEnviado = "Olá, Cliente";
        Scanner input = new Scanner(System.in);

        serversocket = new ServerSocket(9600);
        System.out.println("Servidor iniciado!");

        while (true) {
            if (connect()) {
                try {
                    textoRecebido = Conexao.receber(socketClient);
                    int ch = Integer.parseInt(textoRecebido);
                    System.out.println("Cliente enviou: " + ch);
                    switch (ch) {
                        case 0:
                            Conexao.enviar(socketClient, "Voce escolheu sair do programa");
                            socketClient.close();
                            break;
                        case 1:
                            Conexao.enviar(socketClient, "Voce escolheu adicionar um novo funcionario");
                            break;

                        case 2:
                            Conexao.enviar(socketClient, "Voce escolheu visualizar os funcionarios");
                            break;

                        case 3:
                            Conexao.enviar(socketClient, "Voce escolheu procurar por um funcionario");
                            break;

                        case 4:
                            Conexao.enviar(socketClient, "Voce escolheu deletar um funcionario");
                            break;

                        case 5:
                            Conexao.enviar(socketClient, "Voce escolheu atualizar um funcionario");
                            break;
                        default:
                        Conexao.enviar(socketClient,"Opcao invalida, escolha um valor entre 0 e 5");
                        break;
                    }
                    socketClient.close();

                } catch (Exception e) {
                    String errorMsg = "\nError, envie apenas numeros entre 0 e 5";
                    Conexao.enviar(socketClient, errorMsg);
                    socketClient.close();
                }
            }
        }
    }

}
