package me.kapehh.net.pyplugins;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by karen on 10.02.2017.
 */
class PyPluginThreadManager {
    static Thread getThreadByName(String threadName) {
        for (Thread thread : getRunningJythonThreads()) {
            if (thread.getName().equalsIgnoreCase(threadName))
                return thread;
        }
        return null;
    }

    static List<Thread> getRunningJythonThreads() {
        return getRunningJythonThreads("jython-threads");
    }

    private static List<Thread> getRunningJythonThreads(String groupName) {
        List<Thread> threads = new ArrayList<>();
        ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();
        ThreadGroup parent;
        while ((parent = threadGroup.getParent()) != null) {
            threadGroup = parent;
            Thread[] threadList = new Thread[threadGroup.activeCount()];
            threadGroup.enumerate(threadList);
            for (Thread thread : threadList) {
                if (thread.getThreadGroup().getName().equalsIgnoreCase(groupName)) {
                    threads.add(thread);
                }
            }
        }
        return threads;
    }
}
