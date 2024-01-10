package Lab.ItWorked;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.*;

public class LoggerAPI {

    public static class teste {

        static LoggerAPI.tried<teste> x = new LoggerAPI.tried<>();
        public static Logger log = x.retorno(teste.class);


        public static void main(String[] args) {
            System.out.println("-- main method starts --");

            log.info("in MyClass");
            log.warning("a test warning");

            //===================================================

            //Logger logger = x.retorno(teste.class);
            //logger.setUseParentHandlers(false);

            //ConsoleHandler handler = new ConsoleHandler();

            //Formatter formatter = new LogFormatter();
           // handler.setFormatter(formatter);

           // logger.addHandler(handler);

            //logger.info("in MyClass");
            //logger.warning("a test warning");
            //logger.log(Level.INFO, "in MyClasses");

        }
    }

    public static class tried<T>{

        public Logger retorno(Class<T> classe){
            Logger log;
            final String formatter = "java.util.logging.SimpleFormatter.format";
            final String regex = "[%1$tF %1$tT] [%4$-7s] %5$s %n";

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


    public static class LogFormatter extends Formatter
    {
        // ANSI escape code
        public static final String ANSI_RESET = "\u001B[0m";
        public static final String ANSI_BLACK = "\u001B[30m";
        public static final String ANSI_RED = "\u001B[31m";
        public static final String ANSI_GREEN = "\u001B[32m";
        public static final String ANSI_YELLOW = "\u001B[33m";
        public static final String ANSI_BLUE = "\u001B[34m";
        public static final String ANSI_PURPLE = "\u001B[35m";
        public static final String ANSI_CYAN = "\u001B[36m";
        public static final String ANSI_WHITE = "\u001B[37m";

        // Here you can configure the format of the output and
        // its color by using the ANSI escape codes defined above.

        // format is called for every console log message
        @Override
        public String format(LogRecord record)
        {
            // This example will print date/time, class, and log level in yellow,
            // followed by the log message and it's parameters in white .
            StringBuilder builder = new StringBuilder();

            switch (record.getLevel().getName()) {
                case "INFO":
                builder.append(ANSI_YELLOW);

                builder.append("[").append(calcDate(record.getMillis())).append("] ");
                builder.append("[").append(record.getLevel().getName()).append("] ");

                //builder.append(ANSI_WHITE);
                //builder.append(" - ");
                builder.append(record.getMessage());
                break;

                case "WARNING":
                    builder.append(ANSI_PURPLE);

                    builder.append("[").append(calcDate(record.getMillis())).append("] ");
                    builder.append("[").append(record.getLevel().getName()).append("] ");

                    //builder.append(ANSI_WHITE);
                    //builder.append(" - ");
                    builder.append(record.getMessage());
                    break;

                case "SEVERE":
                    builder.append(ANSI_CYAN);

                    builder.append("[").append(calcDate(record.getMillis())).append("] ");
                    builder.append("[").append(record.getLevel().getName()).append("] ");

                    //builder.append(ANSI_WHITE);
                    //builder.append(" - ");
                    builder.append(record.getMessage());
                    break;
                default:
                    break;
            }

            Object[] params = record.getParameters();

            if (params != null)
            {
                builder.append("\t");
                for (int i = 0; i < params.length; i++)
                {
                    builder.append(params[i]);
                    if (i < params.length - 1)
                        builder.append(", ");
                }
            }

            builder.append(ANSI_RESET);
            builder.append("\n");
            return builder.toString();
        }

        private String calcDate(long millisecs) {
            SimpleDateFormat date_format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date resultdate = new Date(millisecs);
            return date_format.format(resultdate);
        }
    }
}
