package com.gsingharoy.productssearch.server.models

import io.circe.Encoder
import io.circe.generic.semiauto


case class Product(name: String,
                   price: Double = 0.0D,
                   brand: String,
                   imageUrl: Option[String] = None)

object Product{

  implicit val encodeProduct: Encoder[Product] = semiauto.deriveEncoder[Product]

}
