package guru.springframework.services.unit;

import guru.springframework.dao.ProductDao;
import guru.springframework.entity.ProductEntity;
import guru.springframework.services.impl.ProductServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.never;


@RunWith(MockitoJUnitRunner.class)
public class ProductServiceImplSpyTest {
    @Spy
    private ProductServiceImpl prodServiceSpy;
    @Mock
    private ProductDao productDao;
    @Mock
    private ProductEntity product;

    @Test(expected=NullPointerException.class)
    public void shouldThrowNullPointerException_whenGetProductByIdIsCalledWithoutContext() throws Exception {
        //Act
        ProductEntity retrievedProduct = prodServiceSpy.findById(5);
        //Assert
        assertThat(retrievedProduct, is(equalTo(product)));
    }

    public void shouldThrowNullPointerException_whenSaveProductIsCalledWithoutContext() throws Exception {
        //Arrange
        Mockito.doReturn(product).when(productDao).update(product);
        //Act
        Integer result = prodServiceSpy.update(product);
        //Assert
        assertThat(result, is(equalTo(1)));
    }

    @Test
    public void shouldVerifyThatGetProductByIdIsCalled() throws Exception {
        //Arrange
        Mockito.doReturn(product).when(prodServiceSpy).findById(5);
        //Act
        ProductEntity retrievedProduct = prodServiceSpy.findById(5);
        //Assert
        Mockito.verify(prodServiceSpy).findById(5);
    }
    @Test
    public void shouldVerifyThatSaveProductIsNotCalled() throws Exception {
        //Arrange
        Mockito.doReturn(product).when(prodServiceSpy).findById(5);
        //Act
        ProductEntity retrievedProduct = prodServiceSpy.findById(5);
        //Assert
        Mockito.verify(prodServiceSpy,never()).update(product);
    }
}