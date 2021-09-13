package org.acme.vertx.hello;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import org.acme.vertx.hello.impl.HelloWorldImpl;

@VertxGen
public interface HelloWorld {
    static HelloWorld create( Vertx vertx, HelloOptions options ) { return new HelloWorldImpl( vertx, options ); }

    Future<String> sayHello();
    @Fluent
    HelloWorld sayHello( Handler<AsyncResult<String>> resultHandler );
}
