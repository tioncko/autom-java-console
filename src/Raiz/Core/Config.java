package Raiz.Core;

import java.io.FileInputStream;
import java.util.*;
import java.util.logging.Logger;

public class Config {

    static impressaoLog.logGenerico<Config> printLog = new impressaoLog.logGenerico<>();
    static Logger log = printLog.getLogRetorno(Config.class);

    /**
     * Método que coleta as propriedades informadas no .properties e disponibiliza para as classes do prpjeto
     */
    public static String getProperties(String param) {
        String pr = null;
        try {
            String configFilePath = "src/config.properties";
            FileInputStream propsInput = new FileInputStream(configFilePath);
            Properties prop = new Properties();
            prop.load(propsInput);

            String src = "src/";

            pr = switch (param) {
                case "store_class", "supply_class" -> src + prop.getProperty(param);
                case "endpointMarcas" -> prop.getProperty(param);
                default -> pr;
            };

        } catch (Exception e) {
            //log.info("in MyClass: " + e.getMessage());
            //log.warning("a test warning: " + e.getMessage());
            log.severe("[" + Config.class.getSimpleName() + "] " + e.getMessage());
        }
        return pr;
    }
    //#region rascunho
/*
    public enum NameSettings {
        Loja("store_class"),
        Fornecedores("supply_class"),
        Marcas("endpointMarcas");

        private final String property;

        NameSettings(String param) {
            this.property = param;
        }

        public String getProperty() {
            return property;
        }
    }
 */
    //#endregion
}
