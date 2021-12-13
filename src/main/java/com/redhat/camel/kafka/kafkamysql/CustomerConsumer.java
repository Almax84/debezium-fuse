package com.redhat.camel.kafka.kafkamysql;

import com.google.gson.Gson;
import com.redhat.camel.kafka.kafkamysql.model.CustomerPayload;
import org.apache.camel.ConsumerTemplate;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.json.JSONObject;

public class CustomerConsumer implements Processor {

    ConsumerTemplate consumer;

    public void setConsumer(ConsumerTemplate consumer) {
        this.consumer = consumer;
    }


    @Override
    public void process(Exchange exchange) throws Exception {

        String body = exchange.getIn().getBody(String.class);

        JSONObject obj = new JSONObject(body);
        JSONObject payloadJsonObj = obj.getJSONObject("payload");


        Gson gson = new Gson();
        CustomerPayload customerPayloadCustomer = gson.fromJson(payloadJsonObj.toString(),
                CustomerPayload.class);

        exchange.getIn().setBody(customerPayloadCustomer);



    }
}
