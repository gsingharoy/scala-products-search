package com.gsingharoy.productsingester.repository

import com.gsingharoy.productsingester.models.Product


trait ProductsRepository{
  def store(products: Seq[Product]): Unit
}
