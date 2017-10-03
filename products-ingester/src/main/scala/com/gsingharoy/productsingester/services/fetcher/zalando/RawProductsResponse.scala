package com.gsingharoy.productsingester.services.fetcher.zalando

import io.circe.Decoder
import io.circe.generic.semiauto


private[zalando] case class RawProductsResponse(content: Seq[RawProduct],
                            totalElements: Option[Int],
                            totalPages: Option[Int],
                            page: Option[Int],
                            size: Option[Int]) {
  lazy val products = content.map(_.toProduct)
}

private[zalando] object RawProductsResponse{
  implicit val decodeProductsResponse: Decoder[RawProductsResponse] = semiauto.deriveDecoder[RawProductsResponse]
}
