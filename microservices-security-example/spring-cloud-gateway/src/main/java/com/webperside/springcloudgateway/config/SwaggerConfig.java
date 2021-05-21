package com.webperside.springcloudgateway.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Primary
@Configuration
@EnableSwagger2
//@Profile("swagger")
public class SwaggerConfig implements SwaggerResourcesProvider {

//    private final ZuulProperties zuulProperties;
//
//    public SwaggerConfig(ZuulProperties zuulProperties) {
//        this.zuulProperties = zuulProperties;
//    }

    @Override
    public List<SwaggerResource> get() {
        List<SwaggerResource> resources = new ArrayList<>();
//        zuulProperties.getRoutes().values().forEach(r -> {
//            System.out.println(r.getPath());
//            String link = r.getPath().replace("**", "v2/api-docs");
//            if (!link.contains("/eureka-service")) {
//                resources.add(swaggerResource(r.getServiceId(), link, "2.0"));
//            }
//        });

        resources.sort(Comparator.comparing(SwaggerResource::getName));

        return resources;
    }

    private SwaggerResource swaggerResource(String name, String location, String version) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion(version);
        return swaggerResource;
    }

}
