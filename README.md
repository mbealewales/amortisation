## Amortisation REST Service, Mike Beale, 15/04/24

### Building the service

The easiest way to build the service is to run `./mvnw package` from the command line at the
top level of the repository. This will create a jar file `amortisation-0.0.1.SNAPSHOT.jar` in the `target` directory.

I tested building/running using Java 17 and Java 11.

### Running the service

To run the service after building, type `java -jar ./target/amortisation-0.0.1.SNAPSHOT.jar`

Tomcat is configured to run on port 9090: to change, edit the file `application.properties`,
re-build and re-run.

### Testing the service

There are a few unit tests that are run when a build is done.

Manual testing can also be done using `curl` or POSTMAN.

The following Methods and URLS and request bodies are supported:

```POST /amortisation/schedules
Content-Type: application/json
{
   "assetCost": 25000,
   "deposit": 5000,
   "yearlyInterestRate": 7.5,
   "monthlyRepayments": 12 
}

GET /amortisation/schedules

[
    {
        "id": 1,
        "loanDetails": {
            "id": 1,
            "assetCost": 25000.0,
            "deposit": 5000.0,
            "yearlyInterestRate": 7.5,
            "monthlyRepayments": 12,
            "balloonPayment": 0.0
        },
        "monthlyRepaymentAmount": 2585.56,
        "totalInterestDue": 10846.27,
        "totalPaymentsDue": 31026.72
    }
]

GET /amortisation/schedules/1
{
    "first": [
        {
            "id": 1,
            "period": 1,
            "payment": 2585.56,
            "principal": 1085.56,
            "interest": 1500.0,
            "balance": 18914.44,
            "loanSchedule": {
                "id": 1,
                "loanDetails": {
                    "id": 1,
                    "assetCost": 25000.0,
                    "deposit": 5000.0,
                    "yearlyInterestRate": 7.5,
                    "monthlyRepayments": 12,
                    "balloonPayment": 0.0
                },
                "monthlyRepaymentAmount": 2585.56,
                "totalInterestDue": 10846.27,
                "totalPaymentsDue": 31026.72
            }
        },
        {
            "id": 2,
            "period": 2,
            "payment": 2585.56,
            "principal": 1166.98,
            "interest": 1418.58,
            "balance": 17747.46,
            "loanSchedule": {
                etc.
                etc.
    },
    second:{
         "id": 1,
        "loanDetails": {
            "id": 1,
            "assetCost": 25000.0,
            "deposit": 5000.0,
            "yearlyInterestRate": 7.5,
            "monthlyRepayments": 12,
            "balloonPayment": 0.0
        },
        "monthlyRepaymentAmount": 2585.56,
        "totalInterestDue": 10846.27,
        "totalPaymentsDue": 31026.72
    }

```