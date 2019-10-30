package io.vertx.howtos

import io.vertx.core.AbstractVerticle
import io.vertx.core.Promise
import io.vertx.core.logging.SLF4JLogDelegateFactory
import io.vertx.howtos.react.BackendVerticle

class MainVerticle : AbstractVerticle() {
  companion object {
    private const val LOGGER_DELEGATE_FACTORY_PROPERTY = "vertx.logger-delegate-factory-class-name"
  }

  override fun start(startPromise: Promise<Void>) {
    if (System.getProperty(LOGGER_DELEGATE_FACTORY_PROPERTY) == null) {
      System.setProperty(
        LOGGER_DELEGATE_FACTORY_PROPERTY,
        SLF4JLogDelegateFactory::class.qualifiedName ?: throw ClassNotFoundException()
      )
    }

    vertx.deployVerticle(BackendVerticle()) {
      if (it.succeeded()) {
        startPromise.complete()
      } else {
        startPromise.fail(it.cause())
      }
    }
  }
}
