package online.nasgar.skywars.database;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import me.yushust.inject.key.TypeReference;
import online.nasgar.skywars.SkyWars;
import online.nasgar.skywars.api.repository.Repository;
import online.nasgar.skywars.api.repository.key.fetcher.InstanceDeserializerFetcher;
import online.nasgar.skywars.api.repository.key.fetcher.RepositoryKeyFetcher;
import online.nasgar.skywars.api.serializer.Serializable;

import javax.inject.Inject;
import java.io.*;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ExecutorService;

public class JSONRepository<O extends Serializable> implements Repository<O> {

    private final File folder;
    private final Class<O> clazz;
    private final TypeReference<O> typeReference;
    private final Gson gson;
    private final ExecutorService executorService;

    @Inject private JSONRepository(
            SkyWars skyWars,
            TypeReference<O> typeReference,
            GsonBuilder gsonBuilder,
            ExecutorService executorService) {
        this.clazz = typeReference.getRawType();
        this.folder = new File(skyWars.getDataFolder(), RepositoryKeyFetcher.fetch(clazz));
        if(!folder.exists()){
            folder.mkdirs();
        }
        this.typeReference = typeReference;
        this.gson = gsonBuilder.create();
        this.executorService = executorService;
    }


    @Override
    public Optional<O> findSync(String id) throws IOException {
        File file = new File(
                folder,
                id + ".json"
        );
        if (!file.exists()) {
            return Optional.empty();
        }
        Reader reader = Files.newBufferedReader(file.toPath());
        return Optional.ofNullable(
                gson.fromJson(
                        reader,
                        InstanceDeserializerFetcher.fetch(clazz)
                ));
    }

    @Override
    public Set<O> findAllSync() {
        Set<O> objects = new HashSet<>();
        File[] files = folder.listFiles(pathname -> pathname.getName().endsWith(".json"));
        if (files == null) {
            return objects;
        }
        for (File file : files) {
            try {
                objects.add(
                        gson.fromJson(
                                new FileReader(file),
                                InstanceDeserializerFetcher.fetch(clazz)
                        )
                );
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return objects;
    }

    @Override
    public boolean removeSync(String id) {
        File file = new File(
                folder,
                id + ".json"
        );

        if(!file.exists()){
            return false;
        }
        return file.delete();
    }

    @Override
    public boolean replaceSync(String id, O o) throws IOException {
        File file = new File(
                folder,
                id + ".json"
        );
        createFile(file);

        FileWriter fileWriter = new FileWriter(file.getPath());
        gson.toJson(o, InstanceDeserializerFetcher.fetch(clazz), fileWriter);
        fileWriter.flush();
        fileWriter.close();
        return file.exists();
    }

    @Override
    public boolean saveSync(O o) throws IOException{
        File file = new File(
                folder,
                o.getId() + ".json"
        );
        createFile(file);
        FileWriter fileWriter = new FileWriter(file.getPath());
        gson.toJson(o, InstanceDeserializerFetcher.fetch(clazz), fileWriter);
        fileWriter.flush();
        fileWriter.close();
        return true;
    }

    public void createFile(File file){
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
