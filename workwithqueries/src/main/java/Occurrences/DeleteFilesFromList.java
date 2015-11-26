/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Occurrences;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import Tools.Dialogs;

/**
 *
 * @author HSOTELO
 */
public class DeleteFilesFromList {
    public static void main(String[] args){
        try
        {
            File file = Dialogs.getFile("Select file that has the list to files to delete");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            File directory = Dialogs.getDirectory("Select which is the directory to search the files");
            String line, fileDelete;
            File f;
            while ((line = reader.readLine()) != null)
            {
                fileDelete=line + ".csv";
                f=new File(directory, fileDelete);
                System.out.println("File: " + f.getAbsolutePath());
                f.delete();
            }
            reader.close();
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
    }
}
