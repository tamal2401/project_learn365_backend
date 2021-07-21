package org.learn365.test.util;

import java.util.Optional;

public class TestUtil {

    public static <T> T optionalExceptionWrapper(T value, String msg){
        Optional<T> result = Optional.of(value);
        return result.orElseThrow(()-> {
            throw new RuntimeException(msg);
        });
    }
}
