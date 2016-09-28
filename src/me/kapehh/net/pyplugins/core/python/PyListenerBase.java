package me.kapehh.net.pyplugins.core.python;

import me.kapehh.net.pyplugins.core.python.PyEventHandler;
import org.bukkit.event.Event;
import org.python.core.PyObject;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by karen on 26.09.2016.
 */
public class PyListenerBase {
    HashMap<Class<? extends Event>, Set<PyEventHandler>> handlers = new HashMap<>();

    /*public void fireEvent(Event e, PyEventHandler handler) {
        handler.getHandler().__call__(Py.java2py(e));
    }*/

    public void addHandler(PyObject handler, Class<? extends Event> type, int priority) {
        // Если это не функция (метод) то выходим
        // Но об этом никому не скажем, хе-хе-хе-хе-хе
        if (!handler.isCallable())
            return;

        Set<PyEventHandler> set = this.handlers.get(type);
        PyEventHandler pythonHandler = new PyEventHandler(handler, type, priority);

        if(set == null) {
            set = new HashSet<>();
            handlers.put(type, set);
        }

        set.add(pythonHandler);
    }
}
