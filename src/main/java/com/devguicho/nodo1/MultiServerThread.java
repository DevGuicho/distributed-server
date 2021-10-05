/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devguicho.nodo1;

/**
 *
 * @author beatl
 */
import dictionary.Dictionary;
import dictionary.Server;
import dictionary.Service;
import java.net.*;
import java.io.*;
import java.util.Date;
import java.util.Hashtable;
import java.util.Set;

public class MultiServerThread extends Thread {

    private Socket socket = null;
    private String serverName;
    private Dictionary d;
    private Server myServer;
    String serverX;
    String maquinaX;
    int puertoX;
    private boolean isService;
    PrintWriter escritorX = null;
    String DatosEnviadosX = null;
    BufferedReader entradaX = null;
    Socket clienteX = null;

    public MultiServerThread(Socket socket, String serverName) {
        super("MultiServerThread");
        this.socket = socket;
        this.d = new Dictionary();
        this.serverName = serverName;
        this.myServer = d.getServer(serverName);
        ServerMultiClient.NoClients++;
        System.out.println("After Init");
    }

    public void run() {

        try {
            PrintWriter escritor = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String lineIn, lineOut;

            while ((lineIn = entrada.readLine()) != null) {
                System.out.println("Received: " + lineIn);
                System.out.println(myServer.getServices().get(lineIn));
                escritor.flush();
                if (lineIn.equals("FIN")) {
                    ServerMultiClient.NoClients--;
                    break;
                } else if (myServer.getServices().get(lineIn) != null) {
                    escritor.println("Echo... " + myServer.getServices().get(lineIn));
                    escritor.flush();
                } else if (myServer.getServices().get(lineIn) == null) {
                    Hashtable<String, Server> servers = d.getServersDictionary();
                    servers.remove(myServer.getName());
                    Set<String> claves = servers.keySet();
                    for (String clave : claves) {
                        Server temp = servers.get(clave);
                        if (temp.getServices().get(lineIn) != null) {
                            puertoX = (int) temp.getPort();
                            maquinaX = temp.getIp();
                            serverX = temp.getName();
                            isService = true;
                            break;
                        }
                        isService = false;
                    }

                    if (isService) {

                        System.out.println("Connecting to another server");
                        try {
                            clienteX = new Socket(maquinaX, puertoX);
                            System.out.println("Connected to another server");
                        } catch (Exception e) {
                            System.out.println("Fallo : " + e.toString());
                            System.exit(0);
                        }
                        System.out.println("Trying to send data to another server");

                        try {
                            escritorX = new PrintWriter(clienteX.getOutputStream(), true);
                            entradaX = new BufferedReader(new InputStreamReader(clienteX.getInputStream()));
                        } catch (Exception e) {
                            System.out.println("Fallo : " + e.toString());
                            clienteX.close();
                            System.exit(0);
                        }
                        String lineX;
                        String DatosEnviadosX;
                        System.out.println("Sending "+lineIn+" to another server");
                        DatosEnviadosX = lineIn;
                        escritorX.println(DatosEnviadosX);
                        lineX = entradaX.readLine();
                        System.out.println(serverX + ": " + lineX);
                        DatosEnviadosX = "FIN";
                        escritorX.println(DatosEnviadosX);
                        System.out.println("Closing another server");
                        clienteX.close();
                        escritorX.close();
                        entradaX.close();
                        escritor.println("Response from " + serverX + "... " + lineX);
                        escritor.flush();
                    } else {
                        //escritor.println("Echo... " + lineIn);
                        escritor.println("Echo... " + "No existe ese servicio");
                        escritor.flush();
                    }
                }
            }
            try {
                entrada.close();
                escritor.close();
                socket.close();
            } catch (Exception e) {
                System.out.println("Error : " + e.toString());
                socket.close();
                System.exit(0);
            }
        } catch (IOException e) {
            System.out.println("Error---");
            e.printStackTrace();
        }
    }
}
