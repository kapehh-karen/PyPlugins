# PyPlugins v4
Bukkit плагин, позволяющий расширять функционал Bukkit-серверов (CraftBukkit, Spigot и т.п.) с использованием Jython (интерпретатор Python версии 2.7)

# Установка
1. Поместить плагин <code>PyPlugins.jar</code> в директорию <code>plugins/</code> которая находится в той же директории где и сервер
2. Создать (если нету) в директории сервера папку <code>lib/</code> и поместить туда файл <code>jython.jar</code>
3. Запустить сервер

Если требуется использовать уже установленный jython интерпретатор в системе (например, для установки на него дополнительных python модулей, которые могут установиться на него), то требуется изменить файл <code>META-INF/MANIFEST.MF</code>, подправив в нем __Class-Path__ на полный путь к jython.jar (Важно! В Windows к полному пути требуется добавить слэш, например: /C:/Jython27/jython.jar) (Важно! Максимальная длина в манифесте MANIFEST.MF равна __72__ символам, включая перенос строки \r\n в windows и \n в linux)

# Структура плагинов PyPlugins
В директории <code>plugins/</code> находится папка <code>PyPlugins/</code> (создается автоматически при первом запуске сервера).
Там будут храниться все ваши python-плагины и модули.

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


@pyp_listener
class MyListener(PyListener):
    pass
    
    
@pyp_plugin
class MyPlugin(PyPlugin):
    pass
```

<code>MyPlugin</code> - объект плагина (имя может быть любым), он должен обязательно наследовать **PyPlugin**! И быть отмеченым декоратором <code>pyp_plugin</code> для регистрации в системе.

<code>MyListener</code> - объект слушателя (имя может быть любым), он должен обязательно наследовать **PyListener**! И быть отмеченым декоратором <code>pyp_listener</code> для регистрации в системе.

# Декораторы PyPlugins

<code>@pyp_plugin</code> - помечает выбранный **класс** (который должен наследоваться от PyPlugin) как основной класс python-плагина.
Данный класс **может реализовать** (а может и не реализовывать) следующие методы: <code>enable</code> и <code>disable</code>. Данные методы вызываются при активации и деактивации python-плагина. Методы полезно использовать, например, для создания и удаления потоков и т.п.

<code>@pyp_command_handler(command_name)</code> - помечает выбранный **метод** как обработчик команды. Методы помеченные данным декоратором должны быть внутри основного класса python-плагина (класс отмеченный декоратором <code>@pyp_plugin</code>). В качестве аргумента декоратор принимает строку с именем команды. Для выполнения данного обработчика событий требуется выполнить команду: <code>/pyc **commandName**</code>

<code>@pyp_listener</code> - помечает выбранный **класс** (который должен наследоваться от PyListener) как слушатель игровых событий.

<code>@pyp_event_handler(event, [priority])</code> - помечает выбранный **метод** как слушатель события <code>event</code> с приоритетом <code>priority</code> (данный аргумент **не обязателен**, по-умолчанию используется приоритет **NORMAL**). Методы помеченные данным декоратором должны быть внутри класса слушателя событий (классы отмеченные декоратором <code>@pyp_listener</code>)

### Применение декораторов на примере

```python
# -*- coding: utf-8 -*-

# Импортируем Java-классы
from org.bukkit.entity import Player
from org.bukkit.event.player import PlayerJoinEvent
from org.bukkit.event.player import PlayerQuitEvent

@pyp_listener
class MyListener(PyListener):

    @pyp_event_handler(PlayerJoinEvent)
    def join(self, event):
        """ Событие вызывается когда игрок входит в игру """
        event.setJoinMessage("")  # Очищаем сообщение (чтобы не выводилось сообщений при входе)

    @pyp_event_handler(PlayerQuitEvent)
    def quit(self, event):
        """ Событие вызывается когда игрок выходит из игры """
        event.setQuitMessage("")


@pyp_plugin
class MyPlugin(PyPlugin):

    @pyp_command_handler("iteminfo")
    def onItemInfo(self, sender, args):
        """ Обработчик команды /pyc iteminfo
            P.S. Имя метода 'onItemInfo', однако это не имеет никакого значения,
                 имя команды передается в декоратор
        """
    
        # Если команду выполняет консоль или она выполняется через rcon, то выводим сообщение: You not player!
        if not isinstance(sender, Player):
            sender.sendMessage("You not player!")
            return
            
        # (Внимание! sender может быть не игроком, поэтому чтобы небыло ошибок мы выше делали проверку)
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

# Настройки плагинов

* Рабочая директория всех python-плагинов: `./plugins/PyPlugins`
* Для получения пути к текущей папке плагина, нужно взять значения из настроек: `Settings.plugin_dir`
* Для получения объекта PyPlugin плагина в файле `main.py`, взять значение из `Settings.plugin`
* Для получения настроек __другого плагина__, необходимо вызывать метод <code>Settings.get_other_settings("PyPluginName")</code> который вернет объект `Settings` другого плагина. Так можно передавать информацию с одного python-плагина в другой.

# Дополнительные примеры

Запрос к БД
```python
from com.ziclix.python.sql import zxJDBC

jdbc_url = r"jdbc:mysql://xx.xx.xx.xx/BDNAME"
username = "BDLOGIN"
password = "BDPASSWORD"
driver = "com.mysql.jdbc.Driver"

# obtain a connection using the with-statment
with zxJDBC.connect(jdbc_url, username, password, driver) as conn:
    with conn.cursor() as c:
        c.execute("select * from table_name limit 1")
        print(c.fetchall())
```

# Известные баги и рекомендации

* Подключение python модулей лучше делать после подключения Java классов. Например:
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

* Иногда, когда сервер работает постоянно 24/7, в JVM может что-то отвалиться и при попытке перезагрузке python скрипта командой <code>pyp reload</code> ваш плагин успешно выгрузится, но обратно *не загрузится*. Начнет ругаться на ошибку связанную с GZIP. Если требуется перезагрузить плагин после длительной работы сервера, советую создать плагин-пустышку, например Test в котором ничего не будет. Затем попробуйте загрузить этот пустой плагин Test командой <code>pyp load Test</code>, если он загрузится успешно, тогда смело перезагружайте свой рабочий плагин.

# Дополнительная информация
* <a href="//github.com/kapehh-karen/PyPlugins-Libs">PyPlugins-Libs - Официальные модули для PyPlugins</a>
* <a href="//github.com/kapehh-karen/PyPlugins-Plugins">PyPlugins-Plugins - Официальные примеры плагинов для PyPlugins</a>
