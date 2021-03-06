package by.htp4.bitreight.library.command;

import by.htp4.bitreight.library.command.impl.GetCatalogBySearchCommand;
import by.htp4.bitreight.library.command.impl.GetCatalogCommand;
import by.htp4.bitreight.library.command.impl.LoginationCommand;
import by.htp4.bitreight.library.command.impl.RegistrationCommand;

import java.util.HashMap;
import java.util.Map;

public class CommandHelper {
    private static final CommandHelper instance = new CommandHelper();

    private Map<CommandName, Command> commands = new HashMap<>();

    private CommandHelper() {
        commands.put(CommandName.REGISTRATION, new RegistrationCommand());
        commands.put(CommandName.LOGINATION, new LoginationCommand());
//        commands.put(CommandName.LOGOUT, new GetCatalogCommand());
        commands.put(CommandName.CATALOG, new GetCatalogCommand());
        commands.put(CommandName.CATALOG_SEARCH, new GetCatalogBySearchCommand());
    }

    public static CommandHelper getInstance() {
        return instance;
    }

    public Command getCommand(String name) {
        return commands.get(CommandName.valueOf(name.toUpperCase()));
    }
}
