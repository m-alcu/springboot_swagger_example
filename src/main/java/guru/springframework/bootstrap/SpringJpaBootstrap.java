package guru.springframework.bootstrap;

import guru.springframework.domain.ClaseInmueble;
import guru.springframework.domain.Product;
import guru.springframework.domain.RangoSuperficie;
import guru.springframework.domain.TipoConstruccion;
import guru.springframework.repositories.ClaseInmuebleRepository;
import guru.springframework.repositories.ProductRepository;
import guru.springframework.repositories.RangoSuperficieRepository;
import guru.springframework.repositories.TipoConstruccionRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class SpringJpaBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private ProductRepository productRepository;
    private ClaseInmuebleRepository claseInmuebleRepositoty;
    private TipoConstruccionRepository tipoConstruccionRepository;
    private RangoSuperficieRepository rangoSuperficieRepository;


    private Logger log = Logger.getLogger(SpringJpaBootstrap.class);

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Autowired
    public void setClaseInmuebleRepositoty(ClaseInmuebleRepository claseInmuebleRepositoty) {
        this.claseInmuebleRepositoty = claseInmuebleRepositoty;
    }

    @Autowired
    public void setTipoConstruccionRepository(TipoConstruccionRepository tipoConstruccionRepository) {
        this.tipoConstruccionRepository = tipoConstruccionRepository;
    }

    @Autowired
    public void setRangoSuperficieRepository(RangoSuperficieRepository rangoSuperficieRepository) {
        this.rangoSuperficieRepository = rangoSuperficieRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        loadProducts();
        loadClaseInmueble();
        loadTipoConstruccion();
        loadRangoSuperficie();
    }

    private void loadProducts() {
        Product shirt = new Product();
        shirt.setDescription("Spring Framework Guru Shirt");
        shirt.setPrice(new BigDecimal("18.95"));
        shirt.setImageUrl("https://springframework.guru/wp-content/uploads/2015/04/spring_framework_guru_shirt-rf412049699c14ba5b68bb1c09182bfa2_8nax2_512.jpg");
        shirt.setProductId("235268845711068308");
        productRepository.save(shirt);

        log.info("Saved Shirt - id: " + shirt.getId());

        Product mug = new Product();
        mug.setDescription("Spring Framework Guru Mug");
        mug.setImageUrl("https://springframework.guru/wp-content/uploads/2015/04/spring_framework_guru_coffee_mug-r11e7694903c348e1a667dfd2f1474d95_x7j54_8byvr_512.jpg");
        mug.setProductId("168639393495335947");
        mug.setPrice(new BigDecimal("11.95"));
        productRepository.save(mug);

        log.info("Saved Mug - id:" + mug.getId());
    }

    private void loadClaseInmueble() {
        ClaseInmueble viviendaProtegida = new ClaseInmueble();
        viviendaProtegida.setClaseInmuebleId("VP");
        viviendaProtegida.setDescription("Vivienda-piso protegida");
        claseInmuebleRepositoty.save(viviendaProtegida);

        ClaseInmueble viviendaPrivada = new ClaseInmueble();
        viviendaPrivada.setClaseInmuebleId("PR");
        viviendaPrivada.setDescription("Vivienda-piso privado");
        claseInmuebleRepositoty.save(viviendaPrivada);
    }

    private void loadTipoConstruccion() {
        TipoConstruccion nueva = new TipoConstruccion();
        nueva.setTipoId("NU");
        nueva.setDescription("Nueva construccion");
        tipoConstruccionRepository.save(nueva);

        TipoConstruccion segundaMano = new TipoConstruccion();
        segundaMano.setTipoId("SM");
        segundaMano.setDescription("Segunda mano");
        tipoConstruccionRepository.save(segundaMano);
    }

    private void loadRangoSuperficie() {
        RangoSuperficie rango50 = new RangoSuperficie();
        rango50.setRangoId("050");
        rango50.setDescription("50 m2");
        rangoSuperficieRepository.save(rango50);

        RangoSuperficie rango80 = new RangoSuperficie();
        rango80.setRangoId("080");
        rango80.setDescription("80 m2");
        rangoSuperficieRepository.save(rango80);

        RangoSuperficie rango100 = new RangoSuperficie();
        rango100.setRangoId("100");
        rango100.setDescription("100 m2");
        rangoSuperficieRepository.save(rango100);

        RangoSuperficie rango150 = new RangoSuperficie();
        rango150.setRangoId("150");
        rango150.setDescription("150 m2");
        rangoSuperficieRepository.save(rango150);
    }



}



