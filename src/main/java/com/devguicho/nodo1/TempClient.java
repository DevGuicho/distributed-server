package com.devguicho.nodo1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author beatl
 */
public class TempClient {

    private Socket client;
    private PrintWriter escritorX = null;
    private String DatosEnviadosX = null;
    private BufferedReader entradaX = null;

    private String maquina;
    private int port;

    public TempClient(String maquina, int port) {
        this.maquina = maquina;
        this.port = port;
    }

    public void connect() {
        try {
            client = new Socket(maquina, port);
            System.out.println("Connected to another server");
        } catch (Exception e) {
            System.out.println("Fallo : " + e.toString());
            System.exit(0);
        }

        try {
            escritorX = new PrintWriter(client.getOutputStream(), true);
            entradaX = new BufferedReader(new InputStreamReader(client.getInputStream()));
        } catch (Exception e) {
            System.out.println("Fallo : " + e.toString());
            
            System.exit(0);
        }
    }

    public Socket getClient() {
        return client;
    }

    public void setClient(Socket client) {
        this.client = client;
    }

    public PrintWriter getEscritorX() {
        return escritorX;
    }

    public void setEscritorX(PrintWriter escritorX) {
        this.escritorX = escritorX;
    }

    public String getDatosEnviadosX() {
        return DatosEnviadosX;
    }

    public void setDatosEnviadosX(String DatosEnviadosX) {
        this.DatosEnviadosX = DatosEnviadosX;
    }

    public BufferedReader getEntradaX() {
        return entradaX;
    }

    public void setEntradaX(BufferedReader entradaX) {
        this.entradaX = entradaX;
    }

    public String getMaquina() {
        return maquina;
    }

    public void setMaquina(String maquina) {
        this.maquina = maquina;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

}
