/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tpsitchat;
import java.awt.*;
import java.net.*;
import java.io.*;
import javax.swing.*;
/**
 *
 * @author Karina
 */
public class ThreadChatClient implements Runnable{
    private List lista;
    Thread me;
    private Socket client;
    private BufferedReader input = null;
    private PrintWriter output = null;
    private String utente;
    
    public ThreadChatClient(List lista, String ipServer, int porta,String utente){
        this.lista= lista;
        try{
            client = new Socket(ipServer,porta);
            this.input = new BufferedReader(new InputStreamReader(client.getInputStream()));
            this.output = new PrintWriter(client.getOutputStream(),true);
            
        } catch(Exception e){
            JOptionPane.showMessageDialog(null,"Impossibile connettersi al server");
        }
        me = new Thread(this);
        me.start();
    }
    
    public void run(){
        while(true){
            try{
                String mess = null;
                while((mess = input.readLine()) == null){
                    
                }
                lista.add(mess);
                lista.select(lista.getItemCount()-1);
            } catch(Exception e){
                
            }
        }
    }
    
    public void spedisciMessaggioChat(String mess){
        try{
            output.println(mess); 
        } catch(Exception e) {
            
        }
    }
    
    public String setUtenteOnline(){
        return this.utente + "is Online";
    }
}
