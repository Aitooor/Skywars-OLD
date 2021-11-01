package online.nasgar.skywars.loader;

import online.nasgar.skywars.api.cache.Cache;
import online.nasgar.skywars.api.loader.Loader;
import online.nasgar.skywars.api.user.User;
import online.nasgar.skywars.api.user.impl.UserImpl;
import org.bukkit.Bukkit;

import javax.inject.Inject;
import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

public class UserLoader implements Loader{

    @Inject private Cache<User> userCache;

    @Override
    public void load() {
        Bukkit.getOnlinePlayers().forEach(
                player -> {
                    String id = player.getUniqueId().toString();
                    try {
                        Optional<User> userOptional = userCache.findOrGet(id);
                        User user = userOptional.orElseGet(() -> new UserImpl(player));
                        user.getCoins().setStatistic(10L);
                        user.setPlayer(player);
                        userCache.cache(user);
                    } catch (IOException | InterruptedException | ExecutionException ioException) {
                        ioException.printStackTrace();
                    }
                });
    }

    @Override
    public void unload() {
        Bukkit.getOnlinePlayers().forEach(
                player -> {
                    Optional<User> optionalUser = userCache.findOne(player.getUniqueId().toString());
                    User user = optionalUser.orElseGet(() -> new UserImpl(player));
                    userCache.save(user);
                });
    }
}
