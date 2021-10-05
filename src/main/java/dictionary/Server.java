/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionary;

import java.util.Hashtable;

/**
 *
 * @author beatl
 */
public class Server {
    private String name;
    private String ip;
    private long port;
    private Hashtable<String,Service> services;

    public Server(String name, String ip, long port, Hashtable<String, Service> services) {
        this.name = name;
        this.ip = ip;
        this.port = port;
        this.services = services;
    }

    

    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public long getPort() {
        return port;
    }

    public void setPort(long port) {
        this.port = port;
    }

    public Hashtable<String, Service> getServices() {
        return services;
    }

    public void setServices(Hashtable<String, Service> services) {
        this.services = services;
    }

    @Override
    public String toString() {
        return "Server{" + "name=" + name + ", ip=" + ip + ", port=" + port + ", services=" + services + '}';
    }
    
    
    
}
