package com.gsingharoy.productsingester.services.fetcher.zalando

import com.gsingharoy.productsingester.services.fetcher.ProductsFetcher
import io.circe.parser

import scala.concurrent.{ExecutionContext, Future}
import scalaj.http.Http
import com.gsingharoy.productsingester.models.Product

case class ZalandoProductsFetcher(productType: String, page: Int, pageSize: Int = 10)(implicit ec: ExecutionContext)
  extends ProductsFetcher {
  private lazy val baseUrl = "https://api.zalando.com/articles"

  def fetch: Future[Seq[Product]] = Future {
    val strJSON: String = Http(s"$baseUrl/?fullText=$productType&page=$page&pageSize=$pageSize").asString.body
    parser.parse(strJSON) match {
      case Left(failure) => Seq()
      case Right(json) => json.as[RawProductsResponse].toOption match {
        case Some(productsResponse) => productsResponse.products
        case _ => Seq()
      }
    }
  }
}

