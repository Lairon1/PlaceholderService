package ru.lairon.service.placeholder;

import ru.lairon.service.namedentity.NamedEntity;
import ru.lairon.service.placeholder.defaultplaceholder.Placeholder;

import java.util.Map;
import java.util.Optional;

public interface PlaceholderService {


    String applyPlaceholders(NamedEntity entity, String str, Map<String, Object> placeholders);
    String applyPlaceholders(NamedEntity entity, String str, Object... placeholders);

    void registerPlaceholder(Placeholder placeholder);
    void unregisterPlaceholder(Placeholder placeholder);
    Optional<Placeholder> findPlaceholderByID(String id);
}
