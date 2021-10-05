/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devguicho.nodo1;

import java.net.*;
import java.io.*;

/**
 *
 * @author beatl
 */
public class ServerMultiClient {

    static int NoClients = 0;

    public static void main(String[] argumentos) throws IOException {
        ServerSocket socketServidor = null;
        Socket socketCliente = null;

        try {
            socketServidor = new ServerSocket(12345);
        } catch (Exception e) {
            System.out.println("Error : " + e.toString());
            System.exit(0);
        }

        System.out.println("Server started... (Socket TCP)");
        int enproceso = 1;
        while (enproceso == 1) {
            try {
                socketCliente = socketServidor.accept();
                MultiServerThread controlThread = new MultiServerThread(socketCliente, "servidor1");
                controlThread.start();
            } catch (Exception e) {
                System.out.println("Error : " + e.toString());
                socketServidor.close();
                System.exit(0);
            }
        }
        System.out.println("Finalizando Servidor...");

    }
}
