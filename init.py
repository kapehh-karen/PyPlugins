# encoding: utf-8

from me.kapehh.net.pyplugins.core.python import PyListener as JavaPyListener
from me.kapehh.net.pyplugins.core.python import PyPlugin as JavaPyPlugin
from org.bukkit.event import EventPriority
from org.bukkit.entity import Player
from org.bukkit.inventory import ItemStack
from org.bukkit.util.io import BukkitObjectInputStream
from org.bukkit.util.io import BukkitObjectOutputStream

from java.io import ByteArrayInputStream
from java.io import ByteArrayOutputStream

import json
import binascii


# Settings init.py

class Settings:
    event_methods = []
    command_methods = []
    plugin = None
    plugin_dir = unicode(__pyplugin__.getPath())


class PypException(Exception):
    pass


# Bukkit JSON

ITEMSTACK_JSON_NAME = "bukkit::itemstack"


class BukkitJSONEncoder(json.JSONEncoder):
    def default(self, obj):
        if not isinstance(obj, ItemStack):
            return None
        byte_array_out = ByteArrayOutputStream()
        bukkit_out = BukkitObjectOutputStream(byte_array_out)
        bukkit_out.writeObject(obj)
        bukkit_out.flush()
        bukkit_out.close()
        raw_bytes = byte_array_out.toByteArray()
        return {ITEMSTACK_JSON_NAME: binascii.hexlify(raw_bytes)}


class BukkitJSONDecoder:
    def from_json(self, json_object):
        if not ITEMSTACK_JSON_NAME in json_object:
            return json_object
        raw_bytes = binascii.unhexlify(json_object[ITEMSTACK_JSON_NAME])
        byte_array_in = ByteArrayInputStream(raw_bytes)
        bukkit_in = BukkitObjectInputStream(byte_array_in)
        bukkit_item = bukkit_in.readObject()
        bukkit_in.close()
        return bukkit_item

    def decode(self, json_string):
        return json.JSONDecoder(object_hook=self.from_json).decode(json_string)


class BukkitJSON:
    encoder = BukkitJSONEncoder()
    decoder = BukkitJSONDecoder()


# Internal classes

class PyListener(JavaPyListener):
    def get_pyplugin(self):
        """
            Return PyPlugin instance
        """
        return Settings.plugin


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
