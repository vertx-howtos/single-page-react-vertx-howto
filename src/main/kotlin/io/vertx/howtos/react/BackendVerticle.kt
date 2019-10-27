package io.vertx.howtos.react

import io.vertx.core.AbstractVerticle
import io.vertx.core.Promise
import io.vertx.core.Vertx
import io.vertx.core.logging.SLF4JLogDelegateFactory
import io.vertx.ext.web.Route
import io.vertx.ext.web.Router
import io.vertx.ext.web.handler.StaticHandler
import mu.KotlinLogging

class BackendVerticle : AbstractVerticle() {

  companion object {
    const val PORT: Int = 8080

    @JvmStatic
    fun main(args: Array<String>) {
      System.setProperty(
        "vertx.logger-delegate-factory-class-name",
        SLF4JLogDelegateFactory::class.qualifiedName ?: throw ClassNotFoundException()
      )

      val vertx = Vertx.vertx()
      vertx.deployVerticle(BackendVerticle())
    }
  }

  override fun start(startPromise: Promise<Void>) {
    val router: Router = Router.router(vertx)
    val messageRoute: Route = router.get("/api/message")
    messageRoute.handler {
      it.response().end("Hello React from Vert.x!")
    }

    router.get().handler(StaticHandler.create())

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
  }
}
