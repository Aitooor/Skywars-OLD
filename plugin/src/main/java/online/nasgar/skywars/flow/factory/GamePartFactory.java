package online.nasgar.skywars.flow.factory;

import me.fixeddev.commandflow.annotated.part.PartFactory;
import me.fixeddev.commandflow.part.CommandPart;
import me.yushust.inject.Injector;
import online.nasgar.skywars.flow.part.GamePart;

import java.lang.annotation.Annotation;
import java.util.List;

public class GamePartFactory implements PartFactory {

    private final Injector injector;

    public GamePartFactory(Injector injector){
        this.injector = injector;
    }

    @Override
    public CommandPart createPart(String name, List<? extends Annotation> modifiers) {
        return new GamePart(name, injector);
    }
}
