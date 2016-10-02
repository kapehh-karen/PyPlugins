from me.kapehh.net.pyplugins.core.python import PyListener
from me.kapehh.net.pyplugins.core.python import PyPlugin
from org.bukkit.event import EventPriority

def PyEventHandler(event, priority=EventPriority.NORMAL):
    def first_wrapper(method):
        def second_wrapper(*args, **kwargs):
            method(*args, **kwargs)
        second_wrapper.__PyEventType = event
        second_wrapper.__PyEventPriority = priority
        return second_wrapper
    return first_wrapper

def BukkitListener(cls):
    listener = cls()
    for nameMethod in dir(listener):
        method = getattr(listener, nameMethod)
        if hasattr(method, '__PyEventType') and hasattr(method, '__PyEventPriority'):
            listener.addHandler(method, getattr(method, '__PyEventType'), getattr(method, '__PyEventPriority'))
    __pyplugin__.addPyListener(listener)
    return cls

def BukkitPlugin(cls):
    if __pyplugin__.getPyPlugin() is not None:
        raise Exception('PyPlugin already set')
    else:
        __pyplugin__.setPyPlugin(cls())
    return cls
