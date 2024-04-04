import java.net.ServerSocket;
import java.net.Socket;

import java.io.*;
public class Server {
    public static void main(String[] args) throws IOException {
        //let us communicate through two computers using TCP
        Socket socket ;
        //a bridge from byte stream to character stream vise versa
        InputStreamReader inputStreamReader ;
        OutputStreamWriter outputStreamWriter ;
        BufferedWriter writer;
        BufferedReader reader ;
        //server socket will wait for a connection on port 12000
        ServerSocket serverSocket;

        serverSocket = new ServerSocket(12000);

        //ensures server is constantly running
        while (true)
            try {
                {
                    //once connected socket object can communicate with client
                    socket = serverSocket.accept();

                    inputStreamReader = new InputStreamReader(socket.getInputStream());
                    outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
                    writer = new BufferedWriter(outputStreamWriter);
                    reader = new BufferedReader(inputStreamReader);
                    //when the client connects the server continues to interact until disconnected
                    while (true) {

                        //Reads the message from the client
                        String messageFromClient = reader.readLine();
                        //reverses String inputs from client
                        String reverse = new StringBuilder(messageFromClient).reverse().toString();


                        writer.write(reverse);
                        writer.newLine();
                        writer.flush();
                        //ends connection and returns dne

                        if(messageFromClient.equalsIgnoreCase("end")){
                            socket.close();
                            inputStreamReader.close();
                            outputStreamWriter.close();
                            writer.close();
                            reader.close();

                            return;
                        }

                    }

                }



            } catch (IOException e) {
                e.printStackTrace();
            }
    }
}

