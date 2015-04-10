/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrator
 */
public class User{
    String nick;
    String IP;
    
    User(String nick, String IP)
    {
        this.nick = nick;
        this.IP = IP;
    }
    User()
    {
        this.nick = null;
        this.IP = null;
    }  
    
}
