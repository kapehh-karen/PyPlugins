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
 * Реализуем интерфейс Listener только для того, чтобы могли привязать к нему все PyEventHandler'ы
 */
public class PyListener implements Listener {
    private final HashMap<Class<? extends Event>, Set<PyEventHandler>> handlers = new HashMap<>();

    /**
     * Регистрирует BukkitEvent на сервере
     * init.py
     *
     * @param handler метод который будет вызываться при срабатывании события
     * @param type тип события
     * @param priority приоритет события
     */
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

        // Добавляем EventHandler в список и регистрируем его на сервере
        set.add(pythonHandler);
        if (PyPluginMain.instance != null)
            PyPluginMain.instance.getServer().getPluginManager().registerEvent(type, this, priority, pythonHandler, PyPluginMain.instance);
    }
}
