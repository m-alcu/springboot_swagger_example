package guru.springframework.controllers;

import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jcabi.manifests.Manifests;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.service.ApiInfo;

/**
 * API info Controller.
 */
@RestController
@RequestMapping(value = "/api/v1/")
public class InfoController implements EnvironmentAware {

    /** The Constant BUILD_NUMBER. */
    private static final String BUILD_NUMBER = "Build-Number";

    /** The api info. */
    private ApiInfo apiInfo;

    /** The environment. */
    private Environment environment;

    /**
     * Get API info.
     *
     * @return API Rest information
     */
    @ApiOperation(tags = "API-INFO", value = "Get the api info", notes = "", response = Map.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Success"),
        @ApiResponse(code = 404, message = "Not Found"), @ApiResponse(code = 204, message = "No Content"),
        @ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 500, message = "Failure")
    })
    @RequestMapping(value = "/info", produces = {
        MediaType.APPLICATION_JSON_VALUE
    }, method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> infoGET() {
        Map<String, Object> info = new TreeMap<>();
        String release = "";
        if (Manifests.exists(BUILD_NUMBER)) {
            release = Manifests.read(BUILD_NUMBER);
        }
        info.put("INFO", apiInfo);
        info.put("Release", release);
        info.put("Active Profiles", environment.getActiveProfiles());
        return new ResponseEntity<>(info, HttpStatus.OK);
    }

    /**
     * Sets the api info.
     *
     * @param apiInfo the new api info
     */
    @Autowired
    public void setApiInfo(ApiInfo apiInfo) {
        this.apiInfo = apiInfo;
    }

    @Override
    @Autowired
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
