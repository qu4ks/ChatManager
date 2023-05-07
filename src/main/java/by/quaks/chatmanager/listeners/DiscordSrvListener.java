package by.quaks.chatmanager.listeners;

import github.scarsz.discordsrv.DiscordSRV;
import github.scarsz.discordsrv.api.Subscribe;
import github.scarsz.discordsrv.api.events.DiscordGuildMessagePostProcessEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class DiscordSrvListener {
    private final Plugin plugin;

    public DiscordSrvListener(Plugin plugin) {
        this.plugin = plugin;
    }
    @Subscribe
    public void discordGlobalMessageProcessed(DiscordGuildMessagePostProcessEvent event){
        event.setCancelled(true);
        if(event.getChannel().getId().equals(DiscordSRV.getPlugin().getOptionalTextChannel("global").getId())){
            Bukkit.getServer().spigot().broadcast(new TextComponent("aboba"));
        }
    }
}
