package com.gsingharoy.productsingester.services.fetcher.zalando

import io.circe.Decoder
import io.circe.generic.semiauto

private[zalando] case class RawProductImage(smallUrl: String)

private[zalando] object RawProductImage {
  implicit val decodeRawProductImage: Decoder[RawProductImage] = semiauto.deriveDecoder[RawProductImage]

}
