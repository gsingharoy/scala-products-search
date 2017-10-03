package com.gsingharoy.productssearch.server.repository


case class ProductsQuery(fullText: Option[String] = None,
                         fullTextType: Option[String] = None,
                         page: Int = 1,
                         pageSize: Int = 10,
                         sort: Option[String] = None,
                         sortOrder: Option[String] = None)
