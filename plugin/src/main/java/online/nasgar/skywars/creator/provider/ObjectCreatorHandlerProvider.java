package online.nasgar.skywars.creator.provider;

import me.yushust.inject.Injector;
import me.yushust.inject.key.TypeReference;
import online.nasgar.skywars.api.creator.ObjectCreatorHandler;
import online.nasgar.skywars.api.game.Game;
import online.nasgar.skywars.api.serializer.Serializable;
import online.nasgar.skywars.creator.game.GameCreatorHandler;


import javax.inject.Inject;
import javax.inject.Provider;

public class ObjectCreatorHandlerProvider<O extends Serializable> implements Provider<ObjectCreatorHandler<O>> {

    @Inject private Injector injector;
    @Inject private TypeReference<O> typeReference;

    @Override
    public ObjectCreatorHandler<O> get() {
        if(typeReference.getType() instanceof Game){
            return injector.getInstance(TypeReference.of(GameCreatorHandler.class));
        }
        return null;
    }
}
