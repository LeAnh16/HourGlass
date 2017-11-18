package no.leanh.file;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Prop {
    public  java.util.Properties properties;
    public  java.util.Properties serverProperties;
    public Prop(){ init(); }

    public  Properties getProperties() {
        return properties;
    }
    public  Properties getServerProperties()
    {
        return serverProperties;
    }

    private void init(){
        properties = new java.util.Properties();
        serverProperties = new java.util.Properties();
        InputStream input = null;

        try {
            input = new FileInputStream("DBconfig.properties");

            // load a properties file
            properties.load(input);

            input = new FileInputStream("ServerProperties");
            serverProperties.load(input);
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
