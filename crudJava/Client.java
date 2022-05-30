package crudJava;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorCompletionService;
import functions.*;

public class Client {
    Socket socket;

    public void comunicarComServidor() throws Exception {
        String textoRequisicao = "Conexao estabelecida";
        String textoRecebido = "";
        socket = new Socket("localhost", 9600);
        int ch = 10;

        Scanner input = new Scanner(System.in);
        Crud.showMenu();
        ch = Integer.parseInt(input.nextLine());

        switch (ch) {
            case 1:
                Crud.addEmployee();
                break;
        
            default:
                break;
        }
        // Enviar mensagem para o servidor
        Conexao.enviar(socket, textoRequisicao);

        // Receber mensagem do servidor
        textoRecebido = Conexao.receber(socket);

        System.out.println("Resposta do Servidor:\n" + textoRecebido);
    }


    public static void main(String[] args) {
        try {
            Client cliente = new Client();
            cliente.comunicarComServidor();
        } catch(Exception e){
            e.printStackTrace();
        }
    } 
}
