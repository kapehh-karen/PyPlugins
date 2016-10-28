package me.kapehh.net.pyplugins.core.python;

import org.bukkit.command.CommandSender;
import org.python.core.Py;
import org.python.core.PyObject;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by karen on 28.09.2016.
 */
public class PyPlugin {
    private final HashMap<String, Set<PyObject>> handlers = new HashMap<>();

    public void onEnable() {
        /* NotImplemented */
    }

    public void onDisable() {
        /* NotImplemented */
    }

    public final void addCommand(String pyCommand, PyObject handler) {
        // Если это не функция (метод) то выходим
        if (!handler.isCallable())
            return;

        Set<PyObject> set = this.handlers.get(pyCommand);

        if(set == null) {
            set = new HashSet<>();
            this.handlers.put(pyCommand, set);
        }

        // Добавляем Handler в список
        set.add(handler);
    }

    public final boolean executeCommand(CommandSender sender, String pyCommand, String[] args) {
        Set<PyObject> set = this.handlers.get(pyCommand);
        if ((set == null) || (set.size() < 1))
            return false;

        for (PyObject handler : set) {
            // Вызываем метод
            try {
                handler.__call__(Py.java2py(sender), Py.java2py(args));
            } catch (Throwable ex) {
                ex.printStackTrace();
            }
        }

        return true;
    }
}
