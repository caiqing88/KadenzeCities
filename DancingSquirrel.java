/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DancingSquirrel;

import com.illposed.osc.OSCListener;
import com.illposed.osc.OSCMessage;
import com.illposed.osc.OSCPort;
import com.illposed.osc.OSCPortIn;
import com.illposed.osc.OSCPortOut;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import org.json.*;



/**
 *
 * @author caiqing
 */
public class DancingSquirrel {

    /**
     * @param args the command line arguments
     * @throws java.net.UnknownHostException
     */
    
    //InetAddress remoteIP = InetAddress.getLocalHost();
    
    static DanceDegrees R1 = new DanceDegrees( "Thread-1");
    static int x=0,y=0;
    
    public static void main(String[] args) {
        // TODO code application logic here
        
        R1.start();
        //Output output = new Output();
        try {
            //output.receiveInput();
            
            //int ListenerPort = 6449;
        OSCPortIn receiver = new OSCPortIn(OSCPort.defaultSCOSCPort());
        OSCListener listener;
            listener = new OSCListener() {
                
                public void acceptMessage(java.util.Date time, OSCMessage message) {
                    System.out.print(message);
                    System.out.println("Message received!");
                    
                    Object[] arguments = message.getArguments();
                    
                    System.out.println(arguments[0].toString());
                    //System.out.println(arguments[1].toString());
                    
                    //System.out.println(arguments[2].toString());
                    float result = Float.parseFloat(arguments[0].toString());
                    dance(result);
                    
                }
            };
        receiver.addListener("/message/receiving", listener);
        receiver.startListening();
        //receiver.run();
        } catch (SocketException ex) {
            ex.printStackTrace();
        }
        
            }//main
    
    
public static void dance(float result){
        int h,v;
    
         if (result == 1.0){ 
        
             h = x + 180;
             x = (h % 360);
             
             v = y + 345;
             y = v % 360;
             
            //send(180,345);
            //R1.setDegreeX(180);
            //R1.setDegreeY(345);
            
            
         }
         if (result == 2.0){
             
             h = x + 270;
             x = (h % 360);
             
             v = y + 270;
             y = v % 360;
            //send (270, 270);
            //R1.setDegreeX(270);
            //R1.setDegreeY(270);
         }
    
         R1.setDegreeX(x);
         R1.setDegreeY(y);
            R1.setDegreeY(y);R1.setDegreeY(y);

}//dance

public static void send(int x, int y) throws SocketException, UnknownHostException, JSONException{
        
        
        
        InetAddress remoteIP = InetAddress.getLocalHost();
        int remotePort = 12000;
        OSCPortOut sender = new OSCPortOut(remoteIP, remotePort);

        
        
        String message = "/wek/outputs";
        Float[] values = new Float[2];
        //values[0] = wekiInput;
        values[0] = Float.valueOf(x);
        values[1] = Float.valueOf(y);
        
        
        

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


}//squirrel






