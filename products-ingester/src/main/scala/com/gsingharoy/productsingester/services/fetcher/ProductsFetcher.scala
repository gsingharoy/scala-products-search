package com.gsingharoy.productsingester.services.fetcher

import com.gsingharoy.productsingester.models.Product

import scala.concurrent.{Future}

trait ProductsFetcher {
  def fetch: Future[Seq[Product]]
}
