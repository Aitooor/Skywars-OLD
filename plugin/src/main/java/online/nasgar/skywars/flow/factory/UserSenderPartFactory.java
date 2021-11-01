package online.nasgar.skywars.flow.factory;

import me.fixeddev.commandflow.annotated.part.PartFactory;
import me.fixeddev.commandflow.part.CommandPart;
import me.yushust.inject.Injector;
import online.nasgar.skywars.flow.part.UserSenderPart;

import java.lang.annotation.Annotation;
import java.util.List;

public class UserSenderPartFactory implements PartFactory {

    private final Injector injector;

    public UserSenderPartFactory(Injector injector){
        this.injector = injector;
    }

    @Override
    public CommandPart createPart(String name, List<? extends Annotation> modifiers) {
        return new UserSenderPart(injector, name);
    }
}
