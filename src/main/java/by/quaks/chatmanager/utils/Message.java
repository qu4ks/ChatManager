package by.quaks.chatmanager.utils;
enum Type {
    LOCAL,
    GLOBAL
}
public class Message {
    private Type type;
    private String message;
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
    public Message(String message){
        if (message.charAt(0)=='!'){ //TODO Переместить в конфиг
            this.setType(Type.GLOBAL);
            message = message.substring(1);
            this.setMessage(message);
        }
    }
}
