package guru.springframework.services.unit;

import guru.springframework.dao.ProductDao;
import guru.springframework.entity.ProductEntity;
import guru.springframework.services.impl.ProductServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;



public class ProductServiceImplMockTest {

    private ProductServiceImpl productServiceImpl;
    @Mock
    private ProductDao productDao;
    @Mock
    private ProductEntity product;
    @Before
    public void setupMock() {
        MockitoAnnotations.initMocks(this);
        productServiceImpl=new ProductServiceImpl();
    }
    @Test
    public void shouldReturnProduct_whenFindByIdIsCalled() throws Exception {
        // Arrange
        when(productDao.findById(5)).thenReturn(product);
        // Act
        ProductEntity retrievedProduct = productServiceImpl.findById(5);
        // Assert
        assertThat(retrievedProduct, is(equalTo(product)));

    }
    @Test
    public void shouldReturnProduct_whenUpdateProductIsCalled() throws Exception {
        // Arrange
        when(productDao.update(product)).thenReturn(1);
        // Act
        Integer result = productServiceImpl.update(product);
        // Assert
        assertThat(result, is(equalTo(1)));
    }
    @Test
    public void shouldCallDeleteMethodOfProductRepository_whenDeleteProductIsCalled() throws Exception {
        // Arrange
        doNothing().when(productDao).delete(5);
        // Act
        productServiceImpl.delete(5);
        // Assert
        verify(productDao, times(1)).delete(5);
    }
}