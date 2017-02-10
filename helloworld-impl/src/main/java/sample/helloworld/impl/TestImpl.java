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

    private final HelloService helloService;

    @Inject
    public TestImpl(HelloService helloService) {
        this.helloService = helloService;
        helloService.greetingsTopic().subscribe()
                .atLeastOnce(Flow.fromFunction(this::doSomethingWithTheMessage));
    }

    private Done doSomethingWithTheMessage(GreetingMessage message) {
        System.out.println("Message: " + message);
        return Done.getInstance();
    }

}