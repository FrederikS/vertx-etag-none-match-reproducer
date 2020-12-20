package io.vertx.reproducer;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.ext.web.Router;

public class HttpServerVerticle extends AbstractVerticle {

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        final Router router = Router.router(vertx);

        router.route("/check/e-tag").handler(ctx -> {
            ctx.etag("1234");

            if (ctx.isFresh()) {
                ctx.response().setStatusCode(304).end("Not Modified");
            } else {
                ctx.response().setStatusCode(200).end("OK");
            }
        });

        vertx.createHttpServer()
             .requestHandler(router)
             .listen(8080)
             .onFailure(startPromise::fail)
             .onSuccess(server -> startPromise.complete());
    }

}
