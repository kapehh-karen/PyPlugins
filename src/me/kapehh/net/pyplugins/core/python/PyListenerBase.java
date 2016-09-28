package me.kapehh.net.pyplugins.core.python;

import org.bukkit.event.Event;
import org.python.core.Py;
import org.python.core.PyObject;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by karen on 26.09.2016.
 */
public class PyListenerBase {
    private HashMap<Class<? extends Event>, Set<PyEventHandler>> handlers = new HashMap<>();

    public void fireEvent(Event e) {
        Set<PyEventHandler> set = this.handlers.get(e.getClass());
        if (set == null) return;

        // Для каждого обработчика текущего события, вызываем его
        for (PyEventHandler handler : set) {
            try {
                handler.getHandler().__call__(Py.java2py(e));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

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
