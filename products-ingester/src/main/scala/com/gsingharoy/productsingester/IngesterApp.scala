package com.gsingharoy.productsingester

import com.gsingharoy.productsingester.repository.ProductsRepository
import com.gsingharoy.productsingester.services.fetcher.zalando.ZalandoProductsFetcher
import com.typesafe.config.ConfigFactory
import com.gsingharoy.productsingester.models.Product

import scala.concurrent.duration._
import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global

object IngesterApp extends App {
  val ec = scala.concurrent.ExecutionContext.Implicits.global
  val appConfig = ConfigFactory.load()

  val productsFuture1 = ZalandoProductsFetcher("shoes", 1, pageSize = 200)(ec).fetch
  val productsFuture2 = ZalandoProductsFetcher("shoes", 2, pageSize = 200)(ec).fetch
  val productsFuture3 = ZalandoProductsFetcher("shoes", 3, pageSize = 200)(ec).fetch

  val productsFuture: Future[Seq[Product]] = for {
    products1 <- productsFuture1
    products2 <- productsFuture2
    products3 <- productsFuture3
  } yield (products1 ++ products2 ++ products3)

  println("Fetching products")
  val products: Seq[Product] = Await.result(productsFuture, 10 second)
  println(s"Finished fetching ${products.length} products")
  val productsRepository = ProductsRepository(appConfig)
  println("Write products to repository")
  productsRepository.store(products)
  println(s"Finished storing ${products.length} products to repository")
}
