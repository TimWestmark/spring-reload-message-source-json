package de.westmark.springjsonreloadableMessageSource;


import de.westmark.springjsonrelodableMessageSource.JsonPropertiesPersister;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StringReader;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

public class JsonPropertiesPersisterTest {
    private JsonPropertiesPersister jsonPropertiesPersister;
    private Properties props;

    @BeforeEach
    public void beforeEach() {
        jsonPropertiesPersister = new JsonPropertiesPersister();
        props = new Properties();
    }

    @Test
    public void empty() throws IOException {
        jsonPropertiesPersister.load(props, new StringReader("{}"));
        assertTrue(props.isEmpty());
    }

    @Test public void stringValue() throws IOException {
        jsonPropertiesPersister.load(props, new StringReader("{key:\"value\"}"));
        assertEquals("value", props.getProperty("key"));
    }

    @Test public void numberValue() throws IOException {
        jsonPropertiesPersister.load(props, new StringReader("{key:123}"));
        assertEquals("123", props.getProperty("key"));
    }

    @Test public void booleanValue() throws IOException {
        jsonPropertiesPersister.load(props, new StringReader("{key:true}"));
        assertEquals("true", props.getProperty("key"));
    }

    @Test public void array() throws IOException {
        jsonPropertiesPersister.load(props, new StringReader("{key:['a','b']}"));

        assertEquals("a", props.getProperty("key.0"));
        assertEquals("b", props.getProperty("key.1"));
    }

    @Test
    public void object() throws IOException {
        jsonPropertiesPersister.load(props, new StringReader("{key:{subKey:'value'}}"));
        assertEquals("value", props.getProperty("key.subKey"));
    }
}
