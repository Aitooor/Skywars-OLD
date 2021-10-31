package online.nasgar.skywars.database;

import online.nasgar.skywars.api.repository.Repository;
import online.nasgar.skywars.api.serializer.Serializable;

import java.io.IOException;
import java.util.Optional;
import java.util.Set;

public class MongoRepository<O extends Serializable> implements Repository<O> {

    @Override
    public Optional<O> findSync(String id) throws IOException {
        return Optional.empty();
    }

    @Override
    public Set<O> findAllSync() {
        return null;
    }

    @Override
    public boolean removeSync(String id) {
        return false;
    }

    @Override
    public boolean replaceSync(String id, O o) throws IOException {
        return false;
    }

    @Override
    public boolean saveSync(O o) throws IOException {
        return false;
    }
}
