# Server

This is the API server application Which returns searches on the products.

**NOTE** *Please note that the products are dumped in a json for now and not yet migrated to a SQL database.*

## API Documentation

`GET /v1/api/search`

Parameter | Required | Description
--- | --- | ---
q | false | full text query to match products by any columns (product name, brand). In case of empty q, all results are return
c | false | Matches only a particular column. Examples: `product`, `brand`
page | false | The page number. Default `1`
sort | false | The way of sorting the products. Options: `price`, `name`, `brand`. By default all sorting order is asc. If not specified, sorts with price
direction | false | The direction of sorting. Options: `desc`, `asc`

## Running

The project is in `sbt` and can be ran by simply
```
sbt run
```
Then try the endpoint `http://localhost:8002/v1/api/search`

## Testing

```
sbt test
```
