import java.io.File;

public class Logger {
    private File file;

    public Logger(String filename){

        file = new File(filename);
    }

    public static synchronized Logger getInstance()
}