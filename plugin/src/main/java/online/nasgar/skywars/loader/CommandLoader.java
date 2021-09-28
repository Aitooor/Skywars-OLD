package online.nasgar.skywars.loader;

import me.fixeddev.commandflow.annotated.AnnotatedCommandTreeBuilder;
import me.fixeddev.commandflow.annotated.part.PartInjector;
import me.fixeddev.commandflow.annotated.part.SimplePartInjector;
import me.fixeddev.commandflow.annotated.part.defaults.DefaultsModule;
import me.fixeddev.commandflow.bukkit.factory.BukkitModule;
import me.yushust.inject.InjectAll;
import me.yushust.inject.Injector;
import online.nasgar.skywars.SkyWars;
import online.nasgar.skywars.api.loader.Loader;
import online.nasgar.skywars.commands.SWCommand;
import online.nasgar.skywars.flow.CustomBukkitModule;

import javax.inject.Inject;

@InjectAll
public class CommandLoader implements Loader {

    private SkyWars skyWars;
    private Injector injector;
    private SWCommand swCommand;

    @Override
    public void load() {
        PartInjector partInjector = new SimplePartInjector();
        partInjector.install(new DefaultsModule());
        partInjector.install(new BukkitModule());
        partInjector.install(new CustomBukkitModule(injector));

        AnnotatedCommandTreeBuilder annotatedCommandTreeBuilder = AnnotatedCommandTreeBuilder.create(partInjector);

    }
}
