package sample.helloworld.impl;

import akka.Done;
import akka.stream.javadsl.Flow;
import javax.inject.Inject;

import com.google.inject.Singleton;
import sample.helloworld.api.GreetingMessage;
import sample.helloworld.api.HelloService;

@Singleton
public class TestImpl {

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