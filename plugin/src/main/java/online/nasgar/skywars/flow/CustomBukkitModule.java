package online.nasgar.skywars.flow;

import me.fixeddev.commandflow.annotated.part.AbstractModule;
import me.yushust.inject.Injector;

public class CustomBukkitModule extends AbstractModule {

    private final Injector injector;

    public CustomBukkitModule(Injector injector){
        this.injector = injector;
    }

    @Override
    public void configure() {

    }
}
