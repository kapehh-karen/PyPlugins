package me.kapehh.net.pyplugins;

import me.kapehh.net.pyplugins.core.PyPluginCore;
import me.kapehh.net.pyplugins.eventwrappers.BukkitEvents;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.python.core.PyDictionary;
import org.python.core.PySystemState;
import org.python.util.PythonInterpreter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * Created by karen on 26.09.2016.
 */
public class Main extends JavaPlugin {
    private PythonInterpreter pythonInterpreter;

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

    @Override
    public void onEnable() {
        Logger log = getLogger();
        log.info("Loading PyPlugins...");

        pythonInterpreter = new PythonInterpreter();
        pythonInterpreter.set("PyPluginsInstance", this);
        PyPluginCore pyPluginCore = new PyPluginCore();
        pythonInterpreter.set("PyPlugin", pyPluginCore);

        log.info("Execute init.py for <PyPlugin>");
        pythonInterpreter.execfile(Main.class.getResourceAsStream("/init.py"));
        log.info("[+] Successful executed init.py");

        // Чекаем PyПлагины
        if (!getDataFolder().exists())
            getDataFolder().mkdirs();
        for (File file : getDataFolder().listFiles()) {
            if (file.isDirectory()) {
                log.info("Loading PyPlugin: " + file.getName());
                pythonInterpreter.getSystemState().setCurrentWorkingDir(file.getAbsolutePath());
                File pyMain = new File(file, "main.py");
                if (!pyMain.exists()) {
                    log.warning("[-] File 'main.py' in '" + file.getName() + "' not found!");
                    continue;
                }
                try {
                    //pythonInterpreter.exec(new PyFileReader(new FileReader(pyMain)));
                    pythonInterpreter.execfile(new FileInputStream(pyMain));
                    log.info("[+] Successful loaded PyPlugin: " + file.getName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        // Регистрируем все Bukkit события
        //getServer().getPluginManager().registerEvents(new BukkitEvents(), this);
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
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
                pythonInterpreter.cleanup();
                pythonInterpreter.close();
                return true;
            }
        }
        return false;
    }

    @Override
    public void onDisable() {
        pythonInterpreter.cleanup();
        pythonInterpreter.close();
    }

}
