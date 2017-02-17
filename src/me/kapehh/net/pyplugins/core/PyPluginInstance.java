package me.kapehh.net.pyplugins.core;

import me.kapehh.net.pyplugins.core.python.PyListener;
import me.kapehh.net.pyplugins.core.python.PyPlugin;
import org.bukkit.event.HandlerList;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by karen on 26.09.2016.
 */
public class PyPluginInstance {
    private static HashMap<String, PyObject> settings = new HashMap<>();

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
        for (PyListener pyListener : this.pyListeners) {
            HandlerList.unregisterAll(pyListener);
        }
        this.pyListeners.clear();

        this.removeSetting();

        // Очищаем интерпретатор
        this.interpreter.cleanup();
        this.interpreter.close();
        this.interpreter = null;
    }

    private void removeSetting() {
        String lowerName = this.name.toLowerCase();
        if (PyPluginInstance.settings.containsKey(lowerName)) {
            PyPluginInstance.settings.remove(lowerName);
        }
    }

    /**
     * PYPLUGIN PART
     */

    // Регистрирует все методы PyListener'а на сервере (каждый метод - отдельное событие)
    public void addPyListener(PyObject listener) {
        PyListener pyListener = (PyListener) listener.__tojava__(PyListener.class);
        this.pyListeners.add(pyListener);
    }

    // Устанавливает экземпляр PyPlugin'а для данного py-плагина
    public void setPyPlugin(PyObject pyPlugin) {
        this.pyPyPlugin = pyPlugin;
        this.pyPlugin = (PyPlugin) pyPlugin.__tojava__(PyPlugin.class);
    }

    // Сохраняет настройки плагина
    public void setSetting(PyObject setting) {
        String lowerName = this.name.toLowerCase();
        if (PyPluginInstance.settings.containsKey(lowerName)) {
            PyPluginInstance.settings.remove(lowerName);
        }
        PyPluginInstance.settings.put(lowerName, setting);
    }

    // Получает настройки плагина
    public PyObject getSetting(String pluginName) {
        String lowerName = pluginName.toLowerCase();
        if (PyPluginInstance.settings.containsKey(lowerName)) {
            return PyPluginInstance.settings.get(lowerName);
        } else {
            return null;
        }
    }
}
