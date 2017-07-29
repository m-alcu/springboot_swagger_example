package guru.springframework.mapping;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import guru.springframework.util.pagination.Paged;



/**
 * Transformer implementation generic.
 */
public abstract class AbstractTransformer implements Transformer {

    /** The model mapper. */
    protected ModelMapper modelMapper;

    /*
     * (non-Javadoc)
     *
     * @see com.hotelbeds.packagebackofficeservice.core.mapping.Transformer#transform(java.lang.Object, java.lang.Class)
     */
    @Override
    public <I, O> O transform(final I input, final Class<O> outputClass) {
        return transform(input, outputClass, (i, c) -> map(i, c));
    }

    private <I, O> O map(final I input, final Class<O> outputClass) {
        return modelMapper.map(input, outputClass);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.hotelbeds.packagebackofficeservice.core.mapping.Transformer#transform(java.util.List, java.lang.Class)
     */
    @Override
    public <I, O> List<O> transform(final List<I> input, final Class<O> outputClass) {
        return input.stream().map(i -> map(i, outputClass)).collect(Collectors.toList());
    }

    /*
     * (non-Javadoc)
     *
     * @see com.hotelbeds.packagebackofficeservice.core.mapping.Transformer#transform(com.hotelbeds.packagebackofficeservice.util.pagination.Paged,
     * java.lang.Class)
     */
    @Override
    public <I, O> Paged<O> transform(Paged<I> page, Class<O> outputClass) {
        final Paged<O> pageOutput = new Paged<>();

        final List<I> inputList = page.getList();
        final List<O> outputList = transform(inputList, outputClass);

        pageOutput.setOffset(page.getOffset());
        pageOutput.setLimit(page.getLimit());
        pageOutput.setTotalSize(page.getTotalSize());
        pageOutput.setList(outputList);

        return pageOutput;
    }

    /**
     * Transform.
     *
     * @param <I> the generic type
     * @param <O> the generic type
     * @param input the input
     * @param outputClass the output class
     * @param mapper the mapper
     * @return the o
     */
    private <I, O> O transform(final I input, final Class<O> outputClass, Mapper<I, O> mapper) {
        return mapper.map(input, outputClass);
    }


}
