package by.quaks.chatmanager;

import by.quaks.chatmanager.files.Config;
import by.quaks.chatmanager.files.MainConfig;
import by.quaks.chatmanager.listeners.BaseChatHandler;
import by.quaks.chatmanager.listeners.DiscordSrvListener;
import by.quaks.chatmanager.utils.EventRegisterer;
import github.scarsz.discordsrv.DiscordSRV;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public final class ChatManager extends JavaPlugin {
    private void initConfigs(Config[] a){ // Метод регистрации файлов
        for (Config obj : a){
            Class<?> clazz = obj.getClass();
            try {
                Method method = clazz.getDeclaredMethod("setup");
                method.invoke(null);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }
    private DiscordSrvListener discordsrvListener = new DiscordSrvListener(this);
    @Override
    public void onEnable() {
        DiscordSRV.api.subscribe(discordsrvListener);
        if(!this.getDataFolder().exists()) { // Создание папки для хранения конфигурационных файлов
            try {
                this.getDataFolder().mkdir();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // Plugin startup logic
        EventRegisterer.registerListeners(new Listener[]{
                new BaseChatHandler()
        },this);
        initConfigs(new Config[]{
                new MainConfig()
        });
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        DiscordSRV.api.unsubscribe(discordsrvListener);
    }
}
