package com.github.fagner.ifood.cadastro;

import java.util.HashMap;
import java.util.Map;

import org.testcontainers.containers.PostgreSQLContainer;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;

public class CadastroTestLifecycleManager implements QuarkusTestResourceLifecycleManager {

    public static final PostgreSQLContainer<?> POSTGRES = new PostgreSQLContainer<>("postgres:10.2");

    @Override
    public Map<String, String> start() {
        POSTGRES.start();
        Map<String, String> propriedades = new HashMap<String, String>();

        //Banco de dados
        propriedades.put("quarkus.datasource.jdbc.url", "jdbc:tracing:postgresql://localhost:5432/postgres");
        propriedades.put("quarkus.datasource.username", "cadastro");
        propriedades.put("quarkus.datasource.password", "cadastro");
        propriedades.put("quarkus.datasource.jdbc.driver","io.opentracing.contrib.jdbc.TracingDriver");
        propriedades.put("quarkus.hibernate-orm.database.generation","drop-and-create");
        propriedades.put("quarkus.hibernate-orm.dialect", "org.hibernate.dialect.PostgreSQL10Dialect");

        return propriedades;
    }

    @Override
    public void stop() {
        if (POSTGRES != null && POSTGRES.isRunning()) {
            POSTGRES.stop();
        }
    }
}
