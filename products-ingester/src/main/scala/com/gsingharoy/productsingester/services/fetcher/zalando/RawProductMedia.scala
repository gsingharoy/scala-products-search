package com.gsingharoy.productsingester.services.fetcher.zalando

import io.circe.Decoder
import io.circe.generic.semiauto

private[zalando] case class RawProductMedia(images: Seq[RawProductImage])

private[zalando] object RawProductMedia {
  implicit val decodeRawProductMedia: Decoder[RawProductMedia] = semiauto.deriveDecoder[RawProductMedia]

}