package online.nasgar.skywars.loader;

import online.nasgar.skywars.SkyWars;
import online.nasgar.skywars.api.loader.Loader;
import online.nasgar.skywars.listeners.PlayerLoginListener;
import online.nasgar.skywars.listeners.PlayerQuitListener;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

import javax.inject.Inject;

public class ListenerLoader implements Loader {

    @Inject private SkyWars skyWars;
    @Inject private PlayerLoginListener playerLoginListener;
    @Inject private PlayerQuitListener playerQuitListener;

    @Override
    public void load() {
        registerListeners(
                playerLoginListener,
                playerQuitListener
        );
    }

    @Override
    public void unload() {

    }

    private void registerListeners(Listener... listeners){
        for(Listener listener : listeners) {
            Bukkit.getPluginManager().registerEvents(listener, skyWars);
        }
    }
}
