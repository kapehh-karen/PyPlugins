package me.kapehh.net.pyplugins.core;

import me.kapehh.net.pyplugins.core.python.PyListener;
import me.kapehh.net.pyplugins.core.python.PyPlugin;
import org.bukkit.event.HandlerList;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by karen on 26.09.2016.
 */
public class PyPluginInstance {
    private PythonInterpreter interpreter;
    private String name;
    private List<PyListener> pyListeners = new ArrayList<>();
    private PyPlugin pyPlugin;

    public PyPluginInstance(String name, PythonInterpreter interpreter) {
        this.name = name;
        this.interpreter = interpreter;
    }

    // Возвращает название плагина
    public String getName() {
        return name;
    }

    public void shutdown() {
        // Удаляем слушатели событий
        for (PyListener pyListener : this.pyListeners)
            HandlerList.unregisterAll(pyListener);
        this.pyListeners.clear();

        // Очищаем интерпретатор
        this.interpreter.cleanup();
        this.interpreter.close();
        this.interpreter = null;
    }

    // ----------------------------------------------

    // Слушатель событий
    public void addPyListener(PyObject listener) {
        this.pyListeners.add((PyListener) listener.__tojava__(PyListener.class));
    }

    // Экземпляр PyПлагина
    public PyPlugin getPyPlugin() {
        return pyPlugin;
    }

    public void setPyPlugin(PyObject pyPlugin) {
        this.pyPlugin = (PyPlugin) pyPlugin.__tojava__(PyPlugin.class);
    }
}
