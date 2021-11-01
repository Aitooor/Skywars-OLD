package online.nasgar.skywars.service;

import online.nasgar.skywars.SkyWars;
import online.nasgar.skywars.api.loader.Loader;
import online.nasgar.skywars.loader.CommandLoader;
import online.nasgar.skywars.loader.GameLoader;
import online.nasgar.skywars.loader.ListenerLoader;
import online.nasgar.skywars.loader.UserLoader;
import online.nasgar.skywars.util.Utils;

import javax.inject.Inject;

public class SkyWarsLoader implements Loader {

    @Inject private SkyWars skyWars;
    @Inject private UserLoader userLoader;
    @Inject private GameLoader gameLoader;
    @Inject private CommandLoader commandLoader;
    @Inject private ListenerLoader listenerLoader;

    @Override
    public void load() {
        userLoader.load();
        gameLoader.load();
        commandLoader.load();
        listenerLoader.load();
        Utils.log("&aEnabled");
    }

    @Override
    public void unload() {
        userLoader.unload();
        gameLoader.unload();
        commandLoader.unload();
        listenerLoader.unload();
        Utils.log("&cDisabled");
    }
}
