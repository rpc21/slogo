package GUI.Commands;

import java.util.function.Consumer;

public interface CommandExecutable {

    void giveAbilityToRunCommands(Consumer<String> commandAccess);
    void runCommand(String command);
}
