package com.hamidsultanzadeh.accountservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@Configuration
@EnableCassandraRepositories
public class CassandraConfiguration extends AbstractCassandraConfiguration {

    @Value("${spring.cloud.cassandra.keyspace.name}")
    private String keyspaceName;

    @Value("${spring.cloud.cassandra.username}")
    private String username;

    @Value("${spring.cloud.cassandra.password}")
    private String password;

    @Value("${spring.cloud.cassandra.contact.point}")
    private String point;

    @Value("${spring.cloud.cassandra.port}")
    private Integer port;

    @Override
    protected String getKeyspaceName() {
        return keyspaceName;
    }

    @Override
    protected String getContactPoints() {
        return point;
    }

    @Override
    protected int getPort() {
        return port;
    }

    @Override
    public SchemaAction getSchemaAction() {
        return SchemaAction.CREATE_IF_NOT_EXISTS;
    }

    @Override
    public String[] getEntityBasePackages() {
        return new String[] {"com.hamidsultanzadeh.accountservice"};
    }

    @Override
    public CassandraClusterFactoryBean cluster() {
        CassandraClusterFactoryBean cas = new CassandraClusterFactoryBean();
        cas.setUsername(username);
        cas.setPassword(password);
        return cas;
    }
}
