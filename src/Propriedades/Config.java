package Propriedades;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;

public class Config {

    static ImpressaoLog.LogGenerico<Config> printLog = new ImpressaoLog.LogGenerico<>();
    static Logger log = printLog.getLogRetorno(Config.class);

    public static String getProperties(String param) {

        String pr = null;
        try {
            String configFilePath = "src/config.properties";
            FileInputStream propsInput = new FileInputStream(configFilePath);
            Properties prop = new Properties();
            prop.load(propsInput);

            pr = "src/" + prop.getProperty(param);
        } catch (IOException e) {

            log.info("in MyClass: " + e.getMessage());
            log.warning("a test warning: " + e.getMessage());
            log.severe("a test warning: " + e.getMessage());
        }
        return pr;
    }

    public enum NameSettings {
        Loja("store_class");

        private final String property;

        NameSettings(String param) {
            this.property = param;
        }

        public String getProperty() {
            return property;
        }
    }
}
