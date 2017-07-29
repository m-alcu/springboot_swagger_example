package guru.springframework.controllers;

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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "onlinestore", description = "Operations pertaining to products in Online Store")
public class ProductController {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @ApiOperation(value = "View a list of available products", response = Iterable.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved list"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<Product>> list(Model model) {
        return new ResponseEntity<>(productService.listAllProducts(), HttpStatus.OK);
    }

    @ApiOperation(value = "Search a product with an ID", response = Product.class)
    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> showProduct(@PathVariable Integer id, Model model) {
        return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Add a product")
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
        product = productService.saveProduct(product);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update a product")
    @RequestMapping(value = "{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> updateProduct(@PathVariable Integer id, @RequestBody Product product) {
        Product storedProduct = productService.getProductById(id);
        storedProduct.setDescription(product.getDescription());
        storedProduct.setImageUrl(product.getImageUrl());
        storedProduct.setPrice(product.getPrice());
        return new ResponseEntity<>(productService.saveProduct(storedProduct), HttpStatus.OK);
    }

    @ApiOperation(value = "Delete a product")
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> delete(@PathVariable Integer id) {
        productService.deleteProduct(id);
        Product product = new Product();
        product.setId(id);
        return new ResponseEntity<>(product, HttpStatus.OK);

    }

}
