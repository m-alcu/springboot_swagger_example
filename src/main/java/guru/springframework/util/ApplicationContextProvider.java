package guru.springframework.util;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Wrapper to always return a reference to the Spring Application Context from within non-Spring enabled beans.
 */
@Component
public class ApplicationContextProvider implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    /**
     * This method is called from within the ApplicationContext once it is done starting up, it will stick a reference to itself into this bean.
     * 
     * @param context a reference to the ApplicationContext.
     */
    @Override
    public void setApplicationContext(ApplicationContext context) {
        applicationContext = context;
    }

    /**
     * Gets the reference to the current Spring ApplicationContext instance
     * 
     * @return the reference to the ApplicationContext
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * @see BeanFactory#getBean(java.lang.String)
     * @param beanName
     * @return
     */
    public static Object getBean(String beanName) {
        return applicationContext.getBean(beanName);
    }

    /**
     * @see BeanFactory#getBean(java.lang.Class)
     * @param beanType
     * @return
     */
    public static <T> T getBean(Class<T> beanType) {
        return applicationContext.getBean(beanType);
    }
}
