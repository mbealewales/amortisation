## Improvements/Roadmap


### Fix bug! Installment calculation error

Because of the rounding up/down of pence (I think), the final installment could in practice take the final payment over the 'total due' etc. - this should form part of the installment calculation.

Much as I hate to do so, I've @Ignored two unit tests due to this. I wouldn't normally check in failing tests etc. but time was pressing and I
wanted to get my code to you!

### Improve testing

There are a couple of unit tests that cover happy path and a couple of exception cases.

More exception cases are in the implementation than in the tests........

Given more time, I would write some integration tests for the AmortisationController.

### Simplify the model

The model is split into three tables; this could be reduced as `loan_details` and `loan_schedule` have a 1 to 1 mapping, and so could be represented as one table.

### Improve the way that the calculation is done

The maths basically uses doubles, and a not-great double->formatted String->double to fix to
2 decimal places. Probably better to use a `BigDecimal` etc.?


### Document the Rest API

Define e.g. an OpenAPI/Swagger specification to document the REST API.

### Add to Rest API

Although not part of requirements, a natural next step would be to expand the Rest API
to allow for amending and deleting schedules etc.

### Support Idempotency

If the REST API requirements are expanded, it would be wise to support idempotency.

### Performance Improvements

Depending on future use cases, the entities and custom named queries could be cached.

### Database Improvements

* Support other datasources e.g. Postgres, Oracle, MySQL.
* Hand-craft the tables/indexes etc. with SQL for advanced performance.
* Use a named-query and ORDER BY for a faster fetch of ordered installments, rather than sorting?

### A UI

A React/Angular/Vue3 application providing a 'nice' UI for a customer or agent.

### Authentication/Authorization

Secure the application using OAuth2 etc.

### Postman scripts

As a nice to have to aid development, it would be good to include POSMAN etc. scripts for testing the Rest API.

### CI/CD

Provide docker, k8s config, CI/CD pipelines for deployment to cloud environments etc.
