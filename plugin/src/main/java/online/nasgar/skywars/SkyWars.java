package online.nasgar.skywars;

import me.yushust.inject.Injector;
import online.nasgar.skywars.module.MainModule;
import online.nasgar.skywars.service.SkyWarsLoader;
import org.bukkit.plugin.java.JavaPlugin;

import javax.inject.Inject;

public class SkyWars extends JavaPlugin {

    @Inject private SkyWarsLoader skyWarsLoader;

    @Override
    public void onEnable() {
        Injector injector = Injector.create(new MainModule(this));
        injector.injectMembers(this);
        skyWarsLoader.load();
    }

    @Override
    public void onDisable() {
        skyWarsLoader.unload();
        //Disabling logic
    }
}
