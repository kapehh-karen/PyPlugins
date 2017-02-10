package me.kapehh.net.pyplugins;

import me.kapehh.net.pyplugins.core.PyPluginInstance;
import me.kapehh.net.pyplugins.util.UniqueLog;
import org.python.core.PyString;
import org.python.core.PySystemState;
import org.python.util.PythonInterpreter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by karen on 10.02.2017.
 */
public class PyPluginManager {
    private static List<PyPluginInstance> pyPluginInstances = new ArrayList<>();
    private static File dataFolder;

    public static List<PyPluginInstance> getPyPluginInstances() {
        return pyPluginInstances;
    }

    static PyPluginInstance getLoadedPyPlugin(String name) {
        for (PyPluginInstance p : pyPluginInstances) {
            if (p.getName().equalsIgnoreCase(name)) {
                return p;
            }
        }
        return null;
    }

    static void setDataFolder(File dataFolder) {
        PyPluginManager.dataFolder = dataFolder;
    }

    static boolean loadPyPlugin(String namePyPlugin, UniqueLog log) {
        return loadPyPlugin(new File(PyPluginManager.dataFolder, namePyPlugin), log);
    }

    static boolean loadPyPlugin(File filePyPlugin, UniqueLog log) {
        boolean ret;
        String pyPluginName = filePyPlugin.getName();
        log.log("Loading PyPlugin: " + pyPluginName);

        // Если это не директория, то это и не плагин наверн
        if (!filePyPlugin.exists() || !filePyPlugin.isDirectory()) {
            log.log("[-] File is not directory or not exists: " + pyPluginName);
            return false;
        }

        // Если плагин уже загружен
        if (getLoadedPyPlugin(pyPluginName) != null) {
            log.log("[-] Plugin already loaded");
            return false;
        }

        // Создаем Python интерпретатор и добавляем нужные пути
        PythonInterpreter pythonInterpreter = new PythonInterpreter();
        PySystemState props = pythonInterpreter.getSystemState();
        PyString bukkitPluginFolder = new PyString(PyPluginManager.dataFolder.getAbsolutePath());
        props.setCurrentWorkingDir(PyPluginManager.dataFolder.getAbsolutePath()); // python plugin folder
        // once add path to sys.path
        if (!props.path.contains(bukkitPluginFolder)) {
            props.path.append(bukkitPluginFolder);
        }

        // Создаем общий объект плагина
        PyPluginInstance pyPluginInstance = new PyPluginInstance(filePyPlugin, pythonInterpreter);

        // Передаем его в Python скрипт
        pythonInterpreter.set("__pyplugin__", pyPluginInstance);

        File pyMain = new File(filePyPlugin, "main.py");
        if (!pyMain.exists()) {
            log.log("[-] File 'main.py' in '" + pyPluginName + "' not found!");
            return false;
        }

        log.log("====== [Start execute: " + pyPluginName + "] ======");
        try {
            // Выполняем скрипт инициализации
            pythonInterpreter.execfile(PyPluginMain.class.getResourceAsStream("/init.py"));
            log.log("[+] Successful executed 'init.py' for " + pyPluginName);

            // Выполняем скрипт плагина
            pythonInterpreter.execfile(pyMain.getAbsolutePath());
            log.log("[+] Successful executed 'main.py' for " + pyPluginName);

            // Ну если уж главный скрипт выполнился, то добавляем в общий список
            pyPluginInstances.add(pyPluginInstance);
            log.log("[+] Successful loaded: " + pyPluginName);

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
                    pyPluginInstance.getPyPlugin().enable();
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }

        log.log("====== [Complete: " + pyPluginName + "] ======");
        return ret;
    }

    static boolean unloadPyPlugin(String namePyPlugin, UniqueLog log) {
        return unloadPyPlugin(getLoadedPyPlugin(namePyPlugin), log);
    }

    static boolean unloadPyPlugin(PyPluginInstance pyPluginInstance, UniqueLog log) {
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
                pyPluginInstance.getPyPlugin().disable();
        } catch (Throwable e) {
            e.printStackTrace();
        }

        pyPluginInstance.shutdown();
        pyPluginInstances.remove(pyPluginInstance);

        log.log("[+] Successful unload PyPlugin: " + pyPluginInstance.getName());
        return true;
    }

    static void emptyLoad() {
        PythonInterpreter pythonInterpreter = new PythonInterpreter();
        pythonInterpreter.cleanup();
        pythonInterpreter.close();
    }
}
