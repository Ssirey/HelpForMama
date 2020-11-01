import java.io.*;
import java.util.Properties;

public class Config {
    public static final String PATH_TO_PROPERTIES = "config.properties";

    public static Properties readConfig(){
        InputStream inputStream;
        Properties properties = new Properties();
        try{
            inputStream = new FileInputStream(PATH_TO_PROPERTIES);
            properties.load(inputStream);
            return properties;
        } catch (Exception e) {
            System.out.println("config wasn't found in same directory");
        }
        try{
            inputStream = Config.class.getResourceAsStream(PATH_TO_PROPERTIES);
            properties.load(inputStream);
            return properties;
        }
        catch (IOException e){
            System.out.println("Something wrong in config!");
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static void closeConfig(Properties prop) {
        try{
            prop.store(new FileOutputStream(PATH_TO_PROPERTIES), null);
        }
        catch (IOException e){
            System.out.println("Config wasn't save");
        }

    }
}
