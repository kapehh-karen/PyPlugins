package me.kapehh.net.pyplugins;

import me.kapehh.net.pyplugins.EventWrappers.BukkitEvents;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.python.util.PythonInterpreter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Logger;

/**
 * Created by karen on 26.09.2016.
 */
public class Main extends JavaPlugin {
    private PythonInterpreter pythonInterpreter;

    @Override
    public void onEnable() {
        Logger log = getLogger();
        log.info("Loading PyPlugins...");

        pythonInterpreter = new PythonInterpreter();

        // Чекаем PyПлагины
        if (!getDataFolder().exists())
            getDataFolder().mkdirs();
        for (File file : getDataFolder().listFiles()) {
            if (file.isDirectory()) {
                log.info("Loading PyPlugin: " + file.getName());
                File pyMain = new File(file, "main.py");
                if (!pyMain.exists()) {
                    log.warning("[-] File 'main.py' in '" + file.getName() + "' not found!");
                    continue;
                }
                try {
                    pythonInterpreter.execfile(new FileInputStream(pyMain));
                    log.info("[+] Successful loaded PyPlugin: " + file.getName());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }

        // Регистрируем все Bukkit события
        getServer().getPluginManager().registerEvents(new BukkitEvents(), this);
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
        if (cmd.getName().equalsIgnoreCase("pyplugins")) {
            if (args.length < 2) {
                return false;
            }

            String action = args[0];
            String pyginName = args[1];

            if (action.equalsIgnoreCase("reload")) {

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
