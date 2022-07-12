package reporting.utility;

import Base.commonApi;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Properties;

public class utility  {

        public static String currentDir = System.getProperty("user.dir");

        public static Properties loadProperties(){
            Properties properties = new Properties();
            try {
                InputStream inputStream = Files.newInputStream(Paths.get(currentDir +"\\config.properties"));
                properties.load(inputStream);
                inputStream.close();
            }
            catch (IOException e){
                e.printStackTrace();
            }

            return properties;
        }
    public static String decode(String key){
        byte[] decodedBytes = Base64.getDecoder().decode(key);
        return new String(decodedBytes);
    }
    /*public static void main(String[] args) {
        String originalStr = "mohamedWALID8991";
        String encyptedStr;
        encyptedStr = Base64.getEncoder().encodeToString(originalStr.getBytes());
        System.out.println(encyptedStr);

        *//*System.out.println(Utility.decode("c3RhbmRhcmRfdXNlcg=="));*//*
    }*/
}

