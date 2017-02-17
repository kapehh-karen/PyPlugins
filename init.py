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
import os


# Settings init.py


__internal_data__ = {
    "event_methods": [],
    "command_methods": [],
    "pyplugin": None,
}


def get_py_cwd():
    return unicode(__pyplugin__.getPath())


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
        return __internal_data__["pyplugin"]


class PyPlugin(JavaPyPlugin):
    pass


# Decorators


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
