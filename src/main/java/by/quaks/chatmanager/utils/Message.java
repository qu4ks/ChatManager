package by.quaks.chatmanager.utils;

import net.md_5.bungee.api.ChatColor;

import net.md_5.bungee.api.chat.TextComponent;

import java.util.HashMap;
import java.util.Map;

enum Type {
    LOCAL,
    GLOBAL
}
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
        if (message.charAt(0)=='!'){ //TODO: MTC
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
        componentPlayerName.setColor(ChatColor.RED); //TODO: MTC
        placeholders.put("player-name", componentPlayerName);
        componentMessage = new TextComponent(ChatComponentHandler.replaceLinks(message));
        componentMessage.setColor(ChatColor.GREEN); //TODO: MTC
        placeholders.put("message", componentMessage);
        componentType = new TextComponent(type.name());
        placeholders.put("chat-type", componentType); //TODO: MTC
        return ChatComponentHandler.replacePlaceholders("%chat-type% • %player-name% | %message%",placeholders); //TODO: MTC
    }
}
