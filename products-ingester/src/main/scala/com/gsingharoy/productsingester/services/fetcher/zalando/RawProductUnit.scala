package com.gsingharoy.productsingester.services.fetcher.zalando

import io.circe.Decoder
import io.circe.generic.semiauto


private[zalando] case class RawProductUnit(price: RawProductPrice)

private[zalando] object RawProductUnit {
  implicit val decodeRawProductUnit: Decoder[RawProductUnit] = semiauto.deriveDecoder[RawProductUnit]

}
