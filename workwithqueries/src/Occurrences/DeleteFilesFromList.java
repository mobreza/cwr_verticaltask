/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Occurrences;

import Tools.Dialogs;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;

/**
 *
 * @author HSOTELO
 */
public class DeleteFilesFromList {
    public static void main(String[] args){
        try
        {
            Dialogs.message("Select file that has the list to files to delete");
            String file = Dialogs.getFile();
            BufferedReader reader = new BufferedReader(new FileReader(file));
            Dialogs.message("Select which is the directory to search the files");
            String directory = Dialogs.getDirectory();
            String split=System.getProperty("os.name").contains("indows") ? "\\" : "/";
            String line, fileDelete;
            File f;
            while ((line = reader.readLine()) != null)
            {
                fileDelete=directory.endsWith(split) ? directory + line + ".csv" : directory + split + line + ".csv";
                System.out.println("File: " + fileDelete);
                f=new File(fileDelete);
                f.delete();
            }
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
    }
}
