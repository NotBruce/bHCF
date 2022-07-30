package me.bruce.bhcf.utils;

import com.google.common.base.Strings;
import org.bukkit.ChatColor;
import org.bukkit.util.ChatPaginator;

public class BukkitUtils {

    // Only here because I CBA for this shit !!!
    public static String STRAIGHT_LINE_TEMPLATE = ChatColor.STRIKETHROUGH.toString() + Strings.repeat("-", 256);
    public static String STRAIGHT_LINE_DEFAULT = STRAIGHT_LINE_TEMPLATE.substring(0, ChatPaginator.GUARANTEED_NO_WRAP_CHAT_PAGE_WIDTH);
}
