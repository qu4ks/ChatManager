package by.quaks.chatmanager.utils;

import by.quaks.chatmanager.files.MainConfig;
import net.md_5.bungee.api.ChatColor;

import net.md_5.bungee.api.chat.TextComponent;

import java.util.HashMap;
import java.util.Map;

public class Message {
    private TextComponent componentPlayerName;
    private TextComponent componentMessage;
    private TextComponent componentType;
    private String playerName;
    private Type type;
    private String message;

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Type getType() {
        return type;
    }
    public void setType(Type type) {
        this.type = type;
    }
    public Message(String message, String playerName){
        this.setPlayerName(playerName);
        if (message.charAt(0)== MainConfig.get().getString("GlobalChatPrefix").charAt(0)){ //TODO: MTC
            this.setType(Type.GLOBAL);
            this.setMessage(message.substring(1).trim());
        }else{
            this.setType(Type.LOCAL);
            this.setMessage(message);
        }
    }
    public TextComponent TextComponent(){
        Map<String, TextComponent> placeholders = new HashMap<>();
        componentPlayerName = new TextComponent(playerName);
        //componentPlayerName.setColor(ChatColor.RED);
        componentPlayerName.setColor(ChatColor.of(MainConfig.get().getString("Player.Color")));
        placeholders.put("player-name", componentPlayerName);
        componentMessage = new TextComponent(ChatComponentHandler.replaceLinks(message));
        componentMessage.setColor(ChatColor.of(MainConfig.get().getString("Message.Color")));
        //componentMessage.setColor(ChatColor.GREEN);
        placeholders.put("message", componentMessage);
        componentType = new TextComponent();
        switch (type){
            case GLOBAL:{
                componentType.setText(MainConfig.get().getString("Global.Label"));
                componentType.setColor(ChatColor.of(MainConfig.get().getString("Global.Color")));
                break;
            }
            case LOCAL:{
                componentType.setText(MainConfig.get().getString("Local.Label"));
                componentType.setColor(ChatColor.of(MainConfig.get().getString("Local.Color")));
                break;
            }
        }
        placeholders.put("chat-type", componentType);
        return ChatComponentHandler.replacePlaceholders(MainConfig.get().getString("Message.Form"),placeholders);
    }
}
