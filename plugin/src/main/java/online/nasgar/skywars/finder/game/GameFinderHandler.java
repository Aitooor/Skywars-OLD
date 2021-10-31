package online.nasgar.skywars.finder.game;

import online.nasgar.skywars.api.cache.Cache;
import online.nasgar.skywars.api.finder.ObjectFinderHandler;
import online.nasgar.skywars.api.game.Game;
import online.nasgar.skywars.api.game.type.GameType;
import org.bukkit.util.NumberConversions;

import javax.inject.Inject;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Stream;

public class GameFinderHandler implements ObjectFinderHandler<Game>{

    @Inject private Cache<Game> gameCache;

    @Override
    public Optional<Game> find(Predicate<Game> predicate) {
        Iterator<Game> gameIterator = gameCache.getAll().stream().filter(predicate).iterator();
        Game foundGame = null;
        double percentage = 0.0D;
        while (gameIterator.hasNext()) {
            Game game = gameIterator.next();
            double size = NumberConversions.toDouble(game.getAlive().size());
            double max = NumberConversions.toDouble(game.getMaxPlayers());
            double gamePercentage = size / max;
            if(gamePercentage < 1D){
                if(percentage < gamePercentage){
                    percentage = gamePercentage;
                } else {
                    foundGame = game;
                }
            }
        }
        return Optional.ofNullable(foundGame);
    }
}
