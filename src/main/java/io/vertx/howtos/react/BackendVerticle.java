package io.vertx.howtos.react;

import io.vertx.core.Future;
import io.vertx.core.VerticleBase;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.StaticHandler;

public class BackendVerticle extends VerticleBase {

  @Override
  public Future<?> start() throws Exception {
    // tag::backend[]
    Router router = Router.router(vertx);
    Route messageRoute = router.get("/api/message"); // <1>
    messageRoute.handler(rc -> {
      rc.response().end("Hello React from Vert.x!"); // <2>
    });

    router.get().handler(StaticHandler.create()); // <3>

    return vertx.createHttpServer()
      .requestHandler(router)
      .listen(8080);
    // end::backend[]
  }

  // tag::main[]
  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx(); // <1>
    vertx.deployVerticle(new BackendVerticle()).await(); // <2>
    System.out.println("Verticle started!");
  }
  // end::main[]
}
