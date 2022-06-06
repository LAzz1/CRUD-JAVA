package crudJava;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;


public class Conexao {

    public static String receber(Socket socket) throws IOException {
        DataInputStream dis=new DataInputStream(socket.getInputStream());  
        String str=(String)dis.readUTF();
        return str;
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

    public static void enviar(Socket socket, String texto) throws IOException {
        DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
        dout.writeUTF(texto);
    }

    public static void enviarInt(Socket socket, int numInt) throws IOException {
        OutputStream out = socket.getOutputStream();
        out.write(numInt);
    }

    public static void enviarFloat(Socket socket, float numFloat) throws IOException {
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        out.writeFloat(numFloat);
    }
}
