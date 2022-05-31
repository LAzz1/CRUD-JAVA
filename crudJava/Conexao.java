package crudJava;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class Conexao {

    public static String receber(Socket socket) throws IOException {
        InputStream in = socket.getInputStream();
        byte infoBytes[] = new byte[100];
        int bytesLidos = in.read(infoBytes);

        if (bytesLidos > 0) {
            return new String(infoBytes);
        } else {
            return "";
        }
    }

    public static int receberInt(Socket socket) throws IOException {
        InputStream in = socket.getInputStream();
        int bytesLidos = in.read();

        return bytesLidos;
    }

    public static float receberFloat(Socket socket) throws IOException {
        DataInputStream dataIn = new DataInputStream(socket.getInputStream());
        float floatLido = dataIn.readFloat();

        return floatLido;
    }

    public static void enviar(Socket socket, String textoRequisicao) throws IOException {
        OutputStream out = socket.getOutputStream();
        out.write(textoRequisicao.getBytes());
    }

    public static void enviarInt(Socket socket, int integer) throws IOException {
        OutputStream out = socket.getOutputStream();
        out.write(integer);
    }

    public static void enviarFloat(Socket socket, float floatPoint) throws IOException {
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        out.writeFloat(floatPoint);
    }
}
