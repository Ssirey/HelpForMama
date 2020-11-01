import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;

public class ConfigManager {
    public ArrayList<String> dateColumnItems;
    public ArrayList<String> sexColumnItems;
    ConfigManager() {
        dateColumnItems = new ArrayList<>(Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J"));

        sexColumnItems = new ArrayList<>(Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J"));
    }

    public String[] formSetting(Properties prop, String key){
        ArrayList<String> defaultSet;
        switch (key) {
            case ("dateColumn"):
                defaultSet = new ArrayList<>(dateColumnItems);
                break;
            case ("sexColumn"):
                defaultSet = new ArrayList<>(sexColumnItems);
                break;
            default:
                System.out.println("Null setting");
                defaultSet = new ArrayList<>();
                break;
        }
        if(defaultSet.contains(prop.getProperty(key))){
            defaultSet.remove(prop.getProperty(key));
            defaultSet.add(0, prop.getProperty(key));
        }
        return defaultSet.toArray(new String[defaultSet.size()]);
    }

    public String[] formSettings (String key){
        return formSetting(Config.readConfig(), key);
    }

    public String getSetting(String key){
        Properties prop = Config.readConfig();
        assert prop != null;
        return prop.getProperty(key);
    }

//    public void setSetting (Properties prop, String key, String value){
//        prop.setProperty(key, value){
//    }
}
