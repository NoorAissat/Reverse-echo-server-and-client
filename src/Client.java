import java.net.Socket;
import java.io.*;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {

        //inputStreamWriter will handle data coming in and outputStreamWriter handles data flowing to server
        //BufferedWriter and reader will allow us to have more characters at a time and will allow us to flush when full

        Socket socket ;
        InputStreamReader inputStreamReader ;
        OutputStreamWriter outputStreamWriter ;
        BufferedWriter writer;
        BufferedReader reader;
        try {

            String ip = "10.0.0.165";
            int port = 12000;

            socket = new Socket(ip, port);


            inputStreamReader = new InputStreamReader(socket.getInputStream());
            outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());

            //wrap input and output StreamWriter into buffered reader and writer

            writer = new BufferedWriter(outputStreamWriter);
            reader = new BufferedReader(inputStreamReader);

            Scanner sc = new Scanner(System.in);

            //message sends to server and flush after message is entered
            while (true){
                String sendMessage = sc.nextLine();
                writer.write(sendMessage);
                writer.newLine();
                writer.flush();

                //Server sends data back
                System.out.println("From server:"+reader.readLine());
                //ends connection with server and close all open streams
                if(sendMessage.equalsIgnoreCase("end")){
                    socket.close();
                    inputStreamReader.close();
                    outputStreamWriter.close();
                    writer.close();
                    reader.close();
                    break;
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

