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
public class ThreadChatConnessioni implements Runnable{
    private ThreadGestioneServizio gestoreChat;
    private Socket client = null;
    private BufferedReader input = null;
    private PrintWriter output = null;
    Thread me;
    
    public ThreadChatConnessioni(ThreadGestioneServizio gestoreChat,Socket client){
        this.gestoreChat = gestoreChat;
        this.client = client;
        try{
            this.input = new BufferedReader(new InputStreamReader(client.getInputStream()));
            this.output = new PrintWriter(this.client.getOutputStream(),true);
        } catch (Exception e){
            output.println("errore lettura");
        }
        me = new Thread(this);
        me.start();
    }
    
    public void run(){
        while(true){
            try{
                String mess = null;
                //resto in attesa
                while((mess = input.readLine()) == null)
                { }
                gestoreChat.spedisciMessaggio(mess);
            } catch(Exception e){
                output.println("Errore nella spedizione del messaggio a tutti");
            }
        }
    }
    
    public void spedisciMessaggioChat(String messaggio){
        try{
            output.println(messaggio);
        } catch(Exception e){
            output.println("Errore nella spedizione del singolo messaggio!");
        }
    }
}
