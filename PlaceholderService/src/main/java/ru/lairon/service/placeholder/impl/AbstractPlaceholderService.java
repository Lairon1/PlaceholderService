package ru.lairon.service.placeholder.impl;

import ru.lairon.service.namedentity.NamedEntity;
import ru.lairon.service.placeholder.PlaceholderService;
import ru.lairon.service.placeholder.defaultplaceholder.Placeholder;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public abstract class AbstractPlaceholderService implements PlaceholderService {

    private Map<String, Placeholder> placeholders = new HashMap<>();


    @Override
    public String applyPlaceholders(NamedEntity entity, String str, Object... placeholders) {
        return applyPlaceholders(entity, str, arrayToMap(placeholders));
    }

    private Map<String, Object> arrayToMap(Object... array) {
        if (array.length % 2 != 0)
            throw new IllegalArgumentException("Key " + array[array.length - 1] + " don't have a value");
        Map<String, Object> result = new HashMap<>();
        for (int i = 0; i < array.length; i += 2) {
            result.put(array[i].toString(), array[i + 1]);
        }
        return result;
    }

    @Override
    public void registerPlaceholder(Placeholder placeholder) {
        if(findPlaceholderByID(placeholder.getSettings().getId()).isPresent())
            throw new IllegalArgumentException("Placeholder " + placeholder.getSettings().getId() + " is already registered");
        placeholders.put(placeholder.getSettings().getId(), placeholder);
    }

    @Override
    public void unregisterPlaceholder(Placeholder placeholder) {
        placeholders.remove(placeholder.getSettings().getId());
    }

    @Override
    public Optional<Placeholder> findPlaceholderByID(String id) {
        return Optional.ofNullable(placeholders.get(id));
    }
}
