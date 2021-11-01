package online.nasgar.skywars.flow;

import me.fixeddev.commandflow.annotated.part.AbstractModule;
import me.fixeddev.commandflow.annotated.part.Key;
import me.fixeddev.commandflow.bukkit.annotation.Sender;
import me.yushust.inject.Injector;
import online.nasgar.skywars.api.game.Game;
import online.nasgar.skywars.api.user.User;
import online.nasgar.skywars.flow.factory.GamePartFactory;
import online.nasgar.skywars.flow.factory.UserPartFactory;
import online.nasgar.skywars.flow.factory.UserSenderPartFactory;

public class CustomBukkitModule extends AbstractModule {

    private final Injector injector;

    public CustomBukkitModule(Injector injector){
        this.injector = injector;
    }

    @Override
    public void configure() {
        bindFactory(Game.class, new GamePartFactory(injector));
        bindFactory(User.class, new UserPartFactory(injector));
        bindFactory(new Key(User.class, Sender.class), new UserSenderPartFactory(injector));
    }
}
