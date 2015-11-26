/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Occurrences;

import DataBase.MySQL;
import Tools.Dialogs;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 *
 * @author HSOTELO
 */
public class OccurrencesByOption {
    public static void main(String[] args){
        try
        {
        	String dbHost = "localhost";
        	String dbName;
        	String dbUsername;
        	String dbPwd;
        	
        	dbName = Dialogs.read("mysql database name on " + dbHost);
        	dbUsername = Dialogs.read("Username");
        	dbPwd = Dialogs.read("Password");
        	
            int option=-1;
            while(option != 1 && option != 2 && option != 3)
                option=Dialogs.readInt("Select option to execute:\n1.Genus\n2.Country\n3.Taxon");            

			MySQL mysql=new MySQL(dbHost, dbName, dbUsername, dbPwd);
            
            File file = Dialogs.getFile("Select file source");
            File destinationDir=Dialogs.getDirectory("Select destination folder");

            if (! destinationDir.isDirectory()) {
            	System.out.println("Not a valid directory: " + destinationDir);
            	System.exit(-1);
            }
                    
            String header="id,source,provider_institute_id,provider_name,institute_id,institute_name,collection,source_url,availability,unique_number,barcode,vno_1,vno_2,x1_family,x1_genus,x1_sp1,x1_author1,x1_rank1,x1_sp2,x1_author2,x1_rank2,x1_sp3,x1_author3,x1_detby,x1_detdate,x1_detdd,x1_detmm,x1_detyy,x1_detstat,x2_genus,x2_sp1,x2_author1,x2_rank1,x2_sp2,x2_rank2,x2_sp3,is_hybrid,hybrid_memo,tnrs_final_taxon,taxon_final,f_x1_genus,f_x1_sp1,f_x1_rank1,f_x1_sp2,f_x1_rank2,f_x1_sp3,annotated_specimen,type,type_memo,collector,addcoll,collnumber,prefix,number,suffix,colldate,colldd,collmm,collyy,final_country,final_iso2,adm1,adm2,adm3,adm4,local_area,locality,lat_deg,lat_min,lat_sec,ns,final_ns,latitude,long_deg,long_min,long_sec,ew,final_ew,longitude,latitude_georef,alt,final_alt,final_cult_stat,final_origin_stat,habitat_txt,fl_code,fr_code,dups,notes,comments,citation,final_lat,final_lon,coord_source,taxstand_final_taxon";
            String query = "Select  " + header + " " +
                           "From cwr_occurrences " +
                           "Where data_public_access=1 and provider_institute_id!='GBIF' and " +
                           (option==1?"f_x1_genus = '":
                                (option==2?"final_country = '":
                                    "taxon_final = '"));
            File destinationTypeDir=new File(destinationDir, (option==1?"genus":(option==2?"country":"taxon")));
            BufferedReader reader = new BufferedReader(new FileReader(file));
            PrintWriter writer;
            String line, newLine;
            while ((line = reader.readLine()) != null)
            {
                line=line.replaceAll("\"", "");
                System.out.println("File: " + line);
                final File destinationFile=new File(destinationTypeDir, line + ".csv");
                System.out.println("Writing to: " + destinationFile.getAbsolutePath());
                writer = new PrintWriter(new BufferedWriter(new FileWriter(destinationFile, true)));
                writer.println(header);
                try
                {
                    mysql.getResults(query + line.replace("'", "''") + "';");
                    while(mysql.getRecordSet().next()){
                        newLine="";
                        for(String field:header.split(","))
                            newLine+=getVI(getVI(mysql.getRecordSet().getString(field))) + ",";
                        writer.println(newLine);
                    }
                }
                catch(Exception ex)
                {
                    System.out.println("Error in file: " + line + "\n" +ex);
                }
                writer.close();
            }
            reader.close();
            System.out.println("Task Finished!!!");
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
    }
    
    public static String getVI(String field)
    {
        return field == null ? "" : field;
    }
    
    
}
