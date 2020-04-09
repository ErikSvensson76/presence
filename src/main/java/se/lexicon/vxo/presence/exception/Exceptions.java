package se.lexicon.vxo.presence.exception;

import java.util.function.Supplier;

import static se.lexicon.vxo.presence.text.ExceptionMessages.APP_RESOURCE_NOT_FOUND;

public interface Exceptions {

    static Supplier<AppResourceNotFoundException> appResourceNotFoundException(){
        return () -> new AppResourceNotFoundException(APP_RESOURCE_NOT_FOUND);
    }

}
