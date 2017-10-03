package com.gsingharoy.productsingester.services.fetcher

import com.gsingharoy.productsingester.models.Product
import io.circe.Decoder
import io.circe.generic.semiauto

private[fetcher] case class RawProduct(id: String,
                      modelId: String,
                      name: String,
                      brand: RawBrand) {
  def toProduct: Product = {
    Product(name = name, brand = brand.name, price = 0.0)
  }
}
private[fetcher] object RawProduct{
  implicit val decodeRawProduct: Decoder[RawProduct] = semiauto.deriveDecoder[RawProduct]

}
