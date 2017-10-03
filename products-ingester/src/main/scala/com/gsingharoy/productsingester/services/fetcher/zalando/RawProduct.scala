package com.gsingharoy.productsingester.services.fetcher.zalando

import com.gsingharoy.productsingester.models.Product
import io.circe.Decoder
import io.circe.generic.semiauto

private[zalando] case class RawProduct(id: String,
                                       modelId: String,
                                       name: String,
                                       brand: RawBrand,
                                       units: Seq[RawProductUnit],
                                       media: Option[RawProductMedia]) {
  def toProduct: Product = Product(name = name, brand = brand.name, price = price, imageUrl = imageUrl)

  private lazy val price = units.headOption match {
    case Some(rawProductUnit) => rawProductUnit.price.value
    case _ => 0.0
  }

  private lazy val imageUrl: Option[String] = media match {
    case Some(media) => media.images.headOption match {
      case Some(image) => Some(image.smallUrl)
      case _ => None
    }
    case _ => None
  }
}

private[zalando] object RawProduct {
  implicit val decodeRawProduct: Decoder[RawProduct] = semiauto.deriveDecoder[RawProduct]

}
