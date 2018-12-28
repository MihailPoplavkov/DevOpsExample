import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Writer {
    private static byte[] data = "hello\n".getBytes();

    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(8080);
        Socket s = ss.accept();
        System.out.println("Client accepted");
        OutputStream os = s.getOutputStream();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < i; j++) {
                os.write(' ');
            }
            os.write(data);
        }
        s.close();
    }
}
