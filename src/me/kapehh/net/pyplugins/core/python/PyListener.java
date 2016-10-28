package me.kapehh.net.pyplugins.core.python;

import me.kapehh.net.pyplugins.PyPluginMain;
import org.bukkit.event.Event;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.python.core.PyObject;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by karen on 26.09.2016.
 */
public class PyListener implements Listener {
    private final HashMap<Class<? extends Event>, Set<PyEventHandler>> handlers = new HashMap<>();
    private PyPlugin pyPlugin = null;

    public PyPlugin getPyPlugin() {
        return pyPlugin;
    }

    public void setPyPlugin(PyPlugin pyPlugin) {
        this.pyPlugin = pyPlugin;
    }

    public final void addHandler(PyObject handler, Class<? extends Event> type, EventPriority priority) {
        // Если это не функция (метод) то выходим
        // Но об этом никому не скажем, хе-хе-хе-хе-хе
        if (!handler.isCallable())
            return;

        Set<PyEventHandler> set = this.handlers.get(type);
        PyEventHandler pythonHandler = new PyEventHandler(handler, type, priority);

        if(set == null) {
            set = new HashSet<>();
            this.handlers.put(type, set);
        }

        // Добавляем EventHandler в список и регистрируем его
        set.add(pythonHandler);
        if (PyPluginMain.instance != null)
            PyPluginMain.instance.getServer().getPluginManager().registerEvent(type, this, priority, pythonHandler, PyPluginMain.instance);
    }
}
