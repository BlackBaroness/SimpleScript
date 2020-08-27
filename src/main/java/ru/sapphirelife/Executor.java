package ru.sapphirelife;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class Executor extends Thread {

    private final List<String> cmd;
    private final Player p;
    private final String nick;

    public Executor(List<String> cmd, Player p, String nick) {
        this.cmd = cmd;
        this.p = p;
        this.nick = nick;
        start();
    }

    @Override
    public synchronized void start() {
        String var;
        for (String s : cmd) {
            var = "ssSay";
            if (s.contains(var)) {
                p.sendMessage(replace(s, var));
                continue;
            }

            var = "ssWait";
            if (s.contains(var)) {
                try {
                    Thread.sleep(Integer.parseInt(replace(s, var)));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                continue;
            }

            var = "ssLog";
            if (s.contains(var) && !s.contains("ssLog-prefix")) {
                Logger.normal(replace(s, var));
                continue;
            }

            var = "ssLog-prefix";
            if (s.contains(var)) {
                Logger.prefix((replace(s, var)));
                continue;
            }

            var = "ssKill";
            if (s.contains(var)) {
                Player player = Bukkit.getPlayer(replace(s, var));
                if (player == null) {
                    Logger.prefix("Player " + replace(s, var) + " not found.");
                    continue;
                }
                player.setHealth(0);
                continue;
            }

            var = "ssExecute";
            if (s.contains(var)) {
                ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                Bukkit.dispatchCommand(console, replace(s, var));
                continue;
            }

            var = "ssExecute-player";
            if (s.contains(var)) {
                p.performCommand(replace(s, var));
            }
        }
    }

    private String replace(String s, String var) {
        return s.replace(var + " ", "")
                .replace("{ONLINE}", String.valueOf(Bukkit.getOnlinePlayers().size()))
                .replace("{PLAYER}", nick);
    }
}
