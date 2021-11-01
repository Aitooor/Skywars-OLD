package online.nasgar.skywars.flow.part;

import me.fixeddev.commandflow.CommandContext;
import me.fixeddev.commandflow.bukkit.BukkitCommandManager;
import me.fixeddev.commandflow.exception.ArgumentParseException;
import me.fixeddev.commandflow.part.ArgumentPart;
import me.fixeddev.commandflow.stack.ArgumentStack;
import me.yushust.inject.Injector;
import me.yushust.inject.key.TypeReference;
import net.kyori.text.TextComponent;
import net.kyori.text.TranslatableComponent;
import online.nasgar.skywars.api.cache.Cache;
import online.nasgar.skywars.api.user.User;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class UserPart implements ArgumentPart {

    private String name;
    private boolean orSource;
    private Injector injector;

    public UserPart(String name, boolean orSource, Injector injector){
        this.name = name;
        this.injector = injector;
    }


    @Override
    public List<User> parseValue(CommandContext context, ArgumentStack argumentStack) throws ArgumentParseException {
        User user;

        Cache<User> cache = injector.getInstance(new TypeReference<Cache<User>>() {});
        if (!argumentStack.hasNext()) {
            user = tryGetSender(context);
            if (orSource && user != null) {
                return Collections.singletonList(user);
            }
        }

        String target = argumentStack.next();

        try {
            UUID uuid = UUID.fromString(target);
            user = cache.findOne(uuid.toString()).orElse(null);
        } catch (IllegalArgumentException ex) {
            user = getUser(target);

            if (user == null) {
                user = tryGetSender(context);

                if (orSource && user != null) {
                    return Collections.singletonList(user);
                }

                ArgumentParseException exception = new ArgumentParseException(TranslatableComponent.of("player.offline", TextComponent.of(target)));
                exception.setArgument(this);

                throw exception;
            }

        }

        return Collections.singletonList(user);
    }

    @Override
    public List<String> getSuggestions(CommandContext commandContext, ArgumentStack stack) {
        String prefix  = stack.hasNext() ? stack.next() : "";

        List<String> suggestions = new ArrayList<>();

        Cache<User> cache = injector.getInstance(new TypeReference<Cache<User>>() {});

        for(User user : cache.getAll()){
            if(user.getName().startsWith(prefix)){
                suggestions.add(user.getName());
            }
        }

        return suggestions;
    }

    private User tryGetSender(CommandContext context){
        CommandSender sender = context.getObject(CommandSender.class, BukkitCommandManager.SENDER_NAMESPACE);

        Cache<User> cache = injector.getInstance(new TypeReference<Cache<User>>() {});
        if (sender instanceof Player) {
            Player player = (Player) sender;
            return cache
                    .findOne(player.getUniqueId().toString())
                    .orElse(null);
        }

        return null;
    }

    private User getUser(String name){
        Cache<User> cache = injector.getInstance(new TypeReference<Cache<User>>() {});
        for(User user : cache.getAll()){
            if(user.getName().equalsIgnoreCase(name)){
                return user;
            }
        }
        return null;
    }

    @Override
    public Type getType() {
        return User.class;
    }

    @Override
    public String getName() {
        return name;
    }
}
