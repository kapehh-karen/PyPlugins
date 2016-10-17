package me.kapehh.net.pyplugins.core;

import me.kapehh.net.pyplugins.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Arrays;

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

        // Вырезаем из аргументов все лишнее (имеется ввиду команду)
        String[] newArgs = Arrays.copyOfRange(args, 1, args.length);

        boolean ret = false;
        // Вызываем во всех плагинах событие ввода команды
        for (PyPluginInstance pluginInstance : main.getPyPluginInstances()) {
            try {
                if (pluginInstance.getPyPlugin() != null) {
                    // Передаем PyCommand в плагины (т.е. первый аргумент args[0])
                    // Если хотя бы один из плагинов обработал команду, возвращаем true
                    ret |= pluginInstance.getPyPlugin().executeCommand(sender, args[0], newArgs);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ret;
    }
}
