package me.kapehh.net.pyplugins.core;

import me.kapehh.net.pyplugins.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Created by karen on 29.09.2016.
 */
public class PyCommandExecutor implements CommandExecutor {
    private Main main;

    public PyCommandExecutor(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        // Если ввели просто /pyc то досвидули, требуется /pyc PyCommand
        if (args.length < 1)
            return false;

        boolean ret = false;
        // Вызываем во всех плагинах событие ввода команды
        for (PyPluginInstance pluginInstance : main.getPyPluginInstances())
            if (pluginInstance.getPyPlugin() != null)
                // Передаем PyCommand в плагины (т.е. первый аргумент args[0])
                // Если хотя бы один из плагинов обработал команду, возвращаем true
                ret |= pluginInstance.getPyPlugin().onCommand(sender, args[0], args);
        return ret;
    }
}
