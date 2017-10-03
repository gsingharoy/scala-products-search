package com.gsingharoy.productsingester.models

import io.circe.Encoder
import io.circe.generic.semiauto

case class Product(name: String,
                   price: Double,
                   brand: String,
                   imageUrl: Option[String] = None)

object Product{

  implicit val encodeProduct: Encoder[Product] = semiauto.deriveEncoder[Product]

}
