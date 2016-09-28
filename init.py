from me.kapehh.net.pyplugins.core.python import PyListenerBase
from me.kapehh.net.pyplugins.core.python import PyPlugin

def PyEventHandler(event, priority=0):
    def first_wrapper(method):
        def second_wrapper(*args, **kwargs):
            method(*args, **kwargs)
        second_wrapper.__PyEventType = event
        second_wrapper.__PyEventPriority = priority
        return second_wrapper
    return first_wrapper

class PyListener(PyListenerBase):
    def __init__(self, *args, **kwargs):
        for m in dir(self):
            method = getattr(self, m)
            if hasattr(method, '__PyEventType') and hasattr(method, '__PyEventPriority'):
                self.addHandler(method, getattr(method, '__PyEventType'), getattr(method, '__PyEventPriority'))
