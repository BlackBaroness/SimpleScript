package ru.sapphirelife;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class SimpleScript extends JavaPlugin {

    @Override
    public void onEnable() {
        // config check
        File cfg = new File(getDataFolder() + File.separator + "config.yml");
        if (!cfg.exists()) {
            Logger.prefix("Config file not found! Saved default one.");
            saveDefaultConfig();
        }

        Loader loader = new Loader(this);
        getCommand("simplescript").setExecutor(loader);
    }

    /*
     * Methods:
     *  ssSay [msg] - sends message to a player's chat
     *  ssWait [milliseconds] - waits
     *  ssLog [msg] - logs to a console without any prefix
     *  ssLog-prefix [msg] - logs to a console with SimpleScript prefix
     *  ssKill [nickname] - kills a player, by founding him by nickname
     *  ssExecute [command] - runs command as console
     *  ssExecute-player [command] - runs command as player
     *
     * Variables:
     *  {ONLINE} - returns number of online players
     *  {PLAYER} - returns player's nickname
     */
}
