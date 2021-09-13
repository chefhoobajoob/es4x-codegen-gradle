package org.acme.vertx.hello.impl;

import io.vertx.core.*;
import org.acme.vertx.hello.HelloOptions;
import org.acme.vertx.hello.HelloWorld;
import org.acme.vertx.hello.Languages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloWorldImpl implements HelloWorld {
    private final static String ArabicHello = "أهلا";
    private final static String EnglishHello = "hello";
    private final static String HindiHello = "नमस्ते";
    private final static String PortugueseHello = "olá";
    private final static String SpanishHello = "hola";
    private final static String SwahiliHello = "jambo";

    private static final Logger _logger = LoggerFactory.getLogger( HelloWorld.class );
    private Languages _language = Languages.ENGLISH;
    private Long _delay = 0L;
    private Vertx _vertx;

    public HelloWorldImpl( Vertx theVertx, HelloOptions theOptions ) {
        _vertx = theVertx;
        _language = theOptions.getLanguage();
        _delay = theOptions.getThinkTimeMillis();
        _logger.info( "HelloWorld created with language ({}) and think time ({}ms)", _language.name(), _delay );
    }

    @Override
    public HelloWorld sayHello( Handler<AsyncResult<String>> resultHandler )
    {
        if ( _delay == 0L ) {
            _logger.debug( "no think time, saying hello immediately" );
            resultHandler.handle( Future.succeededFuture( hello() ) );
        } else {
            _logger.debug( "thinking for {}ms before saying hello", _delay );
            _vertx.setTimer( _delay, tid -> resultHandler.handle( Future.succeededFuture( hello() ) ) );
        }
        return this;
    }

    @Override
    public Future<String> sayHello() {
        Promise<String> promise = Promise.promise();
        sayHello( hello -> promise.complete( hello.result() ) );
        return promise.future();
    }

    private String hello() {
        switch( _language ) {
            case ARABIC:
                return ArabicHello;
            case ENGLISH:
                return EnglishHello;
            case HINDI:
                return HindiHello;
            case PORTUGUESE:
                return PortugueseHello;
            case SPANISH:
                return SpanishHello;
            default:
                return SwahiliHello;
        }
    }
}
