package online.nasgar.skywars.commands;

import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.annotation.Command;
import me.fixeddev.commandflow.annotated.annotation.Named;
import me.fixeddev.commandflow.bukkit.annotation.Sender;
import online.nasgar.skywars.SkyWars;
import online.nasgar.skywars.api.cache.Cache;
import online.nasgar.skywars.api.creator.ObjectCreatorHandler;
import online.nasgar.skywars.api.game.Game;
import online.nasgar.skywars.api.user.User;
import online.nasgar.skywars.util.Utils;
import org.bukkit.entity.Player;

import javax.inject.Inject;

@Command(
        names = "arena",
        desc = "SkyWars arena Command",
        permission = "sw.arena"
)
public class SWArenaCommand implements CommandClass {

    @Inject private SkyWars skyWars;
    @Inject private Cache<Game> gameCache;
    @Inject private ObjectCreatorHandler<Game> gameCreatorHandler;

    @Command(
            names = ""
    ) public boolean onMainCommand(@Sender User user){
        Utils.send(
                user,
                "",
                String.format(
                        "&f-------- &e&lSkyWars &6v%s &f--------",
                        skyWars.getDescription().getVersion()
                ),
                "&f- &7/sw arena create <name> <id> &f- &aCreate/Edit a game",
                "&f- &7/sw arena save <name> &f- &aSave a game",
                "&f-----------------------------"
        );
        return true;
    }

    @Command(
            names = "create",
            permission = "sw.arena.create"
    ) public boolean onCreateCommand(
            @Sender User user,
            @Named("name") String name,
            @Named("id") String id
    ) {
        if(gameCache.getAll().stream().noneMatch(game -> game.getName().equals(name) || game.getId().equals(id))){
            Utils.send(user, "&e&lSkyWars &7- ", String.format("&aCreated game &e%s&f.", name));
        }
        if (gameCreatorHandler.createOrEdit(user, id, name)) {
            Utils.send(user, "&e&lSkyWars &7- ", String.format("&fStarted editing the game &e%s&f.", name));
        } else {
            Utils.send(user, "&e&lSkyWars &7- ", "&cYou're already in setup.");
        }
        return true;
    }

    @Command(
            names = "save",
            permission = "sw.arena.save"
    ) public boolean onSaveCommand(
            @Sender User user,
            @Named("game") Game game
    ) {
        if (gameCreatorHandler.save(user, game)) {
            Utils.send(user, "&e&lSkyWars &7- ", String.format("&fSuccessfully saved the game &e%s&f.", game.getName()));
        } else {
            Utils.send(user, "&e&lSkyWars &7- ", "&cYou're not in setup.");
        }
        return true;
    }
}
