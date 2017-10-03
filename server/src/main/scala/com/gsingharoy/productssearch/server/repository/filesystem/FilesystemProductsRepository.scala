package com.gsingharoy.productssearch.server.repository.filesystem

import java.io.File

import com.typesafe.config.Config

import scala.collection.mutable.ListBuffer
import scala.io.Source
import com.gsingharoy.productssearch.server.models.Product
import io.circe.parser



case class FilesystemProductsRepository(config: Config) {
  /**
    * Fetches list of products from a file location
    * */
  lazy val allProducts: Seq[Product] = {
    val source = Source.fromFile(file.getAbsolutePath)
    val records = ListBuffer[Option[Product]]()

    for (line <- source.getLines()) {
      records.append(parser.parse(line) match {
        case Left(failure) => None
        case Right(json) => json.as[Product].toOption
      })
    }
    records.toList.flatten
  }

  private lazy val file = new File(config.getString("products.input.file"))
}
