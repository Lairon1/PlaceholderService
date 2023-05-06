package ru.lairon.service.placeholder.impl;

import junit.framework.Assert;
import junit.framework.TestCase;
import ru.lairon.service.namedentity.impl.DefaultNamedEntity;
import ru.lairon.service.placeholder.PlaceholderService;
import ru.lairon.service.placeholder.defaultplaceholder.impl.NamedEntityPlaceholder;

import java.util.UUID;

public class PlaceholderServiceTest extends TestCase {

    public void testApplyPlaceholders() {
        PlaceholderService placeholderService = new DefaultPlaceholderService('{', '}');
        UUID uuid = UUID.randomUUID();
        String apply = placeholderService
                .applyPlaceholders(null, "testPlaceholders uuid: {user_uuid}, name: {username}",
                "user_uuid", uuid,
                "username", "lairon"
        );
        Assert.assertEquals("testPlaceholders uuid: " + uuid + ", name: lairon", apply);
    }


    public void testApplyPlaceholdersWithBackslash(){
        PlaceholderService placeholderService = new DefaultPlaceholderService('{', '}');
        double random = Math.random();
        String applied = placeholderService
                .applyPlaceholders(null, "double:{randomDouble}\\{testBack}\\\\",
                        "randomDouble", random,
                        "testBack", "asd");
        Assert.assertEquals("double:" + random + "{testBack}\\", applied);
    }

    public void testNamedEntityPlaceholder(){
        PlaceholderService placeholderService = new DefaultPlaceholderService('{', '}');
        placeholderService.registerPlaceholder(new NamedEntityPlaceholder());
        DefaultNamedEntity entity = new DefaultNamedEntity(UUID.randomUUID(), "0xLairon1");
        String applied = placeholderService.applyPlaceholders(entity,
                "TestPlaceholders: {entity_uuid}, {entity_name}");
        Assert.assertEquals("TestPlaceholders: " + entity.getUUID() + ", " + entity.getName(),
                applied);
    }
}