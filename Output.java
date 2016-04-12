/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cities;

import java.net.*;
import java.io.*;
import org.json.*;
import com.illposed.osc.*;
//https://wush.net/trac/lsu-idt/wiki/JavaOSC

/**
 *
 * @author caiqing
 */
public class Output {
    
    JSONObject json;
    
    public Output(String r) throws JSONException{
    
        json = new JSONObject(r);
    };
    
    public float getHumidity() throws JSONException{
    
    String humidity ="";
    //float h;
    float h;
    
    //humidity = Integer.toString(json.getJSONObject("main").getInt("humidity"));
    
    h = (float) json.getJSONObject("main").getInt("humidity");
    System.out.println("Humidity: " + h);
    //return humidity;
    return h;
    
    }
    
    public float getClouds() throws JSONException{
    
    String clouds = "";
    float c;
    
    //clouds = Integer.toString(json.getJSONObject("clouds").getInt("all"));
    
    c = (float) json.getJSONObject("clouds").getInt("all");
    System.out.println("Clouds: " + c);
    
    //return clouds;
    return c;
    }
    
    
    
   /* public String getTempRange(){
    
    String tempRange = "";
    
    return tempRange;
    }*/
    
    public void send() throws SocketException, UnknownHostException, JSONException{
        
        
        
        InetAddress remoteIP = InetAddress.getLocalHost();
        int remotePort = 6448;
        OSCPortOut sender = new OSCPortOut(remoteIP, remotePort);

        
        
        String message = "/wek/inputs";
        Float[] values = new Float[2];
        //values[0] = wekiInput;
        values[0] = Float.valueOf(getClouds());
        values[1] = Float.valueOf(getHumidity());
        
        
        

        OSCMessage msg = new OSCMessage(message, values);
        String result = "";
        try{
        sender.send(msg);
        System.out.println(msg.toString() + "\n");
        //return result;
        }catch (IOException ex){
            System.out.println("Could not send message: " + ex.getLocalizedMessage());
        }
    }
    
}
