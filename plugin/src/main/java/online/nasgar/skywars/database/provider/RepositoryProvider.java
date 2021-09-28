package online.nasgar.skywars.database.provider;

import me.yushust.inject.Injector;
import me.yushust.inject.key.TypeReference;
import online.nasgar.skywars.api.repository.Repository;
import online.nasgar.skywars.api.serializer.Serializable;
import online.nasgar.skywars.database.JSONRepository;
import online.nasgar.skywars.database.MySQLRepository;
import online.nasgar.skywars.file.FileHandler;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;
import java.util.Locale;

public class RepositoryProvider<O extends Serializable> implements Provider<Repository<O>> {

    @Inject @Named("config") private FileHandler config;
    @Inject private Injector injector;
    @Inject private TypeReference<O> typeReference;

    @Override
    public Repository<O> get() {
        if(config.getString("plugin.storage-type").toUpperCase(Locale.ROOT).equals("MYSQL")){
            return injector.getInstance(
                    TypeReference.of(
                            MySQLRepository.class,
                            typeReference.getRawType()
                    )
            );
        }
        return injector.getInstance(
                TypeReference.of(
                        JSONRepository.class,
                        typeReference.getRawType()
                )
        );
    }
}
