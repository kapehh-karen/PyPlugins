from me.kapehh.net.pyplugins.core.python import PyListener
from me.kapehh.net.pyplugins.core.python import PyPlugin
from org.bukkit.event import EventPriority


__exists_events_names__ = []
__exists_commands_names__ = []


def PyEventHandler(event, priority=EventPriority.NORMAL):
    def first_wrapper(method):
        if method.__name__ in __exists_events_names__:
            raise Exception('Event method name "%s" already exists!' % method.__name__)
        else:
            __exists_events_names__.append(method.__name__)

        def second_wrapper(*args, **kwargs):
            method(*args, **kwargs)
        second_wrapper.__PyEventType = event
        second_wrapper.__PyEventPriority = priority
        return second_wrapper
    return first_wrapper


def PyCommandHandler(cmdName):
    def first_wrapper(method):
        if method.__name__ in __exists_commands_names__:
            raise Exception('Command method name "%s" already exists!' % method.__name__)
        else:
            __exists_commands_names__.append(method.__name__)

        def second_wrapper(*args, **kwargs):
            method(*args, **kwargs)
        second_wrapper.__PyCommandName = cmdName
        return second_wrapper
    return first_wrapper


def BukkitListener(cls):
    if not issubclass(cls, PyListener):
        raise Exception('%s is not subclass PyListener' % cls)

    listener = cls()
    for nameMethod in dir(listener):
        method = getattr(listener, nameMethod)
        if hasattr(method, '__PyEventType') and hasattr(method, '__PyEventPriority'):
            listener.addHandler(method, getattr(method, '__PyEventType'), getattr(method, '__PyEventPriority'))

    __pyplugin__.addPyListener(listener)
    return cls


def BukkitPlugin(cls):
    if not issubclass(cls, PyPlugin):
        raise Exception('%s is not subclass PyPlugin' % cls)

    if __pyplugin__.getPyPlugin() is not None:
        raise Exception('PyPlugin already set')

    pyplugin = cls()
    for nameMethod in dir(pyplugin):
        method = getattr(pyplugin, nameMethod)
        if hasattr(method, '__PyCommandName'):
            pyplugin.addCommand(getattr(method, '__PyCommandName'), method)

    __pyplugin__.setPyPlugin(pyplugin)
    return cls
