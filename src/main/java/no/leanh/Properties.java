package no.leanh;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Properties {
    public static java.util.Properties properties;
    public Properties(){ init(); }

    private void init(){
        properties = new java.util.Properties();
        InputStream input = null;

        try {
            input = new FileInputStream("config.properties");

            // load a properties file
            properties.load(input);
            System.out.println("Properties loaded");

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
