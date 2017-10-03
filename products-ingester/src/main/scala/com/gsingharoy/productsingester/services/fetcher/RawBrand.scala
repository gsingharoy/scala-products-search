package com.gsingharoy.productsingester.services.fetcher

import io.circe.Decoder
import io.circe.generic.semiauto

private[fetcher] case class RawBrand(name: String)

private[fetcher] object RawBrand {
  implicit val decodeRawBrand: Decoder[RawBrand] = semiauto.deriveDecoder[RawBrand]
}
