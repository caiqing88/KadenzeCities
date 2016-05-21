/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DancingSquirrel;

import com.illposed.osc.OSCMessage;
import com.illposed.osc.OSCPortOut;
import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import org.json.JSONException;

/**
 *
 * @author caiqing
 */


class DanceDegrees implements Runnable {
   private Thread t;
   private String threadName;
   private int degreeX;
   private int degreeY;
   
   DanceDegrees( String name){
       threadName = name;
       degreeX = 0;
       degreeY = 0;
       System.out.println("Creating " +  threadName );
   }
   public void run() {
      System.out.println("Running " +  threadName );
      try {
         //for(int i = 4; i > 0; i--) {
         while (true) {
            //System.out.println("Thread: " + threadName + ", " + i);
            System.out.println("Running...");
             
                 send(degreeX, degreeY);
                 
             System.out.println("sent: " + degreeX + "," + degreeY);
            // Let the thread sleep for a while.
            Thread.sleep(500);               
         }
     } catch (InterruptedException e) {
         System.out.println("Thread " +  threadName + " interrupted.");
     } catch (SocketException ex) {
                 ex.printStackTrace();
             } catch (UnknownHostException ex) {
                 ex.printStackTrace();
             } catch (JSONException ex) {
                 ex.printStackTrace();
             }
     System.out.println("Thread " +  threadName + " exiting.");
   }
   
   
   public void setDegreeX(int x){
   degreeX = x;
   }
   
   public void setDegreeY(int y){
   degreeY = y;
   }
   public void start ()
   {
      System.out.println("Starting " +  threadName );
      if (t == null)
      {
         t = new Thread (this, threadName);
         t.start ();
      }
   }

   
   
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
    }//send

}//DanceDegrees