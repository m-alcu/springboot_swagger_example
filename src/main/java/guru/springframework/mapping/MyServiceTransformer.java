package guru.springframework.mapping;

import javax.annotation.PostConstruct;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

/**
 * Transformer with Entity <-> DTO mapping configurations.
 */
@Component
public class MyServiceTransformer extends AbstractTransformer {

    /**
     * It starts the model mapper and it configuration.
     */
    @PostConstruct
    public void init() {

        modelMapper = new ModelMapper();

        modelMapper.getConfiguration().setPropertyCondition(context -> context.getMapping().getLastDestinationProperty() != null);

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
    }

}
