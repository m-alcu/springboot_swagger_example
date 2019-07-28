package guru.springframework.controllers;

import guru.springframework.dto.ProductDto;
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

import guru.springframework.domain.Product;
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

    @ApiOperation(tags = "Product", value = "View a list of available products", response = List.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved list"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProductDto>> list(Model model) {
        final List<ProductDto> result = transformer.transform(productService.listAllProducts(), List.class);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(tags = "Product", value = "Search a product with an ID", response = Product.class)
    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDto> showProduct(@PathVariable Integer id, Model model) {
        ProductDto result = transformer.transform(productService.getProductById(id), ProductDto.class);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(tags = "Product", value = "Add a product")
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> saveProduct(@RequestBody ProductDto productDto) {
        Product result = productService.saveProduct(transformer.transform(productDto, Product.class));
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @ApiOperation(tags = "Product", value = "Update a product")
    @RequestMapping(value = "{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> updateProduct(@PathVariable Integer id, @RequestBody ProductDto productDto) {
        Product storedProduct = productService.getProductById(id);
        storedProduct.setDescription(productDto.getDescription());
        storedProduct.setImageUrl(productDto.getImageUrl());
        storedProduct.setPrice(productDto.getPrice());
        return new ResponseEntity<>(productService.saveProduct(storedProduct), HttpStatus.OK);
    }

    @ApiOperation(tags = "Product", value = "Delete a product")
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> delete(@PathVariable Integer id) {
        productService.deleteProduct(id);
        Product product = new Product();
        product.setId(id);
        return new ResponseEntity<>(product, HttpStatus.OK);

    }

}
