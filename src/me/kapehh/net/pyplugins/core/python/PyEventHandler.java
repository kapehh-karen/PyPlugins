package me.kapehh.net.pyplugins.core.python;

import org.bukkit.event.Event;
import org.bukkit.event.EventException;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.plugin.EventExecutor;
import org.python.core.Py;
import org.python.core.PyObject;

/**
 * Реализуем интерфейс EventExecutor и вызываем метод из питона для обработки события
 */
public class PyEventHandler implements EventExecutor {
    private PyObject handler;
    private Class<? extends Event> type;
    private EventPriority priority;

    public PyEventHandler(PyObject handler, Class<? extends Event> type, EventPriority priority) {
        this.handler = handler;
        this.type = type;
        this.priority = priority;
    }

    public PyObject getHandler() {
        return handler;
    }

    public void setHandler(PyObject handler) {
        this.handler = handler;
    }

    public Class<? extends Event> getType() {
        return type;
    }

    public void setType(Class<? extends Event> type) {
        this.type = type;
    }

    public EventPriority getPriority() {
        return priority;
    }

    public void setPriority(EventPriority priority) {
        this.priority = priority;
    }

    @Override
    public void execute(Listener listener, Event event) throws EventException {
        // По какой-то причине при прослушивании PlayerDeathEvent вызывался EntityDeathEvent
        // такого быть не должно, по этому добавляем дополнительную проверку :)
        if (!type.isInstance(event))
            return;

        // Вызываем событие и передаем в аргументы event
        try {
            this.handler.__call__(Py.java2py(event));
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
    }
}
