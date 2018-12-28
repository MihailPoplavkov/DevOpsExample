import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Reader {
    private static String writerHost = System.getenv("WRITER_HOST");
    static {
        System.out.println("Writer host: " + writerHost);
    }
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(8081);
        Socket outSocket = ss.accept();
        System.out.println("Client accepted " + writerHost);

        Socket inSocket = new Socket(writerHost, 8080);
        InputStream is = inSocket.getInputStream();
        StringBuilder sb = new StringBuilder();
        int b;
        while ((b = is.read()) != -1) {
            sb.append((char)b);
        }
        inSocket.close();

        OutputStream os = outSocket.getOutputStream();
        String response = "HTTP/1.1 200 OK\n" +
                "Content-Type: text/html\n\n" +
                sb.toString();
        os.write(response.getBytes());
        outSocket.close();
    }
}
