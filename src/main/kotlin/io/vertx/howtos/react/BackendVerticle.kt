package io.vertx.howtos.react

import io.vertx.core.AbstractVerticle
import io.vertx.core.Promise
import io.vertx.core.Vertx
import io.vertx.ext.web.Route
import io.vertx.ext.web.Router
import io.vertx.ext.web.handler.StaticHandler
import mu.KotlinLogging

class BackendVerticle : AbstractVerticle() {

  companion object {
    const val PORT: Int = 8080

    @JvmStatic
    // tag::main[]
    fun main(args: Array<String>) {
      System.setProperty("vertx.logger-delegate-factory-class-name", "io.vertx.core.logging.SLF4JLogDelegateFactory")

      val vertx = Vertx.vertx() // <1>
      vertx.deployVerticle(BackendVerticle()) // <2>
    }
    // end::main[]
  }

  override fun start(startPromise: Promise<Void>) {
    // tag::backend[]
    val router: Router = Router.router(vertx)
    val messageRoute: Route = router.get("/api/message") // <1>
    messageRoute.handler {
      it.response().end("Hello React from Vert.x!") // <2>
    }

    router.get().handler(StaticHandler.create()) // <3>

    vertx
      .createHttpServer()
      .requestHandler(router)
      .listen(PORT) {
        if (it.succeeded()) {
          startPromise.complete()
          KotlinLogging.logger {}.info { "HTTP server started on port $PORT" }
        } else {
          startPromise.fail(it.cause())
        }
      }
    // end::backend[]
  }
}
