import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class LinkReplacer {
    public static void main(String[] args) {
        String input = "This is a text with multiple links: http://www.example.com, https://www.google.com, and http://stackoverflow.com";
        TextComponent output = replaceLinks(input);
        System.out.println(output.toLegacyText());
    }

    public static TextComponent replaceLinks(String input) {
        // Regular expression to match URLs
        String pattern = "(https?://\\S+)";

        // Split the input string into parts based on the URL pattern
        String[] parts = input.split(pattern, -1);

        // Create a new TextComponent to hold the output
        TextComponent output = new TextComponent("");

        // Loop through the parts, creating TextComponents for links and text
        for (int i = 0; i < parts.length; i++) {
            String part = parts[i];

            if (part.matches(pattern)) {
                // Create a TextComponent for the link
                TextComponent link = new TextComponent("[LINK]");
                link.setColor(ChatColor.GRAY);
                link.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, part));
                link.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TextComponent[] {
                        new TextComponent(part)
                }));

                // Add the link TextComponent to the output
                output.addExtra(link);
            } else {
                // Create a TextComponent for the non-link text
                TextComponent text = new TextComponent(part);

                // Add the text TextComponent to the output
                output.addExtra(text);
            }
        }

        return output;
    }
}