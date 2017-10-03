package com.gsingharoy.productsingester.services.fetcher.zalando

import io.circe.Decoder
import io.circe.generic.semiauto

private[zalando] case class RawBrand(name: String)

private[zalando] object RawBrand {
  implicit val decodeRawBrand: Decoder[RawBrand] = semiauto.deriveDecoder[RawBrand]
}
