package me.kapehh.net.pyplugins.core;

import me.kapehh.net.pyplugins.core.python.PyListener;
import me.kapehh.net.pyplugins.core.python.PyPlugin;
import org.bukkit.event.HandlerList;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by karen on 26.09.2016.
 */
public class PyPluginInstance {
    private PythonInterpreter interpreter;
    private String path;
    private String name;
    private List<PyListener> pyListeners = new ArrayList<>();
    private PyPlugin pyPlugin = null;
    private PyObject pyPyPlugin = null; // Для более быстрой передачи ссылки в другие python скрипты

    public PyPluginInstance(File filePyPlugin, PythonInterpreter interpreter) {
        this.name = filePyPlugin.getName();
        this.path = filePyPlugin.getAbsolutePath();
        this.interpreter = interpreter;
    }

    // Возвращает название плагина
    public String getName() {
        return name;
    }

    // Возвращает путь к папке PyПлагина
    public String getPath() {
        return path;
    }

    // Java экземпляр PyПлагина
    public PyPlugin getPyPlugin() {
        return pyPlugin;
    }

    // Python экземпляр PyПлагина
    public PyObject getPyPyPlugin() {
        return pyPyPlugin;
    }

    public void shutdown() {
        // Удаляем слушатели событий связанные с нашими PyListener'fvb
        for (PyListener pyListener : this.pyListeners)
            HandlerList.unregisterAll(pyListener);
        this.pyListeners.clear();

        // Очищаем интерпретатор
        this.interpreter.cleanup();
        this.interpreter.close();
        this.interpreter = null;
    }

    /**
     * PYPLUGIN PART
     */

    /**
     * Регистрирует все методы PyListener'а на сервере (каждый метод - отдельное событие)
     * init.py
     *
     * @param listener класс отмеченный декоратором BukkitListener
     */
    public void addPyListener(PyObject listener) {
        PyListener pyListener = (PyListener) listener.__tojava__(PyListener.class);
        this.pyListeners.add(pyListener);
    }

    /**
     * Устанавливает экземпляр PyPlugin'а для данного py-плагина
     * init.py
     *
     * @param pyPlugin класс отмеченный декоратором BukkitPlugin
     */
    public void setPyPlugin(PyObject pyPlugin) {
        this.pyPyPlugin = pyPlugin;
        this.pyPlugin = (PyPlugin) pyPlugin.__tojava__(PyPlugin.class);
    }
}
