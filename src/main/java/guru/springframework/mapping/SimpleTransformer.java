package guru.springframework.mapping;

import javax.annotation.PostConstruct;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

/**
 * Simple transformer without mapper configurations.
 */
@Component
public class SimpleTransformer extends AbstractTransformer {

    /**
     * It starts the model mapper.
     */
    @PostConstruct
    public void init() {
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }
}
