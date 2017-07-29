package guru.springframework.mapping;

/**
 * Generic mapper.
 *
 * @param <I> Input
 * @param <O> Type output
 */
@FunctionalInterface
public interface Mapper<I, O> {

    /**
     * Map input <I> into a type <O>.
     *
     * @param input object to map
     * @param outputClass type of object which will be converted
     * @return object of type <O> mapped
     */
    O map(I input, Class<O> outputClass);
}
