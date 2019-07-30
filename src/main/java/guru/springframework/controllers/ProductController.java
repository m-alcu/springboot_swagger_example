package guru.springframework.controllers;

import guru.springframework.dto.ProductDto;
import guru.springframework.entity.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import guru.springframework.services.ProductService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;

@RestController
@RequestMapping(value = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController extends BaseController {

    @Autowired
    private ProductService productService;

    @ApiOperation(tags = "ProductEntity", value = "View a list of available products", response = List.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved list"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProductDto>> list(Model model) {
        final List<ProductDto> result = transformer.transform(productService.findAll(), ProductDto.class);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(tags = "ProductEntity", value = "Search a product with an ID", response = ProductEntity.class)
    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDto> findById(@PathVariable Integer id, Model model) {
        ProductDto result = transformer.transform(productService.findById(id), ProductDto.class);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(tags = "ProductEntity", value = "Add a product")
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> insert(@RequestBody ProductDto productDto) {
        int result = productService.insert(transformer.transform(productDto, ProductEntity.class));
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @ApiOperation(tags = "ProductEntity", value = "Update a product")
    @RequestMapping(value = "{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> update(@PathVariable Integer id, @RequestBody ProductDto productDto) {
        ProductEntity storedProduct = productService.findById(id);
        storedProduct.setDescription(productDto.getDescription());
        storedProduct.setImageUrl(productDto.getImageUrl());
        storedProduct.setPrice(productDto.getPrice());
        return new ResponseEntity<>(productService.update(storedProduct), HttpStatus.OK);
    }

    @ApiOperation(tags = "ProductEntity", value = "Delete a product")
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductEntity> delete(@PathVariable Integer id) {
        productService.delete(id);
        ProductEntity product = new ProductEntity();
        product.setId(id);
        return new ResponseEntity<>(product, HttpStatus.OK);

    }

}
