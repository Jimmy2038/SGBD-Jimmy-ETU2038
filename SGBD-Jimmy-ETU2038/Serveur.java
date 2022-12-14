import java.net.*;
import java.util.Scanner;
import java.io.*;
public class Serveur extends Thread{
  int num;
  public static void main(String[] args) throws Exception {
      new Serveur().start();    
  }
  @Override
  public void run() {
    try {
      ServerSocket  ss = new ServerSocket(500);
      System.out.println("Demmarage du serveur... ");
      while (true) {
        Socket s = ss.accept();
        ++num;
        new Echange(s,num).start();
    }
    } catch (Exception e) {
      
    }
    
  }
}