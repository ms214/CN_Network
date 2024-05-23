import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class LocalDNS_Server_김민수_20203037 {
    public static void main(String[] args) {
        BufferedReader in = null;
        BufferedWriter out = null;

        try{
            ServerSocket serverSocket = new ServerSocket(9999); //9999 포트로 서버 오픈
            System.out.println("Ready");
            Socket socket = serverSocket.accept(); // 소켓 연결
            System.out.println("Accepted");

            in = new BufferedReader(new InputStreamReader(socket.getInputStream())); // client로부터 들어온 입력
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())); // client로 보내는 출력

            while(true){
                String str = in.readLine();

                if(str.equalsIgnoreCase("q")){
                    System.out.println("Finish");
                    break;
                }
                String[] sp_str = str.split(":");
                if(sp_str[0].equalsIgnoreCase("N")){
                    // 파일에서 sp_str[1]의 도메인에 맞는 아이피주소 리턴
                    String tmp = getIP(sp_str[1].strip());
                    out.write(tmp+"\n");
                    out.flush();
                }else if(sp_str[0].equalsIgnoreCase("R")){
                    // 파일에서 sp_str[1]의 아이피주소에 맞는 도메인 리턴
                    String tmp = getDomain(sp_str[1].strip());
                    out.write(tmp+"\n");
                    out.flush();
                }else{
                    out.write("Wrong Input Format\n");
                    out.flush();
                }
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    static String getDomain(String ip) throws FileNotFoundException {
        Scanner fileScan = new Scanner(new File("./data/LocalDNS.txt"));
        while(fileScan.hasNext()){
            String[] tmp = fileScan.nextLine().split(" ");
            if(tmp[1].equals(ip)) return tmp[0];
        }
        return "Not Found";
    }

    static String getIP(String domain) throws FileNotFoundException {
        Scanner fileScan = new Scanner(new File("./data/LocalDNS.txt"));
        while(fileScan.hasNext()){
            String[] tmp = fileScan.nextLine().split(" ");
            if(tmp[0].equalsIgnoreCase(domain)) return tmp[1];
        }
        return "Not Found";
    }
}