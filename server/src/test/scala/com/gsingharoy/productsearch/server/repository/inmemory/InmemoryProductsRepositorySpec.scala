package com.gsingharoy.productsearch.server.repository.inmemory

import com.gsingharoy.productssearch.server.models.Product
import com.gsingharoy.productssearch.server.repository.ProductsQuery
import com.gsingharoy.productssearch.server.repository.inmemory.InmemoryProductsRepository
import org.scalatest.{GivenWhenThen, Matchers, WordSpec}

class InmemoryProductsRepositorySpec extends WordSpec with GivenWhenThen with Matchers {
  "InmemoryRepository#findAll" should {
    "Return all products with respecting page size and page no." in {
      Given("a collection of products")
      val products = Seq(
        Product(name = "product1", brand = "brand1"),
        Product(name = "product2", brand = "brand2"),
        Product(name = "product3", brand = "brand3"),
        Product(name = "product4", brand = "brand4"),
        Product(name = "product5", brand = "brand5")
      )
      And("an inmemory repository containing the products")
      val repository = InmemoryProductsRepository(products)

      And("a query requesting first 2 products")
      Then("the repository should return first 2 products")
      repository.findAll(ProductsQuery(page=1, pageSize = 2)) should equal(Seq(
        Product(name = "product1", brand = "brand1"),
        Product(name = "product2", brand = "brand2")
      ))

      When("a query requesting page 2 of size 2")
      Then("the repository should return the appropiate products")
      repository.findAll(ProductsQuery(page=2, pageSize = 2)) should equal(Seq(
        Product(name = "product3", brand = "brand3"),
        Product(name = "product4", brand = "brand4")
      ))

      When("a query requesting page 3 of size 2")
      Then("the repository should return the appropiate products")
      repository.findAll(ProductsQuery(page=3, pageSize = 2)) should equal(Seq(
        Product(name = "product5", brand = "brand5")
      ))
    }
  }
}
