package guru.springframework.controllers;

import guru.springframework.mapping.SimpleTransformer;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseController {

    @Autowired
    protected SimpleTransformer transformer;
}
