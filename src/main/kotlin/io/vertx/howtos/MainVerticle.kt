package io.vertx.howtos

import io.vertx.core.AbstractVerticle
import io.vertx.core.Promise
import io.vertx.core.logging.SLF4JLogDelegateFactory
import io.vertx.howtos.react.BackendVerticle

class MainVerticle: AbstractVerticle() {
  override fun start(startPromise: Promise<Void>) {
    System.setProperty(
      "vertx.logger-delegate-factory-class-name",
      SLF4JLogDelegateFactory::class.qualifiedName ?: throw ClassNotFoundException()
    )
    vertx.deployVerticle(BackendVerticle()) {
      if (it.succeeded()) {
        startPromise.complete()
      } else {
        startPromise.fail(it.cause())
      }
    }
  }
}
