package me.kapehh.net.pyplugins.core;

import org.python.core.PyObject;
import org.python.util.PythonInterpreter;

/**
 * Created by karen on 26.09.2016.
 */
public class PyPluginCore {
    private PythonInterpreter interpreter;
    private PyListenerBase bukkitListener;

    public PyListenerBase getBukkitListener() {
        return bukkitListener;
    }

    public void setBukkitListener(PyObject bukkitListener) {
        this.bukkitListener = (PyListenerBase) bukkitListener.__tojava__(PyListenerBase.class);
    }
}
