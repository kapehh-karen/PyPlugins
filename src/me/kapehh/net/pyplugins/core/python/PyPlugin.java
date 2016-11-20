package me.kapehh.net.pyplugins.core.python;

import org.bukkit.command.Command;
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

    /**
     * Данный метод перезапишется в py-плагине своей реализацией
     * init.py
     */
    public void enable() {
        /* NotImplemented */
    }

    /**
     * Данный метод перезапишется в py-плагине своей реализацией
     * init.py
     */
    public void disable() {
        /* NotImplemented */
    }

    /**
     * Данный метод вызывается в файле init.py при регистрации команд
     * init.py
     *
     * @param pyCommand команда py-плагина
     * @param handler объект питоновский, который будет вызываться для обработки команды
     */
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

    /**
     * Обработчик команды py-плагина
     *
     * @see me.kapehh.net.pyplugins.core.PyCommandExecutor#onCommand
     * @param sender - игрок или консоль, которая вводит команду
     * @param pyCommand - имя команды (/pyc pyCommand)
     * @param args - аргументы команды (/pyc pyCommand [arg0 arg1 ...])
     * @return возвращает true если программа была обработана плагином
     */
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
