package online.nasgar.skywars.database;

import com.google.gson.GsonBuilder;
import me.yushust.inject.key.TypeReference;
import online.nasgar.skywars.SkyWars;
import online.nasgar.skywars.api.repository.Repository;
import online.nasgar.skywars.api.serializer.Serializable;

import javax.inject.Inject;
import java.util.concurrent.ExecutorService;

public class JSONRepository<O extends Serializable> implements Repository<O> {



    @Inject private JSONRepository(
            SkyWars skyWars,
            TypeReference<O> typeReference,
            GsonBuilder gsonBuilder,
            ExecutorService executorService) {

    }
}
