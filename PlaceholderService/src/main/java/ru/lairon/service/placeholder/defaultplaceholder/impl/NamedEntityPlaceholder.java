package ru.lairon.service.placeholder.defaultplaceholder.impl;

import ru.lairon.service.namedentity.NamedEntity;
import ru.lairon.service.placeholder.defaultplaceholder.Placeholder;
import ru.lairon.service.placeholder.defaultplaceholder.PlaceholderSettings;

public class NamedEntityPlaceholder extends Placeholder {
    public NamedEntityPlaceholder() {
        super(PlaceholderSettings.builder("entity")
                .author("0xLairon1")
                .version("0.0.1")
                .build());
    }

    @Override
    public String apply(NamedEntity entity, String arg) {
        if(entity == null) return "";
        if(arg.equalsIgnoreCase("uuid"))
            return entity.getUUID().toString();
        else if (arg.equalsIgnoreCase("name"))
            return entity.getName();

        return "";
    }
}
