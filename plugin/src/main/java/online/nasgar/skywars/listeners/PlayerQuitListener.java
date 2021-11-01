package online.nasgar.skywars.listeners;

import online.nasgar.skywars.api.cache.Cache;
import online.nasgar.skywars.api.user.User;
import online.nasgar.skywars.api.user.impl.UserImpl;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import javax.inject.Inject;
import java.util.Optional;

public class PlayerQuitListener implements Listener {

    @Inject private Cache<User> userCache;

    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        Player player = event.getPlayer();
        Optional<User> optionalUser = userCache.findOne(player.getUniqueId().toString());
        User user = optionalUser.orElseGet(() -> new UserImpl(player));
        userCache.save(user);
    }
}
