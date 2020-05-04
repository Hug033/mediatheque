package sample;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class ClientConnexion{

    private Socket connexion = null;
    private PrintWriter writer = null;
    private BufferedInputStream reader = null;

    //Notre liste de commandes. Le serveur nous répondra différemment selon la commande utilisée.
    private List<String> ListCommandes;
    private static int count = 0;
    private String name = "Client-";

    public ClientConnexion(String host, int port, List<String> commandes){
        name += ++count;
        try {
            connexion = new Socket(host, port);
            this.ListCommandes = commandes;
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public List<String> run(){

        //nous n'allons faire que 10 demandes par thread...
        for(int i =0; i < 10; i++){
            try {
                Thread.currentThread().sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                OutputStream outputStream = connexion.getOutputStream();
                //On envoie la commande au serveur
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

                // Liste des messages a envoyer
                List<String> messages = new ArrayList<>();
                messages.add("auth");
                messages.add("login");
                messages.add("test");
                messages.add("fsdf54s9");
                objectOutputStream.writeObject(messages);
                objectOutputStream.flush();

                //On attend la réponse
                InputStream inputStream = connexion.getInputStream();
                ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
                List<String> response = (List<String>) objectInputStream.readObject();
                System.out.println("\t * " + name + " : Réponse reçue " + response);
                return response;

            } catch (IOException e1) {
                e1.printStackTrace();
            }

            catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }

            try {
                Thread.currentThread().sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        List<String> fail = new ArrayList<>();
        fail.add("auth_fail");
        return fail;
    }

    //Méthode pour lire les réponses du serveur
    private String read() throws IOException{
        String response = "";
        int stream;
        byte[] b = new byte[4096];
        stream = reader.read(b);
        response = new String(b, 0, stream);
        return response;
    }
}