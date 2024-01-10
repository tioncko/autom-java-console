package Raiz.Core;

import Cadastro.NovosDados.Repositorio.Enums.coresANSI;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.*;

public class ImpressaoLog {

    public static class LogGenerico <T> {

        protected Logger log;
        public Logger getLogRetorno(Class<T> classe) {
            String formatter = "java.util.logging.SimpleFormatter.format";
            String regex = "[%1$tF %1$tT] [%4$-7s] %5$s %n";

            System.setProperty(formatter, regex);
            log = Logger.getLogger(classe.getName());
            log.setUseParentHandlers(false);

            ConsoleHandler handler = new ConsoleHandler();
            Formatter logformatter = new LogFormatter();
            handler.setFormatter(logformatter);

            log.addHandler(handler);
            return log;
        }
    }

    public static class LogFormatter extends Formatter {
/*
        private enum ANSI_Levels {
            ANSI_RESET("\u001B[0m"),
            ANSI_BLACK("\u001B[30m"),
            ANSI_RED("\u001B[31m"),
            ANSI_GREEN("\u001B[32m"),
            ANSI_YELLOW("\u001B[33m"),
            ANSI_BLUE("\u001B[34m"),
            ANSI_PURPLE("\u001B[35m"),
            ANSI_CYAN("\u001B[36m"),
            ANSI_WHITE("\u001B[37m");

            private final String level;

            ANSI_Levels(String ansi) { this.level = ansi; }

            public String getLevel() { return level; }
        }
 */

        @Override
        public String format(LogRecord record) {

            StringBuilder sb = new StringBuilder();
            Object [] params;

            switch (record.getLevel().getName()) {
                case "INFO":
                    sb.append(coresANSI.ANSI_CYAN.getCores());
                    sb.append("[").append(DateFormat(record.getMillis())).append("] ");
                    sb.append("[").append(record.getLevel().getName()).append("] ");
                    sb.append(record.getMessage());
                    break;

                case "WARNING":
                    sb.append(coresANSI.ANSI_YELLOW.getCores());
                    sb.append("[").append(DateFormat(record.getMillis())).append("] ");
                    sb.append("[").append(record.getLevel().getName()).append("] ");
                    sb.append(record.getMessage());
                    break;

                case "SEVERE":
                    sb.append(coresANSI.ANSI_RED.getCores());
                    sb.append("[").append(DateFormat(record.getMillis())).append("] ");
                    sb.append("[").append(record.getLevel().getName()).append("] ");
                    sb.append(record.getMessage());
                    break;

                default:
                    break;
            }

            params = record.getParameters();
            if (params != null) {
                sb.append("\t");
                for (int i = 0; i < params.length; i++) {
                    sb.append(params[i]);
                    if (i < params.length - 1) sb.append(", ");
                }
            }

            sb.append(coresANSI.ANSI_RESET.getCores());
            sb.append("\n");
            return sb.toString();
        }

        private String DateFormat (long mm) {
            SimpleDateFormat dtFormat  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date dtResult = new Date(mm);
            return dtFormat.format(dtResult);
        }
    }
}
