package com.mksherbini.backend.config;

import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.core.env.CompositePropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.DefaultPropertySourceFactory;
import org.springframework.core.io.support.EncodedResource;

import javax.validation.constraints.NotNull;
import java.io.IOException;

public class YamlPropertyLoaderFactory extends DefaultPropertySourceFactory {
    @NotNull
    @Override
    public PropertySource<?> createPropertySource(String name, @NotNull EncodedResource resource) throws IOException {
        try {
            CompositePropertySource propertySource = new CompositePropertySource(name);
            new YamlPropertySourceLoader().load(resource.getResource().getFilename(), resource.getResource())
                    .forEach(propertySource::addPropertySource);
            return propertySource;
        } catch (IllegalStateException e) {
            throw new IOException(e);
        }
    }
}
