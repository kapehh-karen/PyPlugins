package me.kapehh.net.pyplugins;

import me.kapehh.net.pyplugins.core.PyCommandExecutor;
import me.kapehh.net.pyplugins.core.PyPluginInstance;
import me.kapehh.net.pyplugins.util.UniqueLog;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by karen on 26.09.2016.
 */
public class PyPluginMain extends JavaPlugin {
    public static PyPluginMain instance = null;

    @Override
    public void onEnable() {
        instance = this;
        PyPluginManager.setDataFolder(this.getDataFolder());

        // Регистрируем команды
        this.getCommand("pyplugins").setExecutor(this);
        this.getCommand("pycommand").setExecutor(new PyCommandExecutor());

        // Чекаем PyПлагины
        if (!this.getDataFolder().exists())
            this.getDataFolder().mkdirs();

        this.saveDefaultConfig();
        List<String> pluginsForLoad = this.getConfig().getStringList("plugins");

        Logger log = this.getLogger();
        log.info("Plugins from config: " + StringUtils.join(pluginsForLoad, ", "));
        log.info("Start task loading PyPlugins...");

        // Запускаем Task через 1 тик, чтобы код выполнился после загрузки всех плагинов
        new BukkitRunnable() {

            @Override
            public void run() {
                int loadedPyPlugins = 0;

                UniqueLog uniqueLog = new UniqueLog(log);
                for (String pluginName : pluginsForLoad) {
                    File file = new File(getDataFolder(), pluginName);
                    if (file.isDirectory()) {
                        // Если плагин был загружен, увеличиваем счетчик
                        if (PyPluginManager.loadPyPlugin(file, uniqueLog))
                            loadedPyPlugins++;
                    }
                }

                // Если плагины небыли загружены,
                // значит требуется просто инициализировать библиотеку jython (т.к. размер у неё достаточно большой)
                // это делается для того, чтобы в дальнейшем (во время работы сервера) не зависал сервер
                if (loadedPyPlugins <= 0) {
                    PyPluginManager.emptyLoad();
                }
            }
        }.runTaskLater(this, 1);
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (cmd.getName().equalsIgnoreCase("pyplugins")) {

            // Если не ОП - идем мимо
            if (!sender.isOp()) {
                sender.sendMessage(ChatColor.RED + "Only for OP!");
                return true;
            }

            if (args.length == 0) {
                // Команда /pyp
                int cnt = PyPluginManager.getPyPluginInstances().size();
                int i = 0;
                StringBuilder sb = new StringBuilder("PyPlugins (").append(cnt).append("): ");
                for (PyPluginInstance pluginInstance : PyPluginManager.getPyPluginInstances()) {
                    i++;
                    sb.append(ChatColor.GREEN).append(pluginInstance.getName()).append(ChatColor.RESET);
                    if (i < cnt) sb.append(", ");
                }
                sender.sendMessage(sb.toString());
                return true;

            } else if (args.length == 1 && args[0].equalsIgnoreCase("thread-list")) {
                // Команда /pyp thread-list
                StringBuilder sb = new StringBuilder("Jython threads:\n");
                for (Thread thread : PyPluginThreadManager.getRunningJythonThreads())
                    sb.append(" - ").append(thread.getName()).append('\n');
                sender.sendMessage(sb.toString());
                return true;

            } else if (args.length < 2) {
                // Больше нет команд, которые принимают менее двух аргументов
                return false;
            }

            String actionName = args[0];
            String actionArg = args[1];

            UniqueLog uniqueLog = new UniqueLog(getLogger(), sender);
            if (actionName.equalsIgnoreCase("reload")) {
                // Делаем туды-сюды
                PyPluginManager.unloadPyPlugin(actionArg, uniqueLog); // Выгрузили (туды)
                PyPluginManager.loadPyPlugin(actionArg, uniqueLog); // Загрузили (сюды)
                return true;

            } else if (actionName.equalsIgnoreCase("load")) {
                PyPluginManager.loadPyPlugin(actionArg, uniqueLog);
                return true;

            } else if (actionName.equalsIgnoreCase("unload")) {
                PyPluginManager.unloadPyPlugin(actionArg, uniqueLog);
                return true;

            } else if (actionName.equalsIgnoreCase("thread-stop")) {
                Thread thread = PyPluginThreadManager.getThreadByName(actionArg);
                if (thread != null) {
                    thread.interrupt();
                    sender.sendMessage("Thread interrupt.");
                } else {
                    sender.sendMessage("Thread not found!");
                }
                return true;
            }
        }

        return false;
    }

    @Override
    public void onDisable() {
        UniqueLog uniqueLog = new UniqueLog(getLogger());

        List<String> listOfPluginNames = new ArrayList<>();
        for (PyPluginInstance pyPlugin : PyPluginManager.getPyPluginInstances()) {
            listOfPluginNames.add(pyPlugin.getName());
        }
        this.getConfig().set("plugins", listOfPluginNames);
        this.saveConfig();

        while (PyPluginManager.getPyPluginInstances().size() > 0) {
            PyPluginManager.unloadPyPlugin(
                    PyPluginManager.getPyPluginInstances().get(0),
                    uniqueLog);
        }

        instance = null;
    }

}
