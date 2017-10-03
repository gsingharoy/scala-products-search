package com.gsingharoy.productsingester.repository.filesystem

import java.io.{BufferedWriter, File, FileWriter}

import com.gsingharoy.productsingester.models.Product
import com.gsingharoy.productsingester.repository.ProductsRepository
import com.typesafe.config.Config
import io.circe.syntax._


/**
  * This repository stores to the filesystem
  * A similar one extending the ProductsRepository trait can be built when moving to SQL database
  */
case class FilesystemProductsRepository(config: Config) extends ProductsRepository {
  def store(products: Seq[Product]): Unit = {
    val file = new File(config.getString("output.file"))
    val bw = new BufferedWriter(new FileWriter(file))
    products.map { p =>
      bw.write(s"${p.asJson.noSpaces}\n")
    }
    bw.close()
  }
}
