package by.quaks.chatmanager.listeners;

import by.quaks.chatmanager.files.MainConfig;
import by.quaks.chatmanager.utils.Message;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;


public class BaseChatHandler implements Listener {
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        event.setCancelled(true);
        Player p = event.getPlayer();
        Message msg = new Message(event.getMessage(),event.getPlayer().getName());
        switch (msg.getType()){
            case GLOBAL:{
                Bukkit.getServer().spigot().broadcast(msg.TextComponent());
                break;
            }
            case LOCAL:{
                for (Player player : Bukkit.getOnlinePlayers()){
                    if (player.getLocation().distance(p.getLocation()) <= MainConfig.get().getInt("GlobalChatRadius")){
                        player.spigot().sendMessage(msg.TextComponent());
                    }
                }
                break;
            }
        }
    }
}
