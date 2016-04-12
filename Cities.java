/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cities;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.io.InputStream;
import org.json.*;



/**
 *
 * @author caiqing
 */
public class Cities {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        StringBuffer buffer = new StringBuffer();
        java.net.URL url;
        String result;
        String line;
        String callapi = "http://api.openweathermap.org/data/2.5/weather?q=";
        URLConnection connection;
        InputStream in;
        BufferedReader rd2;
        String response;
        Output output;

        JSONObject json;
                
        try {
        /*url = new URL( "http://api.openweathermap.org/data/2.5/weather?q=" );
        connection = url.openConnection( );
        connection.setDoOutput( true );
        OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
        buffer.append("<?xml version='1.0' encoding='utf-8'?><searchblox licenseid='302C02140D9128DD59B531EEA28B2D35770A28A0653ECA68021479151B41E6042FED8F76E73AFCC02F0CAE33DE37'>");
        buffer.append("<document colname='Custom_Collection' location='" + args[0] + "'></document>");
        buffer.append("<title boost='1'>My custom title " + args[0]+ "</title>");
        buffer.append("<keywords boost='1'>");
        buffer.append( "Keyword1, ");
        buffer.append("</keywords>");
        buffer.append("<category>category1</category>");
        buffer.append("</searchblox>");
        result = new String( buffer.toString().getBytes(), "UTF-8" );
        System.out.println( "\n *INDEX XML * \n\n" + result + "\n\n");
        wr.write( result );
        wr.flush();
        wr.close();*/

        //BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
        while ((line = rd.readLine()) != null) {
        callapi += line;
        callapi +="&APPID=yourkey"; //include your weather API App ID here
        callapi +="&mode=JSON";
        url = new URL(callapi);
        connection = url.openConnection();
        //connection.connect();
        connection.setDoInput(true);
        //connection.
        in = url.openStream();
        System.out.println(connection.getContentType());
        //System.out.println(connection.getContent().toString());
        System.out.println(callapi + "\n");
        rd2 = new BufferedReader(new InputStreamReader(in));
        //rd2 = new BufferedReader(new InputStreamReader(url.openStream()));
        response = rd2.readLine();
        System.out.println(response + "\n");
        
        output = new Output(response);
        output.send();
        //System.out.println("clouds: " + output.getClouds());
        
        //System.out.println("humidity: " + output.getHumidity());
        //output.send(output.getClouds());
        rd2.close();
        
        callapi = "http://api.openweathermap.org/data/2.5/weather?q=";
        
        }
        //wr.close();
        rd.close();
        

        //System.out.println( "\n *INDEX RESPONSE XML * \n\n" + city + "\n\n"); 

        }
        catch(Exception e){
        System.out.println(e);
        e.printStackTrace();
        }
            }//main
    
}//cities






