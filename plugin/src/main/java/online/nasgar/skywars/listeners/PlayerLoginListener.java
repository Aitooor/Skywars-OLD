package online.nasgar.skywars.listeners;

import online.nasgar.skywars.SkyWars;
import online.nasgar.skywars.api.cache.Cache;
import online.nasgar.skywars.api.user.User;
import online.nasgar.skywars.api.user.impl.UserImpl;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.scheduler.BukkitRunnable;

import javax.inject.Inject;
import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

public class PlayerLoginListener implements Listener {

    @Inject private SkyWars skyWars;
    @Inject private Cache<User> userCache;

    @EventHandler
    public void onLogin(PlayerLoginEvent event) {
        Bukkit.getScheduler().runTaskLater(
                skyWars,
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        Player player = event.getPlayer();
                        if (player == null) {
                            return;
                        }
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
                    }
                }, 2);
    }
}
