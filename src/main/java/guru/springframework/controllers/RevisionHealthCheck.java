package guru.springframework.controllers;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import com.jcabi.manifests.Manifests;

/**
 * The Class RevisionHealthCheck.
 */
@Component
public class RevisionHealthCheck implements HealthIndicator {

    /** The Constant BUILD_NUMBER. */
    private static final String BUILD_NUMBER = "Build-Number";

    @Override
    public Health health() {
        String release = "";
        if (Manifests.exists(BUILD_NUMBER)) {
            release = Manifests.read(BUILD_NUMBER);
        }
        Health.Builder builder = Health.up().withDetail("Revision", release);
        return builder.build();
    }
}
