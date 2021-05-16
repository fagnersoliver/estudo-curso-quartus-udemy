package com.github.fagner.ifood.mp;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

import org.eclipse.microprofile.reactive.messaging.Incoming;

import io.smallrye.reactive.messaging.annotations.Blocking;
import io.vertx.mutiny.pgclient.PgPool;

@ApplicationScoped
public class RestauranteCadastrado {

	// Salva no banco de dados
	@Inject
	PgPool pgPool;

	@Incoming("restaurantes")
	@Blocking
	public void receberRestaurante(String json) {

		// Verfifica erro ao fazer a inserção no banco de dados.
		Jsonb create = JsonbBuilder.create();
		Restaurante restaurante = create.fromJson(json, Restaurante.class);
		restaurante.persist(pgPool);

		 System.out.println(json);
	}
}
