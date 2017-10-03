package com.gsingharoy.productsingester.services.fetcher.zalando

import io.circe.Decoder
import io.circe.generic.semiauto


private[zalando] case class RawProductPrice(value: Double)

private[zalando] object RawProductPrice {
  implicit val decodeRawProductPrice: Decoder[RawProductPrice] = semiauto.deriveDecoder[RawProductPrice]

}
