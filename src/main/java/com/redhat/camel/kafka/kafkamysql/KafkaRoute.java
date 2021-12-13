package com.redhat.camel.kafka.kafkamysql;

import com.google.gson.Gson;
import com.redhat.camel.kafka.kafkamysql.model.CustomerPayload;
import org.apache.camel.builder.RouteBuilder;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class KafkaRoute extends RouteBuilder {
    @Autowired
    DataSource dataSource;


    @Override
    public void configure() throws Exception {
        from("kafka:dbserver1.inventory.customers")
                .process(exchange -> {
                    String body = exchange.getIn().getBody(String.class);


                    if(body==null){
                        System.out.println("Empty body received!");
                    }else {
                        JSONObject obj = new JSONObject(body);
                        JSONObject payloadJsonObj = obj.getJSONObject("payload");

                        Gson gson = new Gson();
                        CustomerPayload customerPayloadCustomer = gson.fromJson(payloadJsonObj.toString(),
                                CustomerPayload.class);

                        exchange.getIn().setBody(customerPayloadCustomer);
                        System.out.println("Incoming customer is: " + customerPayloadCustomer.toString());
                        if (customerPayloadCustomer.getOp().equals("d")) {
                            exchange.setProperty("id", customerPayloadCustomer.getBefore().getId());

                        } else {


                            exchange.setProperty("first_name", customerPayloadCustomer.getAfter().getFirstName());
                            exchange.setProperty("last_name", customerPayloadCustomer.getAfter().getLastName());
                            exchange.setProperty("email", customerPayloadCustomer.getAfter().getEmail());
                            exchange.setProperty("id", customerPayloadCustomer.getAfter().getId());
                        }
                    }

                })
                .to("direct:customers-route");

        from("direct:customers-route")
                .choice()
                    .when(exchange -> exchange.getIn().getBody() != null && exchange.getIn().getBody(CustomerPayload.class).getOp().equals("u"))
                        .log("UPDATING CUSTOMER! id=${exchangeProperty.id}")
                        .to("sql:UPDATE inventory.customers" +
                                " SET first_name=:#${exchangeProperty.first_name}," +
                                " last_name=:#${exchangeProperty.last_name}," +
                                " email=:#${exchangeProperty.email}" +
                                " WHERE id=:#${exchangeProperty.id};")
                    .when(exchange -> exchange.getIn().getBody() != null && exchange.getIn().getBody(CustomerPayload.class).getOp().equals("c"))
                        .log("CREATING NEW CUSTOMER! id=${exchangeProperty.id}")
                        .to("sql:REPLACE INTO inventory.customers VALUES (:#${exchangeProperty.id}," +
                                " :#${exchangeProperty.first_name}," +
                                " :#${exchangeProperty.last_name}, :#${exchangeProperty.email});")
                    .when(exchange -> exchange.getIn().getBody() != null && exchange.getIn().getBody(CustomerPayload.class).getOp().equals("d"))
                        .log("DELETING CUSTOMER: jid=${exchangeProperty.id}")
                        .to("sql:DELETE FROM inventory.customers WHERE id=:#${exchangeProperty.id}");



    }
}
