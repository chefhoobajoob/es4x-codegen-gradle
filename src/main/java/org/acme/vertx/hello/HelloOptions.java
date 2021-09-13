package org.acme.vertx.hello;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

import java.util.Objects;

@DataObject(generateConverter = true)
public class HelloOptions {
    private Languages language;
    private Long thinkTimeMillis;

    public HelloOptions() {
        init();
    }

    public HelloOptions( HelloOptions other ) {
        other.check();
        language = other.language;
        thinkTimeMillis = other.thinkTimeMillis;
    }

    public HelloOptions( JsonObject json ) {
        init();
        HelloOptionsConverter.fromJson( json, this );
        check();
    }

    public JsonObject toJson() {
        check();
        JsonObject json = new JsonObject();
        HelloOptionsConverter.toJson( this, json );
        return json;
    }

    private void init() {
        language = Languages.ENGLISH;
        thinkTimeMillis = 250L;
    }

    private void check() {
    }

    public Languages getLanguage() {
        return language;
    }
    public HelloOptions setLanguage( Languages language ) {
        if ( language == null ) {
            throw new IllegalArgumentException( "language must be non-null" );
        }
        this.language = language;
        return this;
    }

    public Long getThinkTimeMillis() {
        return thinkTimeMillis;
    }
    public HelloOptions setThinkTimeMillis( Long thinkTimeMillis ) {
        if ( thinkTimeMillis < 0L ) {
            throw new IllegalArgumentException( "thinkTimeMillis must be >= 0" );
        }
        this.thinkTimeMillis = thinkTimeMillis;
        return this;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( !( o instanceof HelloOptions ) ) return false;
        HelloOptions that = (HelloOptions) o;
        return language == that.language && Objects.equals( thinkTimeMillis, that.thinkTimeMillis );
    }

    @Override
    public int hashCode() {
        return Objects.hash( language, thinkTimeMillis );
    }

    public HelloOptions copy() {
        return new HelloOptions( this );
    }
}
