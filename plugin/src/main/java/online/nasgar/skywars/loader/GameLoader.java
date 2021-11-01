package online.nasgar.skywars.loader;

import online.nasgar.skywars.api.cache.Cache;
import online.nasgar.skywars.api.game.Game;
import online.nasgar.skywars.api.loader.Loader;
import online.nasgar.skywars.api.repository.Repository;

import javax.inject.Inject;

public class GameLoader implements Loader {

    @Inject private Cache<Game> gameCache;
    @Inject private Repository<Game> gameRepository;

    @Override
    public void load() {
        gameRepository.findAllSync().forEach(
                game -> {
                    game.setDisabled(false);
                    gameCache.cache(game);
                });
    }

    @Override
    public void unload() {
        gameCache.saveAll();
    }
}
