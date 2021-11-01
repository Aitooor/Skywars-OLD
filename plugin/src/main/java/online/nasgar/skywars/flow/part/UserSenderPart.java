package online.nasgar.skywars.flow.part;

import me.fixeddev.commandflow.CommandContext;
import me.fixeddev.commandflow.bukkit.BukkitCommandManager;
import me.fixeddev.commandflow.exception.ArgumentParseException;
import me.fixeddev.commandflow.exception.CommandException;
import me.fixeddev.commandflow.part.CommandPart;
import me.fixeddev.commandflow.stack.ArgumentStack;
import me.yushust.inject.Injector;
import me.yushust.inject.key.TypeReference;
import net.kyori.text.TranslatableComponent;
import online.nasgar.skywars.api.cache.Cache;
import online.nasgar.skywars.api.user.User;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;
import java.util.Optional;

public class UserSenderPart implements CommandPart {
    private final Injector injector;
    private final String name;

    public UserSenderPart(Injector injector, String name) {
        this.injector = injector;
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void parse(CommandContext context, ArgumentStack stack) throws ArgumentParseException {
        CommandSender sender = context.getObject(CommandSender.class, BukkitCommandManager.SENDER_NAMESPACE);
        Cache<User> cache = injector.getInstance(new TypeReference<Cache<User>>() {});
        if (sender != null) {
            if(sender instanceof Player){
                Player player = (Player) sender;
                String id = player.getUniqueId().toString();
                Optional<User> user = cache.findOne(id);
                if(user.isPresent()) {
                    context.setValue(this, user.get());
                    return;
                }
            }

            throw new ArgumentParseException(TranslatableComponent.of("sender.only-player"));
        }

        throw new CommandException(TranslatableComponent.of("sender.unknown"));
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserSenderPart)){
            return false;
        }
        UserSenderPart that = (UserSenderPart) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
