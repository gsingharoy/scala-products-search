package com.gsingharoy.productsingester.repository

import java.io.{BufferedWriter, File, FileWriter}

import com.typesafe.config.Config
import io.circe.syntax._
import com.gsingharoy.productsingester.models.Product


case class ProductsRepository(config: Config) {
  def store(products: Seq[Product]): Unit = {
    val file = new File(config.getString("output.file"))
    val bw = new BufferedWriter(new FileWriter(file))
    products.map{ p =>
      bw.write(s"${p.asJson.noSpaces}\n")
    }
    bw.close()
  }
}
