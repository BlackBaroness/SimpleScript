package ru.sapphirelife;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import ru.sapphirelife.listeners.Death;
import ru.sapphirelife.listeners.Join;
import ru.sapphirelife.listeners.Leave;

import java.util.ArrayList;
import java.util.List;

public class Loader implements CommandExecutor {

    private final JavaPlugin core;
    private final List<Listener> listeners = new ArrayList<>();

    public Loader(JavaPlugin core) {
        this.core = core;
        loadScripts();
    }

    private void loadScripts() {
        Logger.prefix("Loading scripts...");
        core.reloadConfig();
        unhook();
        listeners.clear();
        FileConfiguration cfg = core.getConfig();
        List<String> cmds;
        int i = 0;

        cmds = cfg.getStringList("onJoin");
        if (!cmds.toString().equals("[]")) {
            Join join = new Join(cmds, core);
            Bukkit.getPluginManager().registerEvents(join, core);
            listeners.add(join);
            i++;
        }

        cmds = cfg.getStringList("onLeave");
        if (!cmds.toString().equals("[]")) {
            Leave leave = new Leave(cmds, core);
            Bukkit.getPluginManager().registerEvents(leave, core);
            listeners.add(leave);
            i++;
        }

        cmds = cfg.getStringList("onDeath");
        if (!cmds.toString().equals("[]")) {
            Death death = new Death(cmds, core);
            Bukkit.getPluginManager().registerEvents(death, core);
            listeners.add(death);
            i++;
        }

        Logger.prefix(i + " listeners active.");


    }

    private void unhook() {
        for (Listener listener : listeners) {
            HandlerList.unregisterAll(listener);
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            if (!sender.hasPermission("ss.reload")) return true;
            sender.sendMessage(ChatColor.BOLD + "" + ChatColor.AQUA + "[" + ChatColor.YELLOW + "SimpleScript" + ChatColor.AQUA + "] " + ChatColor.RESET + "Reloaded.");
        }
        loadScripts();
        return true;
    }
}
