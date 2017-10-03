package com.gsingharoy.productssearch.server.models

import io.circe.{Encoder, Decoder}
import io.circe.generic.semiauto


case class Product(name: String,
                   price: Double = 0.0D,
                   brand: String,
                   imageUrl: Option[String] = None)

object Product{

  implicit val encodeProduct: Encoder[Product] = semiauto.deriveEncoder[Product]
  implicit val decodeProduct: Decoder[Product] = semiauto.deriveDecoder[Product]

}
