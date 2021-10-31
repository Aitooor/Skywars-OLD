package online.nasgar.skywars.api.cache;

import online.nasgar.skywars.api.repository.Repository;
import online.nasgar.skywars.api.serializer.Serializable;

import java.io.IOException;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ExecutionException;

/**
 * Represents the class to use temporal
 * data to save later
 *
 * @param <O> The serializable model to be saved temporary
 */
public interface Cache<O extends Serializable> {

    /**
     * Search for an {@link O} in the cache,
     * using its {@link Serializable#getId()}.
     *
     * @param id The {@link O}'s id.
     * @return An Optional {@link O} value
     * result of the search.
     */
    Optional<O> findOne(String id);

    /**
     * Similar as {@link Cache#findOne(String)},
     * searches for an {@link O} in the cache,
     * using its {@link Serializable#getId()},
     * but, if it isn't present, searches in
     * the {@link Repository}.
     *
     * @param id The {@link O}'s id.
     * @return An Optional {@link O} value
     * result of the cache or repository search.
     */
    Optional<O> findOrGet(String id) throws ExecutionException, InterruptedException, IOException;

    /**
     * Get all cached {@link O}s by the {@link Cache}
     * implementation.
     *
     * @return All cached {@link O}s.
     */
    Set<O> getAll();

    /**
     * Caches an {@link O} into the cache.
     *
     * @param o The {@link O} to be added.
     */
    void cache(O o);

    /**
     * Searches the {@link O} in the
     * {@link Cache} and then will
     * be saved in the {@link Repository}
     * if is present.
     *
     * @param id The {@link O}'s id.
     */
    void save(String id);

    /**
     * @see #save(Serializable)
     *
     * @param o The {@link O}
     */
    default void save(O o){
        save(o.getId());
    }

    /**
     * Saves al cached {@link O}s
     * by the {@link Cache} into
     * the {@link Repository}.
     */
    void saveAll();

    /**
     * Deletes an {@link O} from this {@link Cache}.
     *
     * @param id The {@link O}'s id.
     */
    void remove(String id);

    /**
     * Remove all cached {@link O}s
     */
    void clear();
}
