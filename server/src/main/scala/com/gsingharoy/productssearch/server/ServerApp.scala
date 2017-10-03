package com.gsingharoy.productssearch.server

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.server._
import akka.stream.ActorMaterializer

object ServerApp extends App with Directives {
  implicit val system = ActorSystem("actor-system")
  implicit val materializer: ActorMaterializer = ActorMaterializer()

  val routes: Route = {
    path("v1"/ "api" / "search") {
      get {
        parameterMap { params =>
          val resp: ResponseEntity = HttpEntity(ContentTypes.`application/json`, "{\"my_key\":\"my_value\"}")
          complete(HttpResponse(StatusCodes.OK, entity = resp))
        }
      }
    }
  }

  Http().bindAndHandle(routes, "0.0.0.0", 8002)
}
