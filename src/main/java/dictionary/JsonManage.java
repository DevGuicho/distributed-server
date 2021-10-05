/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionary;

import java.io.FileReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author beatl
 */
public class JsonManage {

    public JSONObject getJSON(String filename) {
        try {
            JSONParser jsonParser = new JSONParser();
            FileReader reader = new FileReader(filename);
            Object obj = jsonParser.parse(reader);
            return (JSONObject) obj;
        } catch (Exception ex) {
            return new JSONObject();
        }

    }

    public JSONObject getObjectFromJSON(JSONObject obj, String name) {
        try {
            return (JSONObject) obj.get(name);

        } catch (Exception e) {
            System.out.println(e);
            return new JSONObject();
        }
    }

    public JSONArray getArrayFromJSON(JSONObject obj, String name) {
        try {
            return (JSONArray) obj.get(name);
        } catch (Exception e) {
            System.out.println(e);
            return new JSONArray();
        }
    }
    
}
