package ru.lairon.service.placeholder.defaultplaceholder;

import ru.lairon.service.namedentity.NamedEntity;

import java.util.Objects;

public abstract class Placeholder {

    private final PlaceholderSettings settings;

    public Placeholder(PlaceholderSettings settings) {
        Objects.requireNonNull(settings, "settings can not be null");
        this.settings = settings;
    }


    public PlaceholderSettings getSettings() {
        return settings;
    }

    public abstract String apply(NamedEntity entity, String arg);

}
