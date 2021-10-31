package online.nasgar.skywars.api.finder;

import online.nasgar.skywars.api.game.Game;
import online.nasgar.skywars.api.serializer.Serializable;

import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * Represents the finder of objects.
 */
@FunctionalInterface
public interface ObjectFinderHandler<O extends Serializable> {

    /**
     * Searches for an {@link O}
     *
     * @return An {@link Optional}
     */
    Optional<O> find(Predicate<O> predicate);
}
