/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionary;

import java.util.Hashtable;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


/**
 *
 * @author beatl
 */
public  class Dictionary {

    private  Hashtable<String, Server> servers = new Hashtable<>();
    
    public Dictionary(){
        this.servers = getServersDictionary();
    }
    
    public Hashtable<String,Server> getServersDictionary() {
        JsonManage jm = new JsonManage();
        JSONObject serversJson = jm.getJSON("servers.json");
        JSONArray servList = jm.getArrayFromJSON(serversJson,"servList");
        servList.forEach(serv->parseServer((JSONObject)serv));
        return servers;
    }
    
    public void parseServer(JSONObject server){
        String name = (String)server.get("name");
        String ip = (String)server.get("ip");
        long port = (long)server.get("port");
        Hashtable<String,Service> services = new Hashtable<>();
        
        JSONArray serviceList = (JSONArray)server.get("services");
        
        serviceList.forEach(service-> parseService((JSONObject)service, services));
        servers.put(name,new Server(name,ip,port, services));
        
        
    }
    
    public void parseService(JSONObject service, Hashtable<String,Service> services ){
        String nameOfService =(String) service.get("nameOfService");
        long factor = (long) service.get("factor");
        
        services.put(nameOfService, new Service(nameOfService, factor));
    }

    public Server getServer(String serverName) {
        return servers.get(serverName);
    }

    

    
}
