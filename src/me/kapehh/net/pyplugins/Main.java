package me.kapehh.net.pyplugins;

import me.kapehh.net.pyplugins.core.PyCommandExecutor;
import me.kapehh.net.pyplugins.core.PyPluginInstance;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.python.util.PythonInterpreter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by karen on 26.09.2016.
 */
public class Main extends JavaPlugin {
    public static Main instance = null;
    private List<PyPluginInstance> pyPluginInstances = new ArrayList<>();

    // --------------------------------------------------------------------------

    public List<PyPluginInstance> getPyPluginInstances() {
        return pyPluginInstances;
    }

    private Thread getThreadByName(String threadName) {
        for (Thread thread : getRunningJythonThreads()) {
            if (thread.getName().equalsIgnoreCase(threadName))
                return thread;
        }
        return null;
    }

    private List<Thread> getRunningJythonThreads() {
        return getRunningJythonThreads("jython-threads");
    }

    private List<Thread> getRunningJythonThreads(String groupName) {
        List<Thread> threads = new ArrayList<>();
        ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();
        ThreadGroup parent;
        while ((parent = threadGroup.getParent()) != null) {
            threadGroup = parent;
            Thread[] threadList = new Thread[threadGroup.activeCount()];
            threadGroup.enumerate(threadList);
            for (Thread thread : threadList) {
                if (thread.getThreadGroup().getName().equalsIgnoreCase(groupName)) {
                    threads.add(thread);
                }
            }
        }
        return threads;
    }

    private PyPluginInstance getLoadedPyPlugin(String name) {
        for (PyPluginInstance p : this.pyPluginInstances) {
            if (p.getName().equalsIgnoreCase(name)) {
                return p;
            }
        }
        return null;
    }

    private boolean loadPyPlugin(String namePyPlugin) {
        return loadPyPlugin(new File(getDataFolder(), namePyPlugin));
    }

