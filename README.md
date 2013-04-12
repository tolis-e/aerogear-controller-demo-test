# AeroGear Controller Demo Functional Test
This project contains the functional and acceptance tests for the [AeroGear Controller Demo](https://github.com/aerogear/aerogear-controller-demo) project.

The [Arquillian](http://arquillian.org/) testing platform is used to enable the testing automation. Arquillian integrates transparently with the testing framework which is JUnit in this case.

## Functional Test Content
The AeroGear Controller Demo Functional Test defines the three core aspects needed for the execution of an [Arquillian](http://arquillian.org/) test case:

- container — the runtime environment
- deployment — the process of dispatching an artifact to a container
- archive — a packaged assembly of code, configuration and resources

The container's configuration resides in the [Arquillian XML](https://github.com/tolis-e/aerogear-controller-demo-ftest/blob/master/src/test/resources/arquillian.xml) configuration file while the deployment and the archive are defined in the [Deployments](https://github.com/tolis-e/aerogear-controller-demo-ftest/blob/master/src/test/java/org/jboss/aerogear/controller/demo/test/Deployments.java) file.

The test case is dispatched to the container's environment through coordination with ShrinkWrap, which is used to declaratively define a custom Java EE archive that encapsulates the test class and its dependent resources. Arquillian packages the ShrinkWrap defined archive at runtime and deploys it to the target container. It then negotiates the execution of the test methods and captures the test results using remote communication with the server. Finally, Arquillian undeploys the test archive.

The [POM](https://github.com/tolis-e/aerogear-controller-demo-ftest/blob/master/pom.xml) configuration file contains two profiles:

* arq-jbossas-managed-7 — managed container 
* arq-jbossas-remote — remote container

By default the arq-jbossas-managed-7 (managed container) profile is active. An Arquillian managed container is a remote container whose lifecycle is managed by Arquillian. The specific profile is also configured to download and unpack the JBoss Application Server 7 distribution zip from the Maven Central repository.

## Functional Test Execution
Before executing the functional test, ensure that the AeroGear Controller Demo project is already built and the corresponding WAR file exists inside the target folder.

The execution of the functional test is done through maven:

    mvn test

## Documentation

* [Arquillian Guides](http://arquillian.org/guides/)
