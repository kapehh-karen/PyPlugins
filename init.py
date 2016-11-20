# encoding: utf-8

from me.kapehh.net.pyplugins.core.python import PyListener as JavaPyListener
from me.kapehh.net.pyplugins.core.python import PyPlugin as JavaPyPlugin
from org.bukkit.event import EventPriority


# Settings init.py
__internal_data__ = {
    "event_methods": [],
    "command_methods": [],
    "pyplugin": None
}


class PyListener(JavaPyListener):

    def get_pyplugin(self):
        """
            Return PyPlugin instance
        """

        return __internal_data__["pyplugin"]


class PyPlugin(JavaPyPlugin):
    pass


def PyEventHandler(event, priority=EventPriority.NORMAL):

    def first_wrapper(method):

        # Check duplicate methods
        if method.__name__ in __internal_data__["event_methods"]:
            raise Exception('Event method name "%s" already exists!' % method.__name__)
        else:
            __internal_data__["event_methods"].append(method.__name__)

        def second_wrapper(*args, **kwargs):
            method(*args, **kwargs)

        second_wrapper.__PyEventType = event
        second_wrapper.__PyEventPriority = priority

        return second_wrapper

    return first_wrapper


def PyCommandHandler(cmdName):

    def first_wrapper(method):

        # Check duplicate methods
        if method.__name__ in __internal_data__["command_methods"]:
            raise Exception('Command method name "%s" already exists!' % method.__name__)
        else:
            __internal_data__["command_methods"].append(method.__name__)

        def second_wrapper(*args, **kwargs):
            method(*args, **kwargs)

        second_wrapper.__PyCommandName = cmdName

        return second_wrapper

    return first_wrapper


def BukkitListener(cls):

    # Check parent class
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

    # Check parent class
    if not issubclass(cls, PyPlugin):
        raise Exception('%s is not subclass PyPlugin' % cls)

    # Only one instance of PyPlugin
    if __internal_data__["pyplugin"] is not None:
        raise Exception('PyPlugin already set')

    pyplugin = cls()
    for nameMethod in dir(pyplugin):
        method = getattr(pyplugin, nameMethod)
        if hasattr(method, '__PyCommandName'):
            pyplugin.addCommand(getattr(method, '__PyCommandName'), method)

    __internal_data__["pyplugin"] = pyplugin
    __pyplugin__.setPyPlugin(pyplugin)

    return cls
