package TCP;
import java.io.*;
import java.net.*;
import java.util.*;

public class TCPServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("Servidor a l'espera de connexions...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Connexió establerta amb el client: " + clientSocket);

                ObjectOutputStream outToClient = new ObjectOutputStream(clientSocket.getOutputStream());
                ObjectInputStream inFromClient = new ObjectInputStream(clientSocket.getInputStream());

                Llista receivedList = (Llista) inFromClient.readObject();
                System.out.println("Llista rebuda del client: " + receivedList.getNom() + " - " + receivedList.getNumberList());

                List<Integer> sortedUniqueNumbers = new ArrayList<>(new TreeSet<>(receivedList.getNumberList()));
                Collections.sort(sortedUniqueNumbers);

                Llista modifiedList = new Llista(receivedList.getNom(), sortedUniqueNumbers);
                outToClient.writeObject(modifiedList);
                System.out.println("Llista modificada enviada al client: " + modifiedList.getNom() + " - " + modifiedList.getNumberList());

                clientSocket.close();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
