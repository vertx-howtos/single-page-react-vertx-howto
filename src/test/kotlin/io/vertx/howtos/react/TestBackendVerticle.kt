package io.vertx.howtos.react

import io.vertx.core.Vertx
import io.vertx.junit5.VertxExtension
import io.vertx.junit5.VertxTestContext
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(VertxExtension::class)
class TestBackendVerticle {

  @BeforeEach
  fun deploy_verticle(vertx: Vertx, testContext: VertxTestContext) {
    vertx.deployVerticle(BackendVerticle(), testContext.succeeding<String> { testContext.completeNow() })
  }

  @Test
  fun verticle_deployed(@Suppress("UNUSED_PARAMETER") vertx: Vertx, testContext: VertxTestContext) {
    testContext.completeNow()
  }
}
