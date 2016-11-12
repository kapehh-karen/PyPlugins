# PyPlugins
Bukkit плагин, позволяющий расширять функционал Bukkit-серверов (CraftBukkit, Spigot и т.п.) с использованием Jython (интерпретатор Python версии 2.7)

# Установка
1. Поместить плагин <code>PyPlugins.jar</code> в директорию <code>plugins/</code> которая находится в той же директории где и сервер
2. Создать (если нету) в директории сервера папку <code>lib/</code> и поместить туда файл <code>jython.jar</code>
3. Запустить сервер

# Структура плагинов PyPlugins
В директории <code>plugins/</code> находится папка <code>PyPlugins/</code> (если нету, можно создать самому).
Там будут храниться все ваши python-плагины.

Структура:
<pre>
./plugins/
    PyPlugins/
        [pypluginname]/
            main.py
</pre>
<code>pypluginname</code> - название python-плагина, <code>main.py</code> - главный файл плагина

# Описание файла main.py
После того, как была создана директория <code>[pypluginname]</code> (имя вашего python-плагина) в <code>./plugins/PyPlugins</code>, необходимо в созданной директории создать файл <code>main.py</code> и описать в нем логику.

В <code>main.py</code> требуется создать объект PyPlugin, который будет использоваться для инициализации python-плагина (данный объект должен быть в единичном экземпляре). И нужное количество PyListener'ов для обработки игровых событий (данный объект не ограничен по количеству).

Пример описания пустого файла <code>main.py</code>:
```python
# -*- coding: utf-8 -*-

@BukkitListener
class MyListener(PyListener):
    pass
    
@BukkitPlugin
class MyPlugin(PyPlugin):
    pass
```

<code>MyPlugin</code> - объект плагина (имя может быть любым), он должен обязательно наследовать <code>PyPlugin</code>! И быть отмеченым декоратором <code>BukkitPlugin</code> для регистрации в системе.

<code>MyListener</code> - объект слушателя (имя может быть любым), он должен обязательно наследовать <code>PyListener</code>! И быть отмеченым декоратором <code>BukkitListener</code> для регистрации в системе.

# Декораторы PyPlugins

<code>@BukkitPlugin</code> - помечает выбранный **класс** (который должен наследовать класс PyPlugin, а название класса может быть любым) как основной класс python-плагина.
Данный класс **может реализовать** (а может и не реализовывать) следующие методы: <code>onEnable</code> и <code>onDisable</code>. Данные методы вызываются при активации и деактивации python-плагина. Методы полезно использовать, например, для создания и удаления потоков и т.п.

<code>@PyCommandHandler(commandName)</code> - помечает выбранный **метод** (название метода может быть любым) как обработчик команды. Методы помеченные данным декоратором должны быть внутри основного класса python-плагина. В качестве аргумента декоратор принимает строку с именем команды. Для выполнения данного обработчика событий требуется выполнить команду: <code>/pyc **commandName**</code>

<code>@BukkitListener</code> - помечает выбранный **класс** (который должен наследовать класс PyListener, а название класса может быть любым) как слушатель игровых событий. Имеет внутренний метод <code>getPyPlugin()</code> возвращающий объект класса python-плагина (отмеченный декоратором <code>@BukkitPlugin</code>)

<code>@PyEventHandler(event, [priority])</code> - помечает выбранный **метод** (название метода может быть любым) как слушатель события <code>event</code> с приоритетом <code>priority</code> (данный аргумент **не обязателен**, по умолчанию используется приоритет **NORMAL**). Методы помеченные данным декоратором должны быть внутри класса слушателя событий (классы отмеченные декоратором <code>@BukkitListener</code>)

### Применение декораторов на примере

```python
# -*- coding: utf-8 -*-

# Импортируем Java-классы
from org.bukkit.entity import Player
from org.bukkit.event.player import PlayerJoinEvent
from org.bukkit.event.player import PlayerQuitEvent

@BukkitListener
class Listnr(PyListener):


    @PyEventHandler(PlayerJoinEvent)
    def join(self, event):
        """ Событие вызывается когда игрок входит в игру
            P.S. Имя метода 'join', однако это не имеет никакого значения
        """
        event.setJoinMessage("") # Очищаем сообщение (чтобы не выводилось сообщений при входе)


    @PyEventHandler(PlayerQuitEvent)
    def quit(self, event):
        """ Событие вызывается когда игрок выходит из игры """
        event.setQuitMessage("")


@BukkitPlugin
class Plug(PyPlugin):


    @PyCommandHandler("iteminfo")
    def onItemInfo(self, sender, args):
        """ Обработчик команды /pyc iteminfo
            P.S. Имя метода 'onItemInfo', однако это не имеет никакого значения,
                 имя команды передается в декоратор
        """
    
        # Если команду выполняет консоль или через rcon, то выводим им сообщение: You not player!
        if not isinstance(sender, Player):
            sender.sendMessage("You not player!")
            return
            
        # (Внимание! sender может быть не игроком, по этому чтобы небыло ошибок мы перед этим делаем проверку)
        item = sender.getItemInHand() # Берем предмет из рук игрока
        sender.sendMessage("Item: %s" % item) # Выводим игроку предмет в его руках
```

Данный плагин уберет вывод сообщений о входах и выходах игроков в чат. И добавит команду <code>/pyc iteminfo</code> которая в чат выведет текстовое представление предмета в руках.

# Команды PyPlugins

* <code>/pyp</code> - выводит список загруженных python-плагинов.
* <code>/pyp load **pypluginname**</code> - загружает плагин
* <code>/pyp reload **pypluginname**</code> - перезагружает плагин
* <code>/pyp unload **pypluginname**</code> - выгружает плагин
* <code>/pyp thread-list</code> - выводит список потоков которые были запущенны в python-скриптах
* <code>/pyp thread-stop **threadName**</code> - завершает поток по его имени
* <code>/pyc **commandName**</code> - выполняет команду **commandName**

# Рекомендации

Подключение python модулей лучше делать после подключения Java классов. Например:
```python
# -*- coding: utf-8 -*-

from org.bukkit.entity import Player
from org.bukkit.event.player import PlayerJoinEvent
from org.bukkit.event.player import PlayerQuitEvent
from org.bukkit.event.player import PlayerCommandPreprocessEvent
from org.bukkit.event.entity import PlayerDeathEvent
from org.bukkit import ChatColor
from org.bukkit import Bukkit
from me.kapehh.main.pluginmanager.vault import PluginVault

import re


# Code
```
