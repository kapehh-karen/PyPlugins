package me.kapehh.net.pyplugins;

import me.kapehh.net.pyplugins.core.PyPluginInstance;
import me.kapehh.net.pyplugins.eventwrappers.BukkitEvents;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.python.util.PythonInterpreter;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by karen on 26.09.2016.
 */
public class Main extends JavaPlugin {
    private List<PyPluginInstance> pyPluginInstances = new ArrayList<>();

    // --------------------------------------------------------------------------

    // jython-threads
    private List<Thread> getRunningThreads(String groupName) {
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

    public List<PyPluginInstance> getPyPluginInstances() {
        return pyPluginInstances;
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
        PyPluginInstance pyPluginInstance = new PyPluginInstance(this, filePyPlugin.getName(), pythonInterpreter);

        // Передаем его в Python скрипт
        pythonInterpreter.set("PyCurrentPluginInstance", pyPluginInstance);

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

            // Выполняем метод onEnable у плагина (ну типа он загружен)
            if (pyPluginInstance.getPyPlugin() != null)
                pyPluginInstance.getPyPlugin().onEnable();

            ret = true;
        } catch (Exception e) {
            e.printStackTrace();
            ret = false;
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

        // Вызываем метод onDisable у плагина (ну типа выгружаем его)
        // Предупреждаем его об этом, чтоб не шалил
        if (pyPluginInstance.getPyPlugin() != null)
            pyPluginInstance.getPyPlugin().onDisable();

        pyPluginInstance.shutdown();
        this.pyPluginInstances.remove(pyPluginInstance);

        log.info("Successful unload PyPlugin: " + pyPluginInstance.getName());
        return true;
    }

    // --------------------------------------------------------------------------

    @Override
    public void onEnable() {
        Logger log = getLogger();
        log.info("Loading PyPlugins...");

        // Чекаем PyПлагины
        if (!getDataFolder().exists())
            getDataFolder().mkdirs();

        for (File file : getDataFolder().listFiles()) {
            if (file.isDirectory()) {
                loadPyPlugin(file);
            }
        }

        // Регистрируем все Bukkit события
        getServer().getPluginManager().registerEvents(new BukkitEvents(this), this);
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        System.out.println("Trying execute command: " + cmd.getName());

        if (!sender.isOp()) {
            sender.sendMessage("Always for OP!");
            return true;
        }
        if (cmd.getName().equalsIgnoreCase("pyplugins")) {
            if (args.length < 2) {
                return false;
            }

            String actionName = args[0];
            String actionArg = args[1];

            if (actionName.equalsIgnoreCase("reload")) {
                // Может зависнуть если есть поток не являющийся демоном, лучше предварительно убить этот поток
                // Делаем туды-сюды
                if (unloadPyPlugin(actionArg)) // Выгрузили (туды)
                    loadPyPlugin(actionArg); // Загрузили (сюды)
                return true;
            } else if (actionName.equalsIgnoreCase("load")) {
                loadPyPlugin(actionArg);
                return true;
            } else if (actionName.equalsIgnoreCase("unload")) {
                unloadPyPlugin(actionArg);
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
    }

}
