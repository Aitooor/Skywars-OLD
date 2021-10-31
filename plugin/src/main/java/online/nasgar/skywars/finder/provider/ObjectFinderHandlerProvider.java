package online.nasgar.skywars.finder.provider;

import me.yushust.inject.Injector;
import me.yushust.inject.key.TypeReference;
import online.nasgar.skywars.api.finder.ObjectFinderHandler;
import online.nasgar.skywars.api.game.Game;
import online.nasgar.skywars.api.serializer.Serializable;
import online.nasgar.skywars.creator.game.GameCreatorHandler;
import online.nasgar.skywars.finder.game.GameFinderHandler;

import javax.inject.Inject;
import javax.inject.Provider;

public class ObjectFinderHandlerProvider<O extends Serializable> implements Provider<ObjectFinderHandler<O>> {

    @Inject private Injector injector;
    @Inject private TypeReference<O> typeReference;

    @Override
    public ObjectFinderHandler<O> get() {
        if(typeReference.getType() instanceof Game){
            return injector.getInstance(TypeReference.of(GameFinderHandler.class));
        }
        return null;
    }
}
