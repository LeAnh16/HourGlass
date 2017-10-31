package no.leanh;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Prop {
    public static java.util.Properties properties;
    public Prop(){ init(); }

    public static Properties getProperties() {
        return properties;
    }

    private void init(){
        properties = new java.util.Properties();
        InputStream input = null;

        try {
            input = new FileInputStream("config.properties");

            // load a properties file
            properties.load(input);
            System.out.println("Prop loaded");

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
