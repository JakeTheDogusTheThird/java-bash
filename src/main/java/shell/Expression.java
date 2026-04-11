package shell;

import java.util.Arrays;

public class Expression {
    String[] tokens;

    public Expression(String command) {
        this.tokens = command.split(" ");
    }

    public String command() {
        return this.tokens[0];
    }

    public String[] arguments() {
        return Arrays.copyOfRange(this.tokens, 1, this.tokens.length);
    }
}
