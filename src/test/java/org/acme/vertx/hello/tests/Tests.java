package org.acme.vertx.hello.tests;

import io.vertx.core.Vertx;
import io.vertx.junit5.VertxExtension;
import io.vertx.junit5.VertxTestContext;
import org.acme.vertx.hello.HelloOptions;
import org.acme.vertx.hello.HelloWorld;
import org.acme.vertx.hello.Languages;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(VertxExtension.class)
public class Tests {
    @Test
    public void saysHello( Vertx theVertx, VertxTestContext theContext ) {
        HelloWorld world = HelloWorld.create( theVertx, new HelloOptions().setLanguage( Languages.ARABIC ).setThinkTimeMillis( 1000L ) );
        world.sayHello().onComplete( hello -> {
            theContext.verify( () -> {
                assertThat( hello.succeeded() ).isTrue();
                assertThat( hello.result() ).isEqualTo( "أهلا" );
            });
            theContext.completeNow();
        });
    }
}
