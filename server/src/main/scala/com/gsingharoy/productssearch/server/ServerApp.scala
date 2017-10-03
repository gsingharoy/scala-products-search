package com.gsingharoy.productssearch.server

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.server._
import akka.stream.ActorMaterializer
import com.gsingharoy.productssearch.server.api.handlers.ProductsSearchHandler
import com.gsingharoy.productssearch.server.api.request.ProductsSearchRequest
import com.gsingharoy.productssearch.server.repository.filesystem.FilesystemProductsRepository
import com.gsingharoy.productssearch.server.repository.inmemory.InmemoryProductsRepository
import com.typesafe.config.ConfigFactory
import io.circe.syntax._

object ServerApp extends App with Directives {
  lazy val productsRepository = InmemoryProductsRepository(
    FilesystemProductsRepository(appConfig).allProducts
  )
  lazy val productsSearchHandler = ProductsSearchHandler(productsRepository)
  implicit val system = ActorSystem("actor-system")
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  val appConfig = ConfigFactory.load()


  // TODO: Move this to a new route class SearchRoute
  val routes: Route = {
    path("v1" / "api" / "search") {
      get {
        parameterMap { params =>
          val response = productsSearchHandler.fetchResponse(ProductsSearchRequest.fromQueryParams(params))

          val resp: ResponseEntity = HttpEntity(ContentTypes.`application/json`, response.asJson.noSpaces)
          complete(HttpResponse(StatusCodes.OK, entity = resp))
        }
      }
    }
  }

  Http().bindAndHandle(routes, "localhost", 8002)
}
