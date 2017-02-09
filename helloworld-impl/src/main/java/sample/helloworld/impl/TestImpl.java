package sample.helloworld.impl;

import akka.Done;
import akka.NotUsed;
import akka.stream.javadsl.Flow;
import com.lightbend.lagom.javadsl.api.ServiceCall;

import javax.inject.Inject;

import sample.helloworld.api.GreetingMessage;
import sample.helloworld.api.HelloService;

import static java.util.concurrent.CompletableFuture.completedFuture;

public class TestImpl implements Test {

  //#inject-service
  private final HelloService helloService;

  @Inject public TestImpl(HelloService helloService) {
    this.helloService = helloService;
  }
  //#inject-service

  public ServiceCall<NotUsed, NotUsed> audit() {
    //#subscribe-to-topic
    helloService.greetingsTopic().subscribe() // <-- you get back a Subscriber instance
        .atLeastOnce(
            Flow.fromFunction(this::doSomethingWithTheMessage));
    //#subscribe-to-topic
    return name -> completedFuture(NotUsed.getInstance());
  }

  private Done doSomethingWithTheMessage(GreetingMessage message) {
    System.out.println("Message: " + message);
    return Done.getInstance();
  }

}