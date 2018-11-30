# ingenico

***Important*** in case you use openjdk-8(mostly linux users), please update to 191v because of [surefire bug](https://stackoverflow.com/questions/53010200/maven-surefire-could-not-find-forkedbooter-class)

Just run test and see report `mvn clean test allure:serve`

To run headless just add -Dheadless=true `mvn clean test -Dheadless="true" allure:serve` but simply you can just install on Jenkins [plugin Xvfb](https://wiki.jenkins.io/display/JENKINS/Xvfb+Plugin) and use it on precondition to jenkins job. 

P.S. just reading throe doc find typo in code of example. At [Request example](https://epayments-api.developer-ingenico.com/s2sapi/v1/en_US/java/hostedcheckouts/create.html#hostedcheckouts-create-request-example) if you select in dropdown **Create hosted checkout basic EUR** in code of example you will still have `amountOfMoney.setCurrencyCode("USD");` instead of **EUR**
  