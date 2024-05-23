import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class LocalDNS_Client_김민수_20203037 {
    public static void main(String[] args) {
        BufferedReader in = null;
        BufferedWriter out = null;
        Socket socket = null;

        Scanner sc = new Scanner(System.in);

        try{
            socket = new Socket("127.0.0.1", 9999);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            while(true){
                System.out.print(">>");
                String s = sc.nextLine();

                if(s.equalsIgnoreCase("q")){
                    out.write(s+"\n");
                    out.flush();
                    break;
                }

                out.write(s+"\n");
                out.flush();
                String i = in.readLine();
                System.out.println(i);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
