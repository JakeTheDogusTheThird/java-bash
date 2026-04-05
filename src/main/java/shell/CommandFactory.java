package shell;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private static final Map<String, Command> commands = new HashMap<>();

    static {
        commands.put("exit", new Exit());
        commands.put("echo", new Echo());
        commands.put("type", new Type());
    }

    public Command getCommand(String command) {
        return commands.getOrDefault(command, new NotACommand());
    }

    public static boolean isBuiltIn(String command) {
        return commands.containsKey(command);
    }
}
