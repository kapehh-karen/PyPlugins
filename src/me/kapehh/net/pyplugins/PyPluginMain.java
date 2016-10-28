package me.kapehh.net.pyplugins;

import me.kapehh.net.pyplugins.core.PyCommandExecutor;
import me.kapehh.net.pyplugins.core.PyPluginInstance;
import me.kapehh.net.pyplugins.util.UniqueLog;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.python.core.PyString;
import org.python.core.PySystemState;
import org.python.util.PythonInterpreter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by karen on 26.09.2016.
 */
public class PyPluginMain extends JavaPlugin {
    public static PyPluginMain instance = null;
    private static List<PyPluginInstance> pyPluginInstances = new ArrayList<>();

    // --------------------------------------------------------------------------

    public static List<PyPluginInstance> getPyPluginInstances() {
        return pyPluginInstances;
    }

    public static PyPluginInstance getLoadedPyPlugin(String name) {
        for (PyPluginInstance p : pyPluginInstances) {
            if (p.getName().equalsIgnoreCase(name)) {
                return p;
            }
        }
        return null;
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

    private boolean loadPyPlugin(String namePyPlugin, UniqueLog log) {
        return loadPyPlugin(new File(getDataFolder(), namePyPlugin), log);
    }

    private boolean loadPyPlugin(File filePyPlugin, UniqueLog log) {
        boolean ret;
        log.log("Loading PyPlugin: " + filePyPlugin.getName());

        // Если это не директория, то это и не плагин наверн
        if (!filePyPlugin.exists() || !filePyPlugin.isDirectory()) {
            log.log("[-] File is not directory or not exists: " + filePyPlugin.getName());
            return false;
        }

        // Если плагин уже загружен
        if (getLoadedPyPlugin(filePyPlugin.getName()) != null) {
            log.log("[-] Plugin already loaded");
            return false;
        }

        // Меняем рабочую директорию на папку плагина
        String pathToPyPlugin = filePyPlugin.getAbsolutePath();
        PySystemState props = new PySystemState();
        props.setCurrentWorkingDir(pathToPyPlugin);
        props.path.append(new PyString(pathToPyPlugin));

        // Создаем Python интерпретатор
        PythonInterpreter pythonInterpreter = new PythonInterpreter(null, props);

        // Создаем общий объект плагина
        PyPluginInstance pyPluginInstance = new PyPluginInstance(filePyPlugin.getName(), pythonInterpreter);

        // Передаем его в Python скрипт
        pythonInterpreter.set("__pyplugin__", pyPluginInstance);

        File pyMain = new File(filePyPlugin, "main.py");
        if (!pyMain.exists()) {
            log.log("[-] File 'main.py' in '" + filePyPlugin.getName() + "' not found!");
            return false;
        }

        log.log("====== [Start execute: " + filePyPlugin.getName() + "] ======");
        try {
            // Выполняем скрипт инициализации
            pythonInterpreter.execfile(PyPluginMain.class.getResourceAsStream("/init.py"));
            log.log("[+] Successful executed 'init.py' for " + filePyPlugin.getName());

            // Выполняем скрипт плагина
            pythonInterpreter.execfile(pyMain.getAbsolutePath() /*new FileInputStream(pyMain)*/);
            log.log("[+] Successful executed 'main.py' for " + filePyPlugin.getName());

            // Ну если уж главный скрипт выполнился, то добавляем в общий список
            pyPluginInstances.add(pyPluginInstance);

            ret = true;
        } catch (Throwable e) {
            e.printStackTrace();
            pyPluginInstance.shutdown(); // Просто подчищаем мусор после наших попыток что-то сделать
            ret = false;
        }

        // Если все ок и небыло ошибок, тогда вызываем onEnable у плагина
        if (ret) {
            try {
                // NOTE: Заключаем вызов метода onEnable в try-catch, чтобы ловить ошибки в python скрипте
                // Выполняем метод onEnable у плагина
                if (pyPluginInstance.getPyPlugin() != null)
                    pyPluginInstance.getPyPlugin().onEnable();
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }

        log.log("====== [Complete: " + filePyPlugin.getName() + "] ======");
        return ret;
    }

    private boolean unloadPyPlugin(String namePyPlugin, UniqueLog log) {
        return unloadPyPlugin(getLoadedPyPlugin(namePyPlugin), log);
    }

    private boolean unloadPyPlugin(PyPluginInstance pyPluginInstance, UniqueLog log) {
        if (pyPluginInstance == null) {
            log.log("[-] Not found PyPlugin");
            return false;
        }

        log.log("[+] Unloading PyPlugin: " + pyPluginInstance.getName());

        try {
            // NOTE: Заключаем вызов метода onDisable в try-catch, чтобы ловить ошибки в python скрипте
            // Вызываем метод onDisable у плагина (ну типа выгружаем его)
            // Предупреждаем его об этом, чтоб не шалил
            if (pyPluginInstance.getPyPlugin() != null)
                pyPluginInstance.getPyPlugin().onDisable();
        } catch (Throwable e) {
            e.printStackTrace();
        }

        pyPluginInstance.shutdown();
        pyPluginInstances.remove(pyPluginInstance);

        log.log("[+] Successful unload PyPlugin: " + pyPluginInstance.getName());
        return true;
    }

    // --------------------------------------------------------------------------

    @Override
    public void onEnable() {
        instance = this;

        Logger log = getLogger();
        int loadedPyPlugins = 0;
        log.info("Start loading PyPlugins...");

        // Чекаем PyПлагины
        if (!getDataFolder().exists())
            getDataFolder().mkdirs();

        UniqueLog uniqueLog = new UniqueLog(log);
        for (File file : getDataFolder().listFiles()) {
            if (file.isDirectory()) {
                // Если плагин был загружен, увеличиваем счетчик
                if (loadPyPlugin(file, uniqueLog))
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

        log.info("Loaded " + loadedPyPlugins + " PyPlugins");

        // Регистрируем команды
        getCommand("pyplugins").setExecutor(this);
        getCommand("pycommand").setExecutor(new PyCommandExecutor());
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
                int cnt = pyPluginInstances.size();
                int i = 0;
                StringBuilder sb = new StringBuilder("PyPlugins (").append(cnt).append("): ");
                for (PyPluginInstance pluginInstance : pyPluginInstances) {
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

            UniqueLog uniqueLog = new UniqueLog(getLogger(), sender);
            if (actionName.equalsIgnoreCase("reload")) {
                // Делаем туды-сюды
                unloadPyPlugin(actionArg, uniqueLog); // Выгрузили (туды)
                loadPyPlugin(actionArg, uniqueLog); // Загрузили (сюды)
                return true;
            } else if (actionName.equalsIgnoreCase("load")) {
                loadPyPlugin(actionArg, uniqueLog);
                return true;
            } else if (actionName.equalsIgnoreCase("unload")) {
                unloadPyPlugin(actionArg, uniqueLog);
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
        UniqueLog uniqueLog = new UniqueLog(getLogger());
        while (pyPluginInstances.size() > 0) {
            unloadPyPlugin(pyPluginInstances.get(0), uniqueLog);
        }

        instance = null;
    }

}
