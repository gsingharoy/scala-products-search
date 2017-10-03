package com.gsingharoy.productsingester.services.fetcher.zalando

import io.circe.parser
import org.scalatest.{GivenWhenThen, Matchers, WordSpec}

import scala.io.Source


class RawProductsResponseJSONDecoderSpec extends WordSpec with Matchers with GivenWhenThen {
  "JSON Decoder" should {
    "decode the response from zalando api to RawProductsResponse" in {
      Given("A valid response returned from external products api")
      val productsResponseJSON = Source.fromURL(getClass.getClassLoader.getResource("zalando-api-response.json")).mkString.stripMargin

      When("I decode the response")
      val unmarshalledRawProductsResponse = parser.parse(productsResponseJSON) match {
        case Left(failure) => None
        case Right(json) => json.as[RawProductsResponse].toOption
      }

      Then("the right attributes are copied to RawProductsResponse")
      val expectecRawProductResponse = RawProductsResponse(
        totalElements = Some(49035),
        size = Some(2),
        page = Some(1),
        totalPages = Some(24518),
        content = Seq(
          RawProduct(
            id = "BU311C02R-802",
            modelId = "BU311C02R",
            name = "Boots - garda suede",
            brand = RawBrand("Buffalo"),
            units = Seq(RawProductUnit(RawProductPrice(72.24)), RawProductUnit(RawProductPrice(72.24)))),
          RawProduct(
            id = "HU312A00O-C11",
            modelId = "HU312A00O",
            name = "SLIMMER STADIL - Trainers - castle rock/ribbon red/bril blue",
            brand = RawBrand("Hummel"),
            units = Seq(RawProductUnit(RawProductPrice(54.99D))))
        ))

      unmarshalledRawProductsResponse should equal(Some(expectecRawProductResponse))
    }
  }
}
