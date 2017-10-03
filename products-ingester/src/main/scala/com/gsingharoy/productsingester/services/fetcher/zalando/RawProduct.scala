package com.gsingharoy.productsingester.services.fetcher.zalando

import com.gsingharoy.productsingester.models.Product
import io.circe.Decoder
import io.circe.generic.semiauto

private[zalando] case class RawProduct(id: String,
                      modelId: String,
                      name: String,
                      brand: RawBrand) {
  def toProduct: Product = {
    Product(name = name, brand = brand.name, price = 0.0)
  }
}
private[zalando] object RawProduct{
  implicit val decodeRawProduct: Decoder[RawProduct] = semiauto.deriveDecoder[RawProduct]

}
