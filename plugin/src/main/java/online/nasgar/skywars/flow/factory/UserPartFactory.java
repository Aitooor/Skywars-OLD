package online.nasgar.skywars.flow.factory;

import me.fixeddev.commandflow.annotated.part.PartFactory;
import me.fixeddev.commandflow.bukkit.annotation.PlayerOrSource;
import me.fixeddev.commandflow.part.CommandPart;
import me.yushust.inject.Injector;
import online.nasgar.skywars.flow.part.UserPart;

import java.lang.annotation.Annotation;
import java.util.List;

public class UserPartFactory implements PartFactory {

    private final Injector injector;

    public UserPartFactory(Injector injector){
        this.injector = injector;
    }

    @Override
    public CommandPart createPart(String name, List<? extends Annotation> modifiers) {
        boolean orSource = false;
        for (Annotation modifier : modifiers) {
            if (modifier.annotationType() == PlayerOrSource.class) {
                orSource = true;
            }
        }
        return new UserPart(name, orSource, injector);
    }
}
