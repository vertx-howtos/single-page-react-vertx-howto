package io.vertx.howtos.react;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.StaticHandler;

public class BackendVerticle extends AbstractVerticle {

  @Override
  public void start() throws Exception {
    // tag::backend[]
    Router router = Router.router(vertx);

    router.get("/greet/:name").handler(rc -> { // <1>
      rc.response().end(String.format("Hello %s!", rc.pathParam("name"))); // <2>
    });

    router.get().handler(StaticHandler.create()); // <3>

    vertx.createHttpServer()
      .requestHandler(router)
      .listen(8080);
    // end::backend[]
  }

  // tag::main[]
  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx(); // <1>
    vertx.deployVerticle(new BackendVerticle()); // <2>
  }
  // end::main[]
}
