/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author HSOTELO
 */
public class Dialogs {
    public static String getFile()
    {
        String a=null;
        try
        {
            JFileChooser fc=new JFileChooser();
            fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
            if(fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
                a=fc.getSelectedFile().getAbsolutePath();
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
        return a;
    }
    
    public static String getDirectory()
    {
        String a=null;
        try
        {
            JFileChooser fc=new JFileChooser();
            fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            if(fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
                a=fc.getSelectedFile().getAbsolutePath();
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
        return a;
    }
    
    public static void message(String msg){
        JOptionPane.showMessageDialog(null, msg);
    }
    
    public static String read(String msg){
        return JOptionPane.showInputDialog(msg);
    }
    
    public static int readInt(String msg){
        try{
            return Integer.parseInt(JOptionPane.showInputDialog(msg));
        }
        catch(NumberFormatException ex){
            System.out.println(ex);
            return readInt(msg);
        }
    }
}
