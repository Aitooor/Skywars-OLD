package online.nasgar.skywars.loader;

import me.fixeddev.commandflow.CommandManager;
import me.fixeddev.commandflow.annotated.AnnotatedCommandTreeBuilder;
import me.fixeddev.commandflow.annotated.AnnotatedCommandTreeBuilderImpl;
import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.SubCommandInstanceCreator;
import me.fixeddev.commandflow.annotated.builder.AnnotatedCommandBuilder;
import me.fixeddev.commandflow.annotated.part.PartInjector;
import me.fixeddev.commandflow.annotated.part.SimplePartInjector;
import me.fixeddev.commandflow.annotated.part.defaults.DefaultsModule;
import me.fixeddev.commandflow.bukkit.BukkitCommandManager;
import me.fixeddev.commandflow.bukkit.factory.BukkitModule;
import me.fixeddev.commandflow.translator.DefaultTranslator;
import me.yushust.inject.InjectAll;
import me.yushust.inject.Injector;
import online.nasgar.skywars.SkyWars;
import online.nasgar.skywars.api.loader.Loader;
import online.nasgar.skywars.commands.SWArenaCommand;
import online.nasgar.skywars.commands.SWCommand;
import online.nasgar.skywars.flow.CustomAuthorizer;
import online.nasgar.skywars.flow.CustomBukkitModule;
import online.nasgar.skywars.flow.CustomTranslationProvider;
import online.nasgar.skywars.flow.CustomUsageBuilder;

import javax.inject.Inject;

@InjectAll
public class CommandLoader implements Loader {

    private SkyWars skyWars;
    private SWCommand swCommand;
    private SWArenaCommand swArenaCommand;
    private CustomTranslationProvider customTranslationProvider;
    private CustomAuthorizer customAuthorizer;
    private CustomUsageBuilder customUsageBuilder;
    private Injector injector;

    @Override
    public void load() {
        PartInjector partInjector = new SimplePartInjector();
        partInjector.install(new DefaultsModule());
        partInjector.install(new BukkitModule());
        partInjector.install(new CustomBukkitModule(injector));

        AnnotatedCommandBuilder annotatedCommandBuilder = AnnotatedCommandBuilder.create(partInjector);
        SubCommandInstanceCreator instanceCreator = (clazz, parent) -> injector.getInstance(clazz);

        AnnotatedCommandTreeBuilder annotatedCommandTreeBuilder =
                new AnnotatedCommandTreeBuilderImpl(
                        annotatedCommandBuilder,
                        instanceCreator
                );

        CommandManager commandManager = new BukkitCommandManager(skyWars.getName());
        commandManager.setTranslator(new DefaultTranslator(customTranslationProvider));
        commandManager.setAuthorizer(customAuthorizer);
        commandManager.setUsageBuilder(customUsageBuilder);

        registerCommands(
                annotatedCommandTreeBuilder,
                commandManager,
                swCommand,
                swArenaCommand
        );
    }

    @Override
    public void unload() {

    }

    private void registerCommands(AnnotatedCommandTreeBuilder annotatedCommandTreeBuilder, CommandManager commandManager, CommandClass... commandClasses) {
        for (CommandClass commandClass : commandClasses) {
            commandManager.registerCommands(annotatedCommandTreeBuilder.fromClass(commandClass));
        }
    }
}
