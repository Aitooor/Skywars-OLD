package online.nasgar.skywars.creator.game;

import online.nasgar.skywars.api.cache.Cache;
import online.nasgar.skywars.api.creator.ObjectCreatorHandler;
import online.nasgar.skywars.api.game.Game;
import online.nasgar.skywars.api.game.impl.GameImpl;
import online.nasgar.skywars.api.user.User;
import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;
import java.util.concurrent.ConcurrentHashMap;

public class GameCreatorHandler implements ObjectCreatorHandler<Game> {

    @Inject private Cache<Game> gameCache;
    private final ConcurrentHashMap<User, Game> setupUsers;

    public GameCreatorHandler() {
        this.setupUsers = new ConcurrentHashMap<>();
    }

    @Override
    public boolean createOrEdit(@NotNull User user, String id, String name) {
        if(isSetup(user)){
            return false;
        }
        Game game = gameCache.findOne(id).orElseGet(() -> new GameImpl(id, name));
        game.setDisabled(true);
        gameCache.cache(game);
        setupUsers.put(user, game);
        return true;
    }

    @Override
    public boolean save(@NotNull User user, Game object) {
        if (!isSetup(user) || !gameCache.findOne(object.getId()).isPresent()) {
            return false;
        }
        object.setDisabled(false);
        gameCache.save(object);
        setupUsers.remove(user);
        return true;
    }

    @Override
    public boolean isSetup(@NotNull User user) {
        return setupUsers.containsKey(user);
    }


}
