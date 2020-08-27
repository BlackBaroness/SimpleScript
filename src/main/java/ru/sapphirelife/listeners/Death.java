package ru.sapphirelife.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import ru.sapphirelife.Executor;

import java.util.List;

public class Death implements Listener {

    private final List<String> commands;
    private final JavaPlugin core;

    public Death(List<String> commands, JavaPlugin core) {
        this.commands = commands;
        this.core = core;
    }

    @EventHandler
    private void onDeath(PlayerDeathEvent e) {
        new BukkitRunnable() {
            @Override
            public void run() {
                Player p = e.getEntity();
                String nick = p.getName();
                Executor executor = new Executor(commands, p, nick);
            }
        }.runTask(core);
    }
}
