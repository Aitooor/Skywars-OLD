package online.nasgar.skywars.flow.part;

import me.fixeddev.commandflow.CommandContext;
import me.fixeddev.commandflow.exception.ArgumentParseException;
import me.fixeddev.commandflow.part.ArgumentPart;
import me.fixeddev.commandflow.stack.ArgumentStack;
import me.yushust.inject.Injector;
import online.nasgar.skywars.api.game.Game;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class GamePart implements ArgumentPart {

    private String name;
    private Injector injector;

    public GamePart(String name, Injector injector){
        this.name = name;
        this.injector = injector;
    }

    @Override
    public List<? extends Object> parseValue(CommandContext commandContext, ArgumentStack argumentStack) throws ArgumentParseException {
        return null;
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
