package by.quaks.chatmanager;

import by.quaks.chatmanager.listeners.BaseChatHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class ChatManager extends JavaPlugin {
    @Override
    public void onEnable() {
        // Plugin startup logic
        EventRegisterer.registerListeners(new Listener[]{
                new BaseChatHandler()
        },this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
