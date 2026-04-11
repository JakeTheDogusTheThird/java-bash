package shell.command;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ExternalCommand implements Command{
    private final String path;

    public ExternalCommand(String path) {
        this.path = path;
    }

    @Override
    public CommandResult execute(String... args) {
        Runtime runtime = Runtime.getRuntime();
        try {
            Process process = runtime.exec(this.path, args);
            InputStream processResult = process.getInputStream();
            StringBuilder sb = new StringBuilder();
            int c;
            while ((c = processResult.read()) != -1) {
                sb.append((char) c);
            }
            return new CommandResult(sb.toString());
        } catch (IOException ioe) {
            return new CommandResult("IO ERROR");
        }
    }
}
