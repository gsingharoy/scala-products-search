package com.gsingharoy.productssearch.server.api.request

import com.gsingharoy.productssearch.server.repository.ProductsQuery


case class ProductsSearchRequest(q: Option[String] = None,
                                 c: Option[String] = None,
                                 page: Option[Int] = None,
                                 sort: Option[String] = None,
                                 direction: Option[String] = None) {
  def toQuery: ProductsQuery = ProductsQuery(
    fullText = q,
    fullTextType = c,
    page = page.getOrElse(1),
    pageSize = 10,
    sort = Some(applicableSort),
    sortOrder = direction.map(_.toLowerCase)
  )

  private lazy val applicableSort = sort.map(_.toLowerCase) match {
    case Some(c) if Seq("brand","name").contains(c) => c
    case _ => "price"
  }
}

object ProductsSearchRequest {
  def fromQueryParams(queryParams: Map[String, String]): ProductsSearchRequest = {
    ProductsSearchRequest(
      q = queryParams.get("q"),
      c = queryParams.get("c"),
      page = queryParams.get("page").map(_.toInt),
      sort = queryParams.get("sort"),
      direction = queryParams.get("direction")
    )
  }
}
