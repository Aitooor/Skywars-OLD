package online.nasgar.skywars.flow.part;

import me.fixeddev.commandflow.CommandContext;
import me.fixeddev.commandflow.exception.ArgumentParseException;
import me.fixeddev.commandflow.part.ArgumentPart;
import me.fixeddev.commandflow.stack.ArgumentStack;
import me.yushust.inject.Injector;
import me.yushust.inject.key.TypeReference;
import online.nasgar.skywars.api.cache.Cache;
import online.nasgar.skywars.api.game.Game;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class GamePart implements ArgumentPart {

    private String name;
    private Injector injector;

    public GamePart(String name, Injector injector){
        this.name = name;
        this.injector = injector;
    }

    @Override
    public List<Game> parseValue(CommandContext context, ArgumentStack argumentStack) throws ArgumentParseException {
        return Collections.singletonList(getGame(argumentStack));
    }

    @Override
    public List<String> getSuggestions(CommandContext commandContext, ArgumentStack stack) {
        String prefix  = stack.hasNext() ? stack.next() : "";

        List<String> suggestions = new ArrayList<>();

        Cache<Game> cache = injector.getInstance(new TypeReference<Cache<Game>>() {});
        Set<Game> games = cache.getAll();

        for(Game game : games){
            if(game.getName().startsWith(prefix)){
                suggestions.add(game.getName());
            }
        }

        return suggestions;
    }

    private Game getGame(ArgumentStack stack){
        Cache<Game> cache = injector.getInstance(new TypeReference<Cache<Game>>() {});
        Set<Game> gameSet = cache.getAll();
        for(Game game : gameSet){
            if(game.getName().equalsIgnoreCase(stack.next())){
                return game;
            }
        }
        throw new ArgumentParseException("This game don't exists!");
    }

    @Override
    public Type getType() {
        return Game.class;
    }

    @Override
    public String getName() {
        return name;
    }
}
