package me.kapehh.net.pyplugins.core;

import me.kapehh.net.pyplugins.Main;
import me.kapehh.net.pyplugins.core.python.PyListenerBase;
import me.kapehh.net.pyplugins.core.python.PyPlugin;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;

/**
 * Created by karen on 26.09.2016.
 */
public class PyPluginInstance {
    private Main main;
    private PythonInterpreter interpreter;
    private String name;
    private PyListenerBase bukkitListener;
    private PyPlugin pyPlugin;

    public PyPluginInstance(Main main, String name, PythonInterpreter interpreter) {
        this.main = main;
        this.name = name;
        this.interpreter = interpreter;
    }

    // Возвращает название плагина
    public String getName() {
        return name;
    }

    public void shutdown() {
        interpreter.cleanup();
        interpreter.close();
        interpreter = null;
    }

    // ----------------------------------------------

    // Слушатель событий Bukkit
    public PyListenerBase getBukkitListener() {
        return bukkitListener;
    }

    public void setBukkitListener(PyObject bukkitListener) {
        this.bukkitListener = (PyListenerBase) bukkitListener.__tojava__(PyListenerBase.class);
    }

    // Экземпляр PyПлагина
    public PyPlugin getPyPlugin() {
        return pyPlugin;
    }

    public void setPyPlugin(PyObject pyPlugin) {
        this.pyPlugin = (PyPlugin) pyPlugin.__tojava__(PyPlugin.class);
    }
}
