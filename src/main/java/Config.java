import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {
    public static final String PATH_TO_PROPERTIES = "src/main/resources/config.properties";

    public static Properties readConfig(){
        FileInputStream fileInputStream;
        Properties properties = new Properties();
        try{
            fileInputStream = new FileInputStream(PATH_TO_PROPERTIES);
            properties.load(fileInputStream);
            return properties;
        }
        catch (IOException e){
            System.out.println("Something wrong in config!");
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
