package com.gsingharoy.productssearch.server.api.response

import io.circe.Encoder
import io.circe.generic.semiauto
import com.gsingharoy.productssearch.server.models.Product


case class ProductsSearchResponse(data: Seq[Product])

object ProductsSearchResponse {
  implicit val encodeProductsSearchResponse: Encoder[ProductsSearchResponse] = semiauto.deriveEncoder[ProductsSearchResponse]

}
