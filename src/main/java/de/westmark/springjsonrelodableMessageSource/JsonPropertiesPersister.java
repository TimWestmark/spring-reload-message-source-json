package de.westmark.springjsonrelodableMessageSource;/* Adapted from https://gist.github.com/eungju/1263507 */


import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.PropertiesPersister;



public class JsonPropertiesPersister implements PropertiesPersister {
    @Override
    public void load(Properties props, InputStream is) throws IOException {
        load(props, new InputStreamReader(is));
    }

    @Override
    public void load(Properties props, Reader reader) throws IOException {
        ObjectMapper m = new ObjectMapper();
        m.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        m.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        m.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
        JsonNode node = m.readTree(reader);
        load(props, node, "");
    }

    void load(Properties props, JsonNode node, String key) {
        if (node.isObject()) {
            Iterator<Map.Entry<String, JsonNode>> fields = node.fields();
            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> field = fields.next();
                load(props, field.getValue(), join(key, field.getKey()));
            }
        } else if (node.isArray()) {
            Iterator<JsonNode> values = node.elements();
            int i = 0;
            while (values.hasNext()) {
                JsonNode value = values.next();
                load(props, value, join(key, String.valueOf(i)));
                i++;
            }
        } else {
            props.setProperty(key, node.textValue());
        }
    }

    String join(String prefix, String key) {
        return prefix.isEmpty() ? key : prefix + "." + key;
    }

    @Override
    public void store(Properties props, OutputStream os, String header) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void store(Properties props, Writer writer, String header) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void loadFromXml(Properties props, InputStream is) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void storeToXml(Properties props, OutputStream os, String header) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void storeToXml(Properties props, OutputStream os, String header, String encoding) throws IOException {
        throw new UnsupportedOperationException();
    }
}