    private boolean loadPyPlugin(File filePyPlugin) {
        boolean ret;
        Logger log = getLogger();
        log.info("Loading PyPlugin: " + filePyPlugin.getName());

        // Если это не директория, то это и не плагин наверн
        if (!filePyPlugin.exists() || !filePyPlugin.isDirectory()) {
            log.warning("[-] File is not directory or not exists: " + filePyPlugin.getName());
            return false;
        }

        // Если плагин уже загружен
        if (getLoadedPyPlugin(filePyPlugin.getName()) != null) {
            log.warning("[-] Plugin already loaded");
            return false;
        }

        // Создаем Python интерпретатор
        PythonInterpreter pythonInterpreter = new PythonInterpreter();

        // Меняем его рабочую директорию на папку плагина
        pythonInterpreter.getSystemState().setCurrentWorkingDir(filePyPlugin.getAbsolutePath());

        // Создаем общий объект плагина
        PyPluginInstance pyPluginInstance = new PyPluginInstance(filePyPlugin.getName(), pythonInterpreter);

        // Передаем его в Python скрипт
        pythonInterpreter.set("__pyplugin__", pyPluginInstance);

        File pyMain = new File(filePyPlugin, "main.py");
        if (!pyMain.exists()) {
            log.warning("[-] File 'main.py' in '" + filePyPlugin.getName() + "' not found!");
            return false;
        }

        log.info("====== [Start execute: " + filePyPlugin.getName() + "] ======");
        try {
            // Выполняем скрипт инициализации
            pythonInterpreter.execfile(Main.class.getResourceAsStream("/init.py"));
            log.info("[+] Successful executed 'init.py' for " + filePyPlugin.getName());

            // Выполняем скрипт плагина
            pythonInterpreter.execfile(pyMain.getAbsolutePath() /*new FileInputStream(pyMain)*/);
            log.info("[+] Successful executed 'main.py' for " + filePyPlugin.getName());

            // Ну если уж главный скрипт выполнился, то добавляем в общий список
            this.pyPluginInstances.add(pyPluginInstance);

            ret = true;
        } catch (Exception e) {
            e.printStackTrace();
            ret = false;
        }
        try {
            // NOTE: Заключаем вызов метода onEnable в try-catch, чтобы ловить ошибки в python скрипте
            // Выполняем метод onEnable у плагина
            if (pyPluginInstance.getPyPlugin() != null)
                pyPluginInstance.getPyPlugin().onEnable();
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("====== [Complete: " + filePyPlugin.getName() + "] ======");
        return ret;
    }

    private boolean unloadPyPlugin(String namePyPlugin) {
        return unloadPyPlugin(getLoadedPyPlugin(namePyPlugin));
    }

    private boolean unloadPyPlugin(PyPluginInstance pyPluginInstance) {
        Logger log = getLogger();

        if (pyPluginInstance == null) {
            log.warning("[-] Not found PyPlugin");
            return false;
        }

        log.info("Unloading PyPlugin: " + pyPluginInstance.getName());

        try {
            // NOTE: Заключаем вызов метода onDisable в try-catch, чтобы ловить ошибки в python скрипте
            // Вызываем метод onDisable у плагина (ну типа выгружаем его)
            // Предупреждаем его об этом, чтоб не шалил
            if (pyPluginInstance.getPyPlugin() != null)
                pyPluginInstance.getPyPlugin().onDisable();
        } catch (Exception e) {
            e.printStackTrace();
        }

        pyPluginInstance.shutdown();
        this.pyPluginInstances.remove(pyPluginInstance);

        log.info("Successful unload PyPlugin: " + pyPluginInstance.getName());
        return true;
    }

    // --------------------------------------------------------------------------

    @Override
    public void onEnable() {
        instance = this;

        Logger log = getLogger();
        int loadedPyPlugins = 0;
        log.info("Loading PyPlugins...");

        // Чекаем PyПлагины
        if (!getDataFolder().exists())
            getDataFolder().mkdirs();

        for (File file : getDataFolder().listFiles()) {
            if (file.isDirectory()) {
                // Если плагин был загружен, увеличиваем счетчик
                if (loadPyPlugin(file))
                    loadedPyPlugins++;
            }
        }

        // Если плагины небыли загружены,
        // значит требуется просто инициализировать библиотеку jython (т.к. размер у неё достаточно большой)
        // это делается для того, чтобы в дальнейшем (во время работы сервера) не зависал сервер
        if (loadedPyPlugins <= 0) {
            PythonInterpreter pythonInterpreter = new PythonInterpreter();
            pythonInterpreter.cleanup();
            pythonInterpreter.close();
        }

        log.info("Loaded " + loadedPyPlugins + " PyPlugins.");

        // Регистрируем команды
        getCommand("pyplugins").setExecutor(this);
        getCommand("pycommand").setExecutor(new PyCommandExecutor(this));
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (cmd.getName().equalsIgnoreCase("pyplugins")) {
            // Если не ОП - идем мимо
            if (!sender.isOp()) {
                sender.sendMessage("Only for OP!");
                return true;
            }

            if (args.length == 0) {
                // Команда /pyp
                int cnt = this.pyPluginInstances.size();
                int i = 0;
                StringBuilder sb = new StringBuilder("PyPlugins (").append(cnt).append("): ");
                for (PyPluginInstance pluginInstance : this.pyPluginInstances) {
                    i++;
                    sb.append(ChatColor.GREEN).append(pluginInstance.getName()).append(ChatColor.RESET);
                    if (i < cnt) sb.append(", ");
                }
                sender.sendMessage(sb.toString());
                return true;
            } else if (args.length == 1 && args[0].equalsIgnoreCase("thread-list")) {
                // Команда /pyp thread-list
                StringBuilder sb = new StringBuilder("Jython threads:\n");
                for (Thread thread : getRunningJythonThreads())
                    sb.append(" - ").append(thread.getName()).append('\n');
                sender.sendMessage(sb.toString());
                return true;
            } else if (args.length < 2) {
                // Больше нет команд, которые принимают менее двух аргументов
                return false;
            }

            String actionName = args[0];
            String actionArg = args[1];

            if (actionName.equalsIgnoreCase("reload")) {
                // Делаем туды-сюды
                unloadPyPlugin(actionArg); // Выгрузили (туды)
                loadPyPlugin(actionArg); // Загрузили (сюды)
                return true;
            } else if (actionName.equalsIgnoreCase("load")) {
                loadPyPlugin(actionArg);
                return true;
            } else if (actionName.equalsIgnoreCase("unload")) {
                unloadPyPlugin(actionArg);
                return true;
            } else if (actionName.equalsIgnoreCase("thread-stop")) {
                Thread thread = getThreadByName(actionArg);
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
        while (this.pyPluginInstances.size() > 0) {
            unloadPyPlugin(this.pyPluginInstances.get(0));
        }

        instance = null;
    }

}
