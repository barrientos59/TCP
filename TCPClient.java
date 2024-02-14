package TCP;
import java.io.*;
import java.net.*;
import java.util.*;

public class TCPClient {
    public static void main(String[] args) {
        try {
            Socket clientSocket = new Socket("localhost", 12345);
            ObjectOutputStream output = new ObjectOutputStream(clientSocket.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(clientSocket.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Nombre de la lista: ");
            String lista = reader.readLine();
            System.out.print("Numeros separados por espacios: ");
            String numbersInput = reader.readLine();
            String[] numArray = numbersInput.split(" ");
            List<Integer> numbers = new ArrayList<>();
            for (String num : numArray) {
                numbers.add(Integer.parseInt(num));
            }
            Llista listToSend = new Llista(lista, numbers);
            System.out.println("Lista enviada: " + listToSend.getNom() + ": " + listToSend.getNumberList());
            output.writeObject(listToSend);
            Llista modifiedList = (Llista) input.readObject();
            System.out.println("Lista recibida: " + modifiedList.getNom() + ": " + modifiedList.getNumberList());
            clientSocket.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
