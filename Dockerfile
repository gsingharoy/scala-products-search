FROM hseeberger/scala-sbt
# For a Alpine Linux version

ADD . /products-search

RUN cd /products-search/products-ingester && sbt run

WORKDIR /products-search/server

CMD sbt run

EXPOSE 8002
