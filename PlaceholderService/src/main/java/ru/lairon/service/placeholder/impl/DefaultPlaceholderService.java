package ru.lairon.service.placeholder.impl;

import ru.lairon.service.namedentity.NamedEntity;

import java.util.Map;
import java.util.Stack;

public class DefaultPlaceholderService extends AbstractPlaceholderService {

    private final char prefix, suffix;

    public DefaultPlaceholderService(char prefix, char suffix) {
        this.prefix = prefix;
        this.suffix = suffix;
        if(prefix == suffix)
            throw new IllegalArgumentException("prefix and suffix can not be equals");
    }

    @Override
    public String applyPlaceholders(NamedEntity entity, String str, Map<String, Object> placeholders) {
        //Проверить на наличие плейсхолдеров !!!
        StringBuilder result = new StringBuilder();
        Stack<StringBuilder> placeholderKeys = new Stack<>();

        char[] charArray = str.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            char current = charArray[i], previous = charArray[i - 1 < 0 ? i : i - 1];
            if(current == '\\' && previous != '\\'){
                continue;
            }
            if (current == prefix && previous != '\\') {
                placeholderKeys.push(new StringBuilder());
                continue;
            }
            if(current == suffix && previous != '\\' && placeholderKeys.size() != 0){
                StringBuilder cpk = placeholderKeys.pop();
                String string = cpk.toString();
                Object value = placeholders.get(string);
                if(value != null){
                    result.append(value);
                    continue;
                }
                int beginIndex = string.indexOf("_");
                String id = string.substring(0, beginIndex);
                findPlaceholderByID(id).ifPresent(placeholder -> {
                    String apply = placeholder.apply(entity, string.substring(beginIndex + 1));
                    result.append(apply);
                });
                continue;
            }
            if(placeholderKeys.size() != 0){
                placeholderKeys.peek().append(current);
                continue;
            }

            result.append(current);
        }

        return result.toString();
    }


}
