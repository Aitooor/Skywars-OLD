package online.nasgar.skywars.module;

import com.google.gson.GsonBuilder;
import me.yushust.inject.AbstractModule;
import me.yushust.inject.key.TypeReference;
import me.yushust.message.MessageHandler;
import online.nasgar.skywars.SkyWars;
import online.nasgar.skywars.api.cache.Cache;
import online.nasgar.skywars.api.creator.ObjectCreatorHandler;
import online.nasgar.skywars.api.finder.ObjectFinderHandler;
import online.nasgar.skywars.api.game.Game;
import online.nasgar.skywars.api.repository.Repository;
import online.nasgar.skywars.api.user.User;
import online.nasgar.skywars.cache.LocalCache;
import online.nasgar.skywars.creator.game.GameCreatorHandler;
import online.nasgar.skywars.creator.provider.ObjectCreatorHandlerProvider;
import online.nasgar.skywars.database.provider.RepositoryProvider;
import online.nasgar.skywars.file.FileHandler;
import online.nasgar.skywars.file.FileManager;
import online.nasgar.skywars.finder.game.GameFinderHandler;
import online.nasgar.skywars.finder.provider.ObjectFinderHandlerProvider;
import online.nasgar.skywars.message.provider.MessageHandlerProvider;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainModule extends AbstractModule {

    private final SkyWars skyWars;

    public MainModule(SkyWars skyWars){
        this.skyWars = skyWars;
    }

    @Override
    public void configure() {
        FileManager fileManager = new FileManager()
                .create(
                        "config",
                        new FileHandler(skyWars,
                                "config",
                                ".yml")
                );

        install(
                fileManager.build()
        );

        bind(TypeReference.of(Cache.class, Game.class))
                .to(TypeReference.of(LocalCache.class, Game.class)).singleton();
        bind(TypeReference.of(Repository.class, Game.class))
                .toProvider(TypeReference.of(RepositoryProvider.class, Game.class));


        bind(TypeReference.of(Cache.class, User.class))
                .to(TypeReference.of(LocalCache.class, User.class)).singleton();
        bind(TypeReference.of(Repository.class, User.class))
                .toProvider(TypeReference.of(RepositoryProvider.class, User.class));



        bind(TypeReference.of(ObjectCreatorHandler.class, Game.class))
                .toProvider(ObjectCreatorHandlerProvider.class).singleton();
        bind(TypeReference.of(ObjectFinderHandler.class, Game.class))
                .toProvider(ObjectFinderHandlerProvider.class).singleton();


        bind(MessageHandler.class)
                .toProvider(MessageHandlerProvider.class).singleton();
        bind(GsonBuilder.class).toInstance(
                new GsonBuilder()
                        .setPrettyPrinting()
                        .excludeFieldsWithoutExposeAnnotation()
        );
        bind(ExecutorService.class)
                .toInstance(Executors.newSingleThreadExecutor());
        bind(SkyWars.class).toInstance(skyWars);
    }
}
