package sample.helloworld.impl;

import akka.NotUsed;
import com.lightbend.lagom.javadsl.api.Descriptor;
import com.lightbend.lagom.javadsl.api.Service;
import com.lightbend.lagom.javadsl.api.ServiceCall;

import static com.lightbend.lagom.javadsl.api.Service.named;
import static com.lightbend.lagom.javadsl.api.Service.pathCall;

public interface Test extends Service {

    @Override
    default Descriptor descriptor() {
        return named("test").withCalls(

        ).withAutoAcl(true);
    }

}
