package ru.lairon.service.placeholder.defaultplaceholder;

import java.util.Objects;

public class PlaceholderSettings {

    private final String id;
    private String author;
    private String version;

    public PlaceholderSettings(String id) {
        this(id, null, null);
    }

    public PlaceholderSettings(String id, String author, String version) {
        Objects.requireNonNull(id, "id can not be null");
        this.id = id;
        this.author = author;
        this.version = version;
    }

    public String getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public static Builder builder(String id){
        return new Builder(id);
    }

    public static class Builder {

        private final String id;
        private String author;
        private String version;

        public Builder(String id) {
            Objects.requireNonNull(id, "id can not be null");
            this.id = id;
        }

        public Builder author(String author){
            this.author = author;
            return this;
        }

        public Builder version(String version){
            this.version = version;
            return this;
        }

        public PlaceholderSettings build(){
            return new PlaceholderSettings(id, author, version);
        }

    }

}
