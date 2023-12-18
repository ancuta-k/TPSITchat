/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tpsitchat;

import java.awt.List;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JOptionPane;

/**
 *
 * @author Karina
 */
public class ThreadGestioneServizio implements Runnable{
    private int nrMaxConnessioni;
    private List lista;
    private ThreadChatConnessioni[] listaConnessioni;
    Thread me;
    private ServerSocket serverChat;
    
    public ThreadGestioneServizio(int numeroMaxConnessioni,List lista){
        this.nrMaxConnessioni = 10;
        this.lista = lista;
       this.listaConnessioni = new ThreadChatConnessioni[this.nrMaxConnessioni];
       me = new Thread(this);
       me.start();
    }
    
    public void run(){
        boolean continua = true;
        //istanziamento della connessione del server per la chat
        try{
            serverChat = new ServerSocket(6789);
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null,"Impossibile istanziare il server!");
            continua = false;
        }
        //se continua = true, accetto le connessioni 
        if (continua) {
            try{
                for (int i = 0; i < nrMaxConnessioni; i ++){
                    Socket tempo = null;
                    tempo = serverChat.accept();
                    listaConnessioni[i] = new ThreadChatConnessioni(this,tempo);
            }
                serverChat.close();
            } catch(Exception e){
                JOptionPane.showMessageDialog(null,"Impossibile istanziare server chat");
            }
        }
    }
    
    public void spedisciMessaggio(String mess){
        lista.add(mess);
        lista.select(lista.getItemCount()-1);
        
        for(int i = 0; i < this.nrMaxConnessioni; i ++){
           if( listaConnessioni[i] != null){
               listaConnessioni[i].spedisciMessaggioChat(mess);
           }
        }
    }
}