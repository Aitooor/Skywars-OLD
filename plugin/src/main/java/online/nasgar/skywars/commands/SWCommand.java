package online.nasgar.skywars.commands;

import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.annotation.Command;
import me.fixeddev.commandflow.annotated.annotation.Named;
import me.fixeddev.commandflow.annotated.annotation.OptArg;
import me.fixeddev.commandflow.annotated.annotation.SubCommandClasses;
import me.fixeddev.commandflow.bukkit.annotation.Sender;
import me.yushust.message.MessageHandler;
import online.nasgar.skywars.SkyWars;
import online.nasgar.skywars.api.cache.Cache;
import online.nasgar.skywars.api.creator.ObjectCreatorHandler;
import online.nasgar.skywars.api.finder.ObjectFinderHandler;
import online.nasgar.skywars.api.game.Game;
import online.nasgar.skywars.api.user.User;
import online.nasgar.skywars.util.Utils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.inject.Inject;
import java.util.Optional;

@Command(
        names = {"sw", "skywars"}
)
@SubCommandClasses(
        SWArenaCommand.class
)
public class SWCommand implements CommandClass {

    @Inject private SkyWars skyWars;
    @Inject private ObjectFinderHandler<Game> gameObjectFinderHandler;
    @Inject private MessageHandler messageHandler;

    @Command(
            names = ""
    ) public boolean onMainCommand(CommandSender sender) {
        if (sender.hasPermission("sw.cmds")) {
            Utils.send(
                    sender,
                    "",
                    String.format(
                            "&f-------- &e&lSkyWars &6v%s &f--------",
                            skyWars.getDescription().getVersion()
                    ),
                    "&f- &7/sw arena &f- &aArena admin commands",
                    "&f-----------------------------"
            );
        }
        return true;
    }

    @Command(
            names = "join",
            permission = "sw.join"
    ) public boolean onJoinCommand(@Sender User user, @OptArg Game game){
        if(game == null){
            Optional<Game> optionalGame = gameObjectFinderHandler.find(games -> !games.isFull());
            if(!optionalGame.isPresent()){
                return true;
            }
            game = optionalGame.get();
        }
        game.getPlayers().add(user);
        return true;
    }
}
