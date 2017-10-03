FROM scala:latest

ADD ./app/data/. /go/bin/app/data
ADD . /go/src/github.com/wellhive/api-server
RUN cd /go/src/github.com/wellhive/api-server && go get && go build

ENTRYPOINT "/go/bin/api-server"

EXPOSE 8080

FROM hseeberger/scala-sbt
# For a Alpine Linux version, comment above and uncomment below:
# FROM 1science/sbt
ADD . /app

RUN cd /
