package com.gsingharoy.productssearch.server.api.request

import com.gsingharoy.productssearch.server.repository.ProductsQuery
import org.scalatest.{Matchers, WordSpec}


class ProductsSearchRequestSpec extends WordSpec with Matchers {
  "ProductsSearchRequest.fromQueryParams" should {
    "return a valid ProductsSearchRequest object from the query params" in {
      val queryParams = Map[String, String](
        "q" -> "full text",
        "c" -> "category",
        "page" -> "2",
        "sort" -> "sorting category",
        "direction" -> "asc"
      )

      ProductsSearchRequest.fromQueryParams(queryParams) should equal(
        ProductsSearchRequest(q = Some("full text"),
          c = Some("category"),
          page = Some(2),
          sort = Some("sorting category"),
          direction = Some("asc"))
      )
    }
  }

  "ProductsSearchRequest.toQuery" should {
    "return a valid ProductsQuery" in {
      val request = ProductsSearchRequest(q = Some("full text"),
        c = Some("category"),
        page = Some(2),
        sort = Some("sorting category"),
        direction = Some("ASC"))

      request.toQuery should equal(ProductsQuery(
        fullText = Some("full text"),
        fullTextType = Some("category"),
        page = 2,
        pageSize = 10,
        sort = Some("price"),
        sortOrder = Some("asc")
      ))
    }
  }
}
