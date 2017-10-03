# Products Ingester

This is the products ingester application which ingests products into the database after retrieving it from the api endpoint of [zalando](https://github.com/zalando/shop-api-documentation/wiki/Articles).

**NOTE** *Please note that the products are dumped in a json for now and not yet migrated to a SQL database. Also only 600 articles are queried and the search parameters are hardcoded in `IngesterApp`*

## Running

The project is in `sbt` and can be ran by simply
```
sbt run
```
You can check the created json dump in the root folder, `../.products-list.json`

## Testing

```
sbt test
```
