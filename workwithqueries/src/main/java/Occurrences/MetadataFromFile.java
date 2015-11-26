/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Occurrences;

import DataBase.MySQL;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 *
 * @author HSOTELO
 */
public class MetadataFromFile {
    
    public static void main(String[] args){
        try
        {
            MySQL mysql=new MySQL("curie.ciat.cgiar.org", "cwr_gapanalysis", "hsotelo", "783836@ciat");
            String file = "C:\\Users\\hsotelo\\Desktop\\WorkSpaceQuery\\all_occurrences_2015-04-16.csv";
            String destination = "C:\\Users\\hsotelo\\Desktop\\WorkSpaceQuery\\all_occurrences_2015-04-16-dest.csv";
            String query = "Select id,filename,provider_institute_id " +
                           "From cwr_occurrences " +
                           "Where id=";
            BufferedReader reader = new BufferedReader(new FileReader(file));
            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(destination, true)));
            String line, newLine, pattern = ",";
            boolean header = true;
            while ((line = reader.readLine()) != null)
            {
                newLine = line;
                if (!header)
                {
                    try
                    {
                        mysql.getResults(query+line.split(pattern)[0].replaceAll("\"", ""));
                        if(mysql.getRecordSet().next())
                            newLine += pattern + "\"" + mysql.getRecordSet().getString("filename") + "\"" + pattern + "\"" + mysql.getRecordSet().getString("provider_institute_id") + "\"";
                        else
                            throw new Exception("Not found rows");
                    }
                    catch(Exception ex)
                    {
                        newLine += pattern + ex.getMessage() + pattern ;
                    }
                }
                else
                {
                    header = false;
                    newLine += "|filename|provider_institute_id";
                }
                writer.println(newLine);
            }
            writer.close();
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
    }
}
