package properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author irvin
 */
public class PropertiesLoader {

    private static Properties props = new Properties();
    private static InputStream input = null;
            
    static {
        try {
            input = PropertiesLoader.class.getClassLoader().getResourceAsStream("properties/config.properties");
            props.load(input);
        } catch (IOException ex) {
            System.out.println(ex.toString());
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    System.out.println(e.toString());
                }
            }
        }
    }
    
    public static String get(String key) {
        return props.getProperty(key);
    }
}
