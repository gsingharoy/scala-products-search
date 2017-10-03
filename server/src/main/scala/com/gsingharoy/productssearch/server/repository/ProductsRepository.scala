package com.gsingharoy.productssearch.server.repository

import com.gsingharoy.productssearch.server.models.Product

trait ProductsRepository {
  def findAll(query: ProductsQuery): Seq[Product]
}
