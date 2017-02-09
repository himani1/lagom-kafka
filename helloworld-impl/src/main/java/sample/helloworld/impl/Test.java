package sample.helloworld.impl;

import static com.lightbend.lagom.javadsl.api.Service.named;
import static com.lightbend.lagom.javadsl.api.Service.pathCall;

import akka.Done;
import akka.NotUsed;
import com.lightbend.lagom.javadsl.api.Descriptor;
import com.lightbend.lagom.javadsl.api.Service;
import com.lightbend.lagom.javadsl.api.ServiceCall;
import com.lightbend.lagom.javadsl.api.broker.Topic;

import static com.lightbend.lagom.javadsl.api.Service.*;

public interface Test extends Service {

  @Override
  default Descriptor descriptor() {
    // @formatter:off
    return named("test").withCalls(
        pathCall("/api/audit",  this::audit)
    ).withAutoAcl(true);
    // @formatter:on
  }
  // The topic handle
  ServiceCall<NotUsed, NotUsed> audit();
}
