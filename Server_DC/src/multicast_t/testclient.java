/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multicast_t;

import java.io.*;  
import java.net.*;  
public class testclient {  
    public static void main(String[] args) {  
        try{      
            Socket s=new Socket("10.132.141.216",6666);  
			System.out.println("Connected!!");
            DataOutputStream dout=new DataOutputStream(s.getOutputStream());
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            String str="";
            while(!str.equals("stop")) {
                str = bf.readLine();  
                dout.writeUTF(str);  
                dout.flush();  
            }
            dout.close();
            s.close();  
        }
        catch(Exception e){
            System.out.println(e);
        }  
    }  
}