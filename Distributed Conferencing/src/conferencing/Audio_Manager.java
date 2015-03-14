package conferencing;


import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ashutosh
 */
public class Audio_Manager{
    boolean flag;
    TargetDataLine mic;
    SourceDataLine speaker;
    byte[] buffer;
    byte[] byteArray;
    AudioFormat format;
    
    public static void main(String[] args) throws IOException {
        Audio_Manager a = new Audio_Manager();
        a.startMic();
        a.byteArray = new byte[5120];
        // Create the AudioData object from the byte array
        a.program();
        
    }
    public void program() throws IOException
    {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        ByteArrayInputStream bstream;
        
        mic.start();
        speaker.start();
        while(true)
        {
            mic.read(byteArray, 0, 1024);
            speaker.write(byteArray, 0, 1024);
        }
    }
    public void send()
    {
        try
        {
            mic.read(this.byteArray,0,5120);
            for(int i=0;i<100;i++)
                System.out.print(byteArray[i]+" ");
        }
        catch(Exception ex)
        {
            System.out.println("Error in Sending");
        }
    }
    public void startMic()
    {
        try{
            // Creating the audio format for saving the audio which will be recorded
            format = new AudioFormat(44100, 8, 1, true, true);
            DataLine.Info info = new DataLine.Info(TargetDataLine.class,format);
            mic = (TargetDataLine)AudioSystem.getLine(info);
            speaker = AudioSystem.getSourceDataLine(format);
            System.out.println("Wtf");
            mic.open();
            speaker.open(format,1024);
            buffer = new byte[512];
        } catch (LineUnavailableException ex) {
            Logger.getLogger(Audio_Manager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
