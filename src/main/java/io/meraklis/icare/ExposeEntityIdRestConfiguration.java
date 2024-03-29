package io.meraklis.icare;

import io.meraklis.icare.applications.PatientApplication;
import io.meraklis.icare.documents.PatientDocument;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class ExposeEntityIdRestConfiguration implements RepositoryRestConfigurer {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        config.exposeIdsFor(PatientApplication.class);
        config.exposeIdsFor(PatientDocument.class);
    }
}
