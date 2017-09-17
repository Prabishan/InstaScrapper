package edu.lfa.instagramdownloader;

import edu.lfa.instagramdownloader.dbconnection.DBConnection;
import edu.lfa.instagramdownloader.scrapper.Grabber;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Scrapper {

    public static void main(String[] args) {
        System.out.println("Instagram Application Scrapper");
        System.out.println("Edu Propose");
        System.out.println("================================");

        String baseURL = "https://www.instagram.com/";
        DBConnection conn = new DBConnection();
                try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Enter the Username:");
            String link = reader.readLine();

            String box = Grabber.grab(baseURL + link);

            String RegEx = "https://(.*?).jpg";
            Pattern pattern = Pattern.compile(RegEx);
            Matcher matcher = pattern.matcher(box);
            while (matcher.find()) {
                String imgPath = matcher.group(0);
                String path = (imgPath);
                String[] tokens = path.split("/");

             
                
                  File file1 = new File("D:/InstagramPics/");
                if (!file1.isDirectory()) {
                    file1.mkdir();
                }
                File file2 = new File("D:/InstagramPics/" + link);
                if (!file2.isDirectory()) {
                    file2.mkdir();
               }
                System.out.println("Downloaded Image....." + tokens[tokens.length - 1]);
                Grabber.downloadImage(imgPath, "D:/InstagramPics/" + link+"/" + tokens[tokens.length - 1]);
            }

            System.out.println("Your file is succesfully downloaded!!!!");

        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }

}
