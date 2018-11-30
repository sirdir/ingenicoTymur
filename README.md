# ingenico

run test and see report `mvn clean test allure:serve`
to run headless just add -Dheadless=true `mvn clean test -Dheadless="true" allure:serve`


P.S. just reading throe doc find typo in code of example. At [Request example](https://epayments-api.developer-ingenico.com/s2sapi/v1/en_US/java/hostedcheckouts/create.html#hostedcheckouts-create-request-example) if you select in dropdown **Create hosted checkout basic EUR** in code of example you will still have `amountOfMoney.setCurrencyCode("USD");` instead of **EUR**
  