package com.zsener.ReadingIsGood.request.validator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class ValidatorFactory {

    private final Map<Class<?>, IValidator<?>> registry = new HashMap();

    public ValidatorFactory(List<IValidator<?>> validators) {
        validators.forEach(this::register);
    }

    public <T> IValidator<T> getFor(Class<T> clazz) {
        IValidator<?> validator = registry.get(clazz);
        if(validator == null) {
            log.error("");
        }
        return (IValidator<T>) validator;
    }

    private void register(IValidator<?> validator) {
        Type[] implementedInterfaces = validator.getClass().getGenericInterfaces();
        ParameterizedType validatorInterface = (ParameterizedType) implementedInterfaces[0];
        Type[] types = validatorInterface.getActualTypeArguments();
        Class<?> clazz = (Class<?>) types[0];
        registry.put(clazz, validator);
    }
}
