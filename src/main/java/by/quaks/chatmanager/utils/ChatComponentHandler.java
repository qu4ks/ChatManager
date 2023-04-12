package by.quaks.chatmanager.utils;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.*;

import java.util.ArrayList;
import java.util.List;
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
    public static TextComponent replaceLinks(String text) {
        String pattern = "(?i)\\b(?:(?:https?|ftp)://)(?:\\S+(?::\\S*)?@)?(?:(?!(?:10|127)(?:\\.\\d{1,3}){3})(?!(?:169\\.254|192\\.168)(?:\\.\\d{1,3}){2})(?!172\\.(?:1[6-9]|2\\d|3[0-1])(?:\\.\\d{1,3}){2})(?:[1-9]\\d?|1\\d\\d|2[01]\\d|22[0-3])(?:\\.(?:1?\\d{1,2}|2[0-4]\\d|25[0-5])){2}(?:\\.(?:[1-9]\\d?|1\\d\\d|2[0-4]\\d|25[0-4]))|(?:(?:[a-z\\u00a1-\\uffff0-9]-*)*[a-z\\u00a1-\\uffff0-9]+)(?:\\.(?:[a-z\\u00a1-\\uffff0-9]-*)*[a-z\\u00a1-\\uffff0-9]+)*(?:\\.(?:[a-z\\u00a1-\\uffff]{2,}))\\.?)(?::\\d{2,5})?(?:[/?#]\\S*)?\\b";
        Pattern urlPattern = Pattern.compile(pattern);
        //TODO: Блять нахуй сделать так чтобы нахуй можно было блять сука нахуй писать слова перед ссылкой и нормально оно её дедектило без выебонов
        TextComponent tc = new TextComponent("");
        Matcher matcher = urlPattern.matcher(text);
        int lastIndex = 0;
        while (matcher.find()) {
            String url = matcher.group();
            TextComponent link = new TextComponent("");
            if (url.endsWith(".gif")) {
                link.setText("[GIF]");//TODO: MTC
            } else if (url.endsWith(".mp4") || url.endsWith(".avi") || url.endsWith(".mov")) {
                link.setText("[VIDEO]");//TODO: MTC
            } else if (url.endsWith(".png") || url.endsWith(".jpg") || url.endsWith(".jpeg")) {
                link.setText("[IMAGE]");//TODO: MTC
            } else {
                link.setText("[LINK]");
            }
            link.setColor(ChatColor.AQUA); //TODO: MTC
            link.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(url).create()));
            link.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, url));
            String preLinkText = text.substring(lastIndex, matcher.start());
            tc.addExtra(preLinkText);
            tc.addExtra(link);
            lastIndex = matcher.end();
        }
        String postLinkText = text.substring(lastIndex, text.length());
        tc.addExtra(postLinkText);

        return tc;
    }
}
