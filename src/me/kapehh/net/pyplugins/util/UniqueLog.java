package me.kapehh.net.pyplugins.util;

import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

import java.util.logging.Logger;

/**
 * Created by karen on 17.10.2016.
 */
public class UniqueLog {
    private Logger logger = null;
    private CommandSender sender = null;

    public UniqueLog(Logger logger) {
        this.logger = logger;
    }

    public UniqueLog(CommandSender sender) {
        this.sender = sender;
    }

    public UniqueLog(Logger logger, CommandSender sender) {
        this.logger = logger;
        this.sender = sender;
    }

    public void log(String message) {
        if ((sender != null) && !(sender instanceof ConsoleCommandSender))
            sender.sendMessage(message);
        if (logger != null)
            logger.info(message);
    }
}
