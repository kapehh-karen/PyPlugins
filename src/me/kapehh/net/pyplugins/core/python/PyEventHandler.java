package me.kapehh.net.pyplugins.core.python;

import org.bukkit.event.Event;
import org.python.core.PyObject;

/**
 * Created by karen on 27.09.2016.
 */
public class PyEventHandler {
    private PyObject handler;
    private Class<? extends Event> type;
    private int priority;

    public PyEventHandler(PyObject handler, Class<? extends Event> type, int priority) {
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

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "PyEventHandler{" +
                "handler=" + handler +
                ", type=" + type +
                ", priority=" + priority +
                '}';
    }
}
