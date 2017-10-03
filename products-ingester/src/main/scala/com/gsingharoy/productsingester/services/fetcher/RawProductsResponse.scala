package com.gsingharoy.productsingester.services.fetcher

import io.circe.Decoder
import io.circe.generic.semiauto


private[fetcher] case class RawProductsResponse(content: Seq[RawProduct],
                            totalElements: Option[Int],
                            totalPages: Option[Int],
                            page: Option[Int],
                            size: Option[Int]) {
  lazy val products = content.map(_.toProduct)
}

private[fetcher] object RawProductsResponse{
  implicit val decodeProductsResponse: Decoder[RawProductsResponse] = semiauto.deriveDecoder[RawProductsResponse]
}
