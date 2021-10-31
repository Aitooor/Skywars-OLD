package online.nasgar.skywars.cache;

import online.nasgar.skywars.api.cache.Cache;
import online.nasgar.skywars.api.repository.Repository;
import online.nasgar.skywars.api.serializer.Serializable;

import javax.inject.Inject;
import java.io.IOException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;

public class LocalCache<O extends Serializable> implements Cache<O> {

    private final ConcurrentMap<String, O> cache = new ConcurrentHashMap<>();
    private final Repository<O> repository;

    @Inject
    public LocalCache(Repository<O> repository){
        this.repository = repository;
    }

    @Override
    public Optional<O> findOne(String id) {
        return Optional.ofNullable(cache.get(id));
    }

    @Override
    public Optional<O> findOrGet(String id) throws ExecutionException, InterruptedException, IOException {
        if(!findOne(id).isPresent()){
            return repository.findSync(id);
        }
        return findOne(id);
    }

    @Override
    public Set<O> getAll() {
        return new HashSet<>(cache.values());
    }

    @Override
    public void cache(O o) {
        cache.put(o.getId(), o);
    }

    @Override
    public void save(String id) {
        findOne(id).ifPresent(repository::save);
    }

    @Override
    public void saveAll() {
        getAll()
                .stream()
                .map(Serializable::getId)
                .forEach(this::save);
    }

    @Override
    public void remove(String id) {
        cache.remove(id);
    }

    @Override
    public void clear() {
        cache.clear();
    }
}
