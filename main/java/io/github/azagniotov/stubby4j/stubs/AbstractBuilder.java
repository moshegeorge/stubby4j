package io.github.azagniotov.stubby4j.stubs;


import io.github.azagniotov.stubby4j.yaml.ConfigurableYAMLProperty;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static io.github.azagniotov.generics.TypeSafeConverter.as;

public abstract class AbstractBuilder<T extends ReflectableStub> {

    final Map<ConfigurableYAMLProperty, Object> fieldNameAndValues;

    AbstractBuilder() {
        this.fieldNameAndValues = new HashMap<>();
    }

    <E> E getStaged(final Class<E> clazzor, final ConfigurableYAMLProperty property, E orElse) {
        return fieldNameAndValues.containsKey(property) ? as(clazzor, fieldNameAndValues.get(property)) : orElse;
    }

    public void stage(final Optional<ConfigurableYAMLProperty> fieldNameOptional, final Optional<Object> fieldValueOptional) {
        if (fieldNameOptional.isPresent() && fieldValueOptional.isPresent()) {
            fieldNameAndValues.put(fieldNameOptional.get(), fieldValueOptional.get());
        }
    }

    public abstract T build();
}
