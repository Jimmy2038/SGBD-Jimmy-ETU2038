import java.net.*;
import java.io.*;
import java.util.Scanner;
public class Echange extends Thread{
  Socket socket;
  int num;
  public Socket getSocket() {
    return socket;
  }
  public void setSocket(Socket socket) {
    this.socket = socket;
  }
  public int getNum() {
    return num;
  }
  public void setNum(int num) {
    this.num = num;
  }
  public Echange (Socket s,int num){
    this.setSocket(s);
    this.setNum(num);
  }
  @Override
  public void run() {
    try {
      InputStreamReader in = new InputStreamReader(socket.getInputStream());
      BufferedReader bf = new BufferedReader(in);
      Relation r = new Relation();
       PrintWriter pr= new PrintWriter(socket.getOutputStream());
       String Ip = socket.getRemoteSocketAddress().toString();
      System.out.println("Connexion du client n°: "+num); 
      pr.println("Bienvenue client n°: "+num+" IP= "+Ip);
      pr.flush();
      while (true) {
        String requet = bf.readLine();
        System.out.println("Le client n°: "+num+" a envoyer cette requete '"+requet+"'");
        try (Scanner scan = new Scanner(System.in)) {
          Fanoratana.append("traitement.txt", requet,false,false);
        }
          try {
          Relation rela= new Relation();
          r=rela.verifie();
        } catch (Exception e) {
              pr.println(e.getMessage());
              pr.flush();
        }

        OutputStream getobj=socket.getOutputStream();
        ObjectOutputStream sendobj=new ObjectOutputStream(getobj);
        sendobj.writeObject(r);
      }
      
      
    } catch (Exception e) {
      
    }
  }
}
