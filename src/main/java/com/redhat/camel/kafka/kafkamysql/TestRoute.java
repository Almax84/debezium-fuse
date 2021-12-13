package com.redhat.camel.kafka.kafkamysql;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;;

//@Component
public class TestRoute extends RouteBuilder {


    @Override
    public void configure() throws Exception {
        from("file:/home/davide/lavoro/rai/debezium/fuse-db-mirror/input?noop=true")
                .log("New file: ${header.CamelFileName}");
//        .to("file:/home/davide/lavoro/rai/debezium/fuse-db-mirror/output");


    }
}
