package by.quaks.chatmanager.listeners;

import by.quaks.chatmanager.utils.Message;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.lang.reflect.InvocationTargetException;

public class BaseChatHandler implements Listener {
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) throws InvocationTargetException {
        event.setCancelled(true);
        Player p = event.getPlayer();
        Message msg = new Message(event.getMessage(),event.getPlayer().getName());
        p.spigot().sendMessage(msg.TextComponent());
//        p.spigot().sendMessage(new TextComponent("Abobik")); //todo
    }
}
