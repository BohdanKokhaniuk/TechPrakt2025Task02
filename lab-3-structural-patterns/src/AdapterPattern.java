import java.io.FileWriter;
import java.io.IOException;

interface ILogger {
    void log(String message);
    void error(String message);
    void warn(String message);
}

class ConsoleLogger implements ILogger {
    public void log(String msg) { System.out.println("\u001B[32mLOG: " + msg + "\u001B[0m"); }
    public void error(String msg) { System.out.println("\u001B[31mERROR: " + msg + "\u001B[0m"); }
    public void warn(String msg) { System.out.println("\u001B[33mWARN: " + msg + "\u001B[0m"); }
}

class SimpleFileWriter {
    private final String path;

    public SimpleFileWriter(String path) {
        this.path = path;
    }

    public void write(String message) {
        try (FileWriter fw = new FileWriter(path, true)) {
            fw.write(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeLine(String message) {
        write(message + System.lineSeparator());
    }
}

class FileLoggerAdapter implements ILogger {
    private final SimpleFileWriter writer;

    public FileLoggerAdapter(String filePath) {
        this.writer = new SimpleFileWriter(filePath);
    }

    public void log(String msg) { writer.writeLine("LOG: " + msg); }
    public void error(String msg) { writer.writeLine("ERROR: " + msg); }
    public void warn(String msg) { writer.writeLine("WARN: " + msg); }
}

class AdapterPattern {
    public static void run() {
        ILogger logger = new ConsoleLogger();
        logger.log("System started");
        logger.warn("Low memory");
        logger.error("File not found");

        ILogger fileLogger = new FileLoggerAdapter("log.txt");
        fileLogger.log("Writing to file");
        fileLogger.warn("Warning to file");
        fileLogger.error("Error to file");
    }
}

