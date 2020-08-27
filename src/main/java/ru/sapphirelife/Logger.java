package ru.sapphirelife;

import org.bukkit.ChatColor;

public class Logger {

    private static final String prefix = ChatColor.BOLD + "" + ChatColor.AQUA + "[" + ChatColor.DARK_GREEN + "SimpleScript" + ChatColor.AQUA + "] " + ChatColor.RESET + "" + ChatColor.WHITE;

    public static void normal(String msg) {
        System.out.println(msg);
    }

    public static void prefix(String msg) {
        System.out.println(prefix + msg);
    }
}
