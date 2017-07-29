package guru.springframework.mapping;

import java.util.List;

import guru.springframework.util.pagination.Paged;



/**
 * Transformer operations interface.
 */
public interface Transformer {

    /**
     * It transforms class <I> in class <O>.
     *
     * @param <I> the generic type
     * @param <O> the generic type
     * @param input object to transform
     * @param outputClass type of object in will be transformed
     * @return <O> object
     */
    <I, O> O transform(final I input, final Class<O> outputClass);

    /**
     * It transforms a list into another type of list.
     *
     * @param <I> the generic type
     * @param <O> the generic type
     * @param inputs list to transform
     * @param outputClass type of object in will the list will be transformed
     * @return <O> list
     */
    <I, O> List<O> transform(final List<I> inputs, final Class<O> outputClass);

    /**
     * It transform a page into another type of page.
     *
     * @param <I> the generic type
     * @param <O> the generic type
     * @param page pagination object
     * @param outputClass type of object in will the pagination will be transformed
     * @return <O> page
     */
    <I, O> Paged<O> transform(final Paged<I> page, final Class<O> outputClass);
}
