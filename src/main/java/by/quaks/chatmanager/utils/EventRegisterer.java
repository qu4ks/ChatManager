package by.quaks.chatmanager.utils;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

public class EventRegisterer {
    public static void registerListeners(Listener[] a, Plugin plugin){ // Метод регистрации листенеров
        for (Listener elem : a) {
            Bukkit.getServer().getPluginManager().registerEvents(elem, plugin);
        }
    }
}
