package online.nasgar.skywars.flow.part;

import me.fixeddev.commandflow.CommandContext;
import me.fixeddev.commandflow.exception.ArgumentParseException;
import me.fixeddev.commandflow.part.ArgumentPart;
import me.fixeddev.commandflow.stack.ArgumentStack;

import java.lang.reflect.Type;
import java.util.List;

public class UserPart implements ArgumentPart {

    @Override
    public List<? extends Object> parseValue(CommandContext commandContext, ArgumentStack argumentStack) throws ArgumentParseException {
        return null;
    }

    @Override
    public Type getType() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }
}
