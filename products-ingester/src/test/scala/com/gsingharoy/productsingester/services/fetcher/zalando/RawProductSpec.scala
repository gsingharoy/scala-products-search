package com.gsingharoy.productsingester.services.fetcher.zalando

import com.gsingharoy.productsingester.models.Product
import org.scalatest.{Matchers, WordSpec}


class RawProductSpec extends WordSpec with Matchers{
  "RawProduct#toProduct" should {
    sealed trait Fixtures {
      val rawProduct = RawProduct(
        id = "BU311C02R-802",
        modelId = "BU311C02R",
        name = "Boots - garda suede",
        brand = RawBrand("Buffalo"),
        units = Seq(RawProductUnit(RawProductPrice(72.24D)), RawProductUnit(RawProductPrice(81.24D))),
        media = Some(RawProductMedia(Seq(RawProductImage("example.url.com")))))
    }
    "convert into an appropiate product and have the price of the first unit if units are present" in new Fixtures {
      val expectedProduct = Product(name = "Boots - garda suede", brand = "Buffalo", price = 72.24D, imageUrl = Some("example.url.com"))

      rawProduct.toProduct should equal(expectedProduct)
    }

    "convert into an appropiate product and have the price 0.0 if units are not present" in new Fixtures {
      val expectedProduct = Product(name = "Boots - garda suede", brand = "Buffalo", price = 0.0D, imageUrl = Some("example.url.com"))
      val rawProductWithoutUnits = rawProduct.copy(units = Seq())

      rawProductWithoutUnits.toProduct should equal(expectedProduct)
    }

    "convert into an appropiate product and have the imageUrl None if it is not present" in new Fixtures {
      val expectedProduct = Product(name = "Boots - garda suede", brand = "Buffalo", price = 72.24D, imageUrl = None)
      val rawProductWithoutUnits = rawProduct.copy(media = None)

      rawProductWithoutUnits.toProduct should equal(expectedProduct)
    }
  }
}
