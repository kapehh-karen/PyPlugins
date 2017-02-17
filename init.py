# encoding: utf-8

from me.kapehh.net.pyplugins.core.python import PyListener as JavaPyListener
from me.kapehh.net.pyplugins.core.python import PyPlugin as JavaPyPlugin
from org.bukkit.event import EventPriority


# Settings init.py

class Settings:
    event_methods = []
    command_methods = []
    listeners = []
    plugin = None
    plugin_dir = unicode(__pyplugin__.getPath())

    @staticmethod
    def get_other_settings(plugin_name):
        return __pyplugin__.getSetting(plugin_name)


# Save settings
__pyplugin__.setSetting(Settings)


class PypException(Exception):
    pass


# Internal classes

class PyListener(JavaPyListener):
    pass


class PyPlugin(JavaPyPlugin):
    pass


# Decorators

def pyp_event_handler(event, priority=EventPriority.NORMAL):
    def first_wrapper(method):
        # Check duplicate methods
        if method.__name__ in Settings.event_methods:
            raise PypException('Event method name "%s" already exists!' % method.__name__)
        else:
            Settings.event_methods.append(method.__name__)

        method._PyEventType = event
        method._PyEventPriority = priority
        return method
    return first_wrapper


def pyp_command_handler(command_name):
    def first_wrapper(method):
        # Check duplicate methods
        if method.__name__ in Settings.command_methods:
            raise PypException('Command method name "%s" already exists!' % method.__name__)
        else:
            Settings.command_methods.append(method.__name__)

        method._PyCommandName = command_name
        return method
    return first_wrapper


def pyp_listener(cls):
    # Check parent class
    if not issubclass(cls, PyListener):
        raise PypException('%s is not subclass PyListener' % cls)

    listener = cls()
    for nameMethod in dir(listener):
        method = getattr(listener, nameMethod)
        if hasattr(method, '_PyEventType') and hasattr(method, '_PyEventPriority'):
            listener.addHandler(method, method._PyEventType, method._PyEventPriority)

    Settings.listeners.append(listener)
    __pyplugin__.addPyListener(listener)
    return cls


def pyp_plugin(cls):
    # Check parent class
    if not issubclass(cls, PyPlugin):
        raise PypException('%s is not subclass PyPlugin' % cls)

    # Only one instance of PyPlugin
    if Settings.plugin is not None:
        raise PypException('PyPlugin already set')

    pyplugin = cls()
    for nameMethod in dir(pyplugin):
        method = getattr(pyplugin, nameMethod)
        if hasattr(method, '_PyCommandName'):
            pyplugin.addCommand(method._PyCommandName, method)

    Settings.plugin = pyplugin
    __pyplugin__.setPyPlugin(pyplugin)
    return cls
