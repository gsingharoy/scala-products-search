package com.gsingharoy.productssearch.server.repository.inmemory

import com.gsingharoy.productssearch.server.models.Product
import com.gsingharoy.productssearch.server.repository.{ProductsQuery, ProductsRepository}

/**
  * This is an in memory implementation of the Products Repository.
  * All the queries are done in memory for the list of products as an input
  * A similar RDBMSProductsRepository can be made for the SQL one
  **/
case class InmemoryProductsRepository(val allProducts: Seq[Product]) extends ProductsRepository {
  def findAll(query: ProductsQuery): Seq[Product] = query match {
    case ProductsQuery(None, None, page, pageSize) => filterProducts(allProducts, (page - 1) * pageSize, pageSize)
    case ProductsQuery(Some(fullText), fullTextType, page, pageSize) => filterProducts(
      filteredProductsFromText(fullText.toLowerCase, fullTextType.map(_.toLowerCase)),
      (page - 1) * pageSize,
      pageSize)
    case _ => Seq()
  }

  private def filterProducts(products: Seq[Product], offset: Int, size: Int): Seq[Product] = {
    if (offset < 0 || offset > products.size) {
      return Seq()
    }
    products.splitAt(offset)._2.splitAt(size)._1
  }

  private def filteredProductsFromText(fullText: String, fullTextType: Option[String]): Seq[Product] = fullTextType match {
    case Some("product") => allProducts.filter(_.name.toLowerCase.contains(fullText))
    case Some("brand") => allProducts.filter(_.brand.toLowerCase.contains(fullText))
    case _ => allProducts.filter( p => p.brand.toLowerCase.contains(fullText) || p.name.toLowerCase.contains(fullText))
  }
}
