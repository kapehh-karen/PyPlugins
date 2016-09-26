package me.kapehh.net.pyplugins;

import org.bukkit.Bukkit;
import org.bukkit.event.*;
import org.bukkit.plugin.EventExecutor;
import org.bukkit.plugin.RegisteredListener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

/**
 * Created by karen on 26.09.2016.
 */
public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("Loading...");
        //getServer().getPluginManager().registerEvents(new ProxyListener(), this);

        /*final ProxyListener pyListener = new ProxyListener();
        ExtendRegisteredListener registeredListener = new ExtendRegisteredListener(pyListener, new EventExecutor() {
            @Override
            public void execute(Listener listener, Event event) throws EventException {
                ((ProxyListener) listener).onEvent(event);
                System.out.println("EXECUTE: " + event.getClass().getName());
                //pyListener.onEvent(event);
            }
        }, EventPriority.NORMAL, this, false *//* ignore cancelled - fast *//*);

        for(HandlerList handler : HandlerList.getHandlerLists()) {
            //System.out.println(handler);
            //System.out.println(Arrays.toString(handler.getRegisteredListeners()));
            //handler.register(registeredListener);
        }*/

        getServer().getPluginManager().registerEvents(new ProxyListener(), this);
    }

    @Override
    public void onDisable() {

    }

}
