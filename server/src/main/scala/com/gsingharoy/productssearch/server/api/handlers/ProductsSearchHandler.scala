package com.gsingharoy.productssearch.server.api.handlers

import com.gsingharoy.productssearch.server.api.request.ProductsSearchRequest
import com.gsingharoy.productssearch.server.api.response.ProductsSearchResponse
import com.gsingharoy.productssearch.server.repository.inmemory.InmemoryProductsRepository

case class ProductsSearchHandler(repository: InmemoryProductsRepository) {
  def fetchResponse(request: ProductsSearchRequest): ProductsSearchResponse =
    ProductsSearchResponse(data = repository.findAll(request.toQuery))

}
