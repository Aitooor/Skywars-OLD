package online.nasgar.skywars.module;

import me.yushust.inject.AbstractModule;
import online.nasgar.skywars.SkyWars;

public class MainModule extends AbstractModule {

    private final SkyWars skyWars;

    public MainModule(SkyWars skyWars){
        this.skyWars = skyWars;
    }

    @Override
    public void configure() {
        
        bind(SkyWars.class).toInstance(skyWars);
    }
}
