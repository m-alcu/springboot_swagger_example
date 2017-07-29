package guru.springframework.util;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/** The Constant log. */

public final class SerializationUtils {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(SerializationUtils.class);

    /**
     * Singleton Jackson {@link com.fasterxml.jackson.databind.ObjectMapper} instance
     */
    private static ObjectMapper jacksonJsonSerializer;

    /**
     * Instantiates a new serialization utils.
     */
    private SerializationUtils() {
        //Do nothing. Just to avoid instantiation
    }

    /**
     * Returns an instance of {@link com.fasterxml.jackson.databind.ObjectMapper}. The mapper is singleton and is configured not to fail on empty
     * beans.
     *
     * @return ObjectMapper
     */
    public static synchronized ObjectMapper getJacksonJsonSerializerInstance() {
        if (jacksonJsonSerializer == null) {
            jacksonJsonSerializer = new ObjectMapper();
            jacksonJsonSerializer.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
            jacksonJsonSerializer.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            jacksonJsonSerializer.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            jacksonJsonSerializer.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            jacksonJsonSerializer.registerModules(new JavaTimeModule());
        }
        return jacksonJsonSerializer;
    }

    /**
     * Serializes the given object to JSON string.
     *
     * @param object object to serialize
     * @return JSON
     * @throws IOException if any serialization problem occurs
     */
    public static String serializeToJsonString(Object object) throws IOException {
        try {
            return getJacksonJsonSerializerInstance().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error("serializeToJsonString", e, "The object " + object.toString() + " cannot be serialized", true);
            throw e;
        }
    }

    /**
     * Serializes the given object to JSON and writes the result to the given file.
     *
     * @param object object to serialize
     * @param targetJasonFilePath complete path to the JSON file to save
     * @throws IOException if any serialization problem occurs
     */
    public static void serializeToJsonFile(Object object, String targetJasonFilePath) throws IOException {
        try {
            getJacksonJsonSerializerInstance().writeValue(new File(targetJasonFilePath), object);
        } catch (JsonProcessingException e) {
            log.error("serializeToJsonFile", e);
            throw e;
        }
    }


    /**
     * Deserializes the given JSON string to the specified object type.
     *
     * @param <T> the type of the expected object
     * @param json JSON string to deserialize
     * @param objectType the class of the object expected from the deserialization
     * @return the t
     * @throws IOException if any deserialization error occurs
     */
    public static <T> T deserializeFromJsonString(String json, Class<T> objectType) throws IOException {
        try {
            if (json == null || json.isEmpty()) {
                return null;
            }
            return getJacksonJsonSerializerInstance().readValue(json, objectType);
        } catch (IOException e) {
            log.error("deserializeFromJsonString", e);
            throw e;
        }
    }

    /**
     * Deserializes the given JSON string to the specified object type.
     *
     * @param <T> the type of the expected object
     * @param json JSON string to deserialize
     * @param typeReference the class<T> of the object expected from the deserialization
     * @return the t
     * @throws IOException if any deserialization error occurs
     */
    public static <T> T deserializeFromJsonString(String json, TypeReference<T> typeReference) throws IOException {
        try {
            return getJacksonJsonSerializerInstance().readValue(json, typeReference);
        } catch (IOException e) {
            log.error("deserializeFromJsonString", e);
            throw e;
        }
    }

    /**
     * Deserializes the given JSON file to the specified object type.
     *
     * @param <T> the type of the expected object
     * @param sourceJsonFilePath complete path to the JSON file to read
     * @param objectType the class of the object expected from the deserialization
     * @return the t
     * @throws IOException if any deserialization error occurs
     */
    public static <T> T deserializeFromJsonFile(String sourceJsonFilePath, Class<T> objectType) throws IOException {
        try {
            return getJacksonJsonSerializerInstance().readValue(new File(sourceJsonFilePath), objectType);
        } catch (IOException e) {
            log.error("deserializeFromJsonFile", e);
            throw e;
        }
    }
}
