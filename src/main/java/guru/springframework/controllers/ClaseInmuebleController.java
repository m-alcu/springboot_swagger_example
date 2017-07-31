package guru.springframework.controllers;

import guru.springframework.domain.ClaseInmueble;
import guru.springframework.domain.Product;
import guru.springframework.services.ClaseInmuebleService;
import guru.springframework.services.ProductService;
import guru.springframework.services.TipoConstruccionService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class ClaseInmuebleController {

    @Autowired
    private ClaseInmuebleService claseInmuebleService;

    @ApiOperation(tags = "Inmuebles", value = "View a list of available inmuables", response = Iterable.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved list"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @RequestMapping(value = "/clases-inmuebles", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<ClaseInmueble>> list(Model model) {
        return new ResponseEntity<>(claseInmuebleService.listAll(), HttpStatus.OK);
    }

}
