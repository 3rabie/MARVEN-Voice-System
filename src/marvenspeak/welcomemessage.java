/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marvenspeak;


import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class welcomemessage extends Thread {
private static final String VOICENAME_kevin = "kevin";
 //private String s; // string to speech
   Voice voice;

 public welcomemessage() {
//  this.text = text;
//  //System.load("/home/rabie/Downloads/mbrola/mbrola.exe");
//System.setProperty("mbrola.base", "/home/rabie/Downloads/mbrola/mbr301h/");
  VoiceManager voiceManager = VoiceManager.getInstance();
  voice = voiceManager.getVoice(VOICENAME_kevin);
  voice.allocate();
 }
 
 public void welmsg(String s) {
  
  voice.speak("Welcome "+s);
 }
  public void speak(String s) {
  
  voice.speak(s);
 }
 
    
}
