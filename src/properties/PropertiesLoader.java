package properties;

import java.io.FileInputStream;
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
            input = new FileInputStream(
                PropertiesLoader.class.getClassLoader().getResource("/properties/config.properties").getFile()
            );
            props.load(input);
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
    
    public static String get(String key) {
        return props.getProperty(key);
    }
}
