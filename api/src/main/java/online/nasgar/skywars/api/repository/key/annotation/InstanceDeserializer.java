package online.nasgar.skywars.api.repository.key.annotation;

import online.nasgar.skywars.api.serializer.Serializable;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Defines the annotated type as a class
 * through {@link #value()}.
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface InstanceDeserializer {

    Class<? extends Serializable> value();
}
