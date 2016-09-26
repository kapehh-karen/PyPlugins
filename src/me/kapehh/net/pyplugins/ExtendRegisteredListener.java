package me.kapehh.net.pyplugins;

import org.bukkit.event.*;
import org.bukkit.plugin.EventExecutor;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredListener;

/**
 * Created by karen on 26.09.2016.
 */
public class ExtendRegisteredListener extends RegisteredListener {
    private EventExecutor executor;
    private Listener listener;

    public ExtendRegisteredListener(Listener listener, EventExecutor executor, EventPriority priority, Plugin plugin, boolean ignoreCancelled) {
        super(listener, executor, priority, plugin, ignoreCancelled);
        this.executor = executor;
        this.listener = listener;
    }

    @Override
    public void callEvent(Event event) throws EventException {
        this.executor.execute(this.listener, event);
    }
}
