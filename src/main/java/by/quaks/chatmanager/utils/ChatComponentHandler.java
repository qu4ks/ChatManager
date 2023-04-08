package by.quaks.chatmanager.utils;

import net.md_5.bungee.api.chat.TextComponent;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChatComponentHandler {
    // Замена плейсхолдеров в сообщении на TextComponents
    public static TextComponent replacePlaceholders(String message, Map<String, TextComponent> placeholders) {
        TextComponent textComponent = new TextComponent();

        // Регулярное выражение для сопоставления заполнителей в сообщении
        Pattern pattern = Pattern.compile("%(.*?)%");
        Matcher matcher = pattern.matcher(message);
        int lastIndex = 0;

        // Переберирание совпадений и замена их TextComponents
        while (matcher.find()) {
            String placeholder = matcher.group(1);
            TextComponent replacement = placeholders.getOrDefault(placeholder, new TextComponent());
            if (matcher.start() > lastIndex) {
                // Добавление любого несоответствующего текста в окончательный TextComponent
                textComponent.addExtra(message.substring(lastIndex, matcher.start()));
            }
            // Добавление замены TextComponent в окончательный TextComponent
            textComponent.addExtra(replacement);
            lastIndex = matcher.end();
        }

        // Добавление любого оставшегося несоответствующнго текста в окончательный TextComponent
        if (lastIndex < message.length()) {
            textComponent.addExtra(message.substring(lastIndex));
        }

        return textComponent;
    }
}
