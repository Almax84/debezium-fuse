package com.redhat.camel.kafka.kafkamysql;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import com.redhat.camel.kafka.kafkamysql.model.CustomerPayload;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class KafkaMysqlApplicationTests {

	@Test
	void contextLoads() throws JSONException, JsonProcessingException {


		String jsonString = "{\"schema\":{\"type\":\"struct\",\"fields\":[{\"type\":\"struct\",\"fields\":[{\"type\":\"int32\",\"optional\":false,\"field\":\"id\"},{\"type\":\"string\",\"optional\":false,\"field\":\"first_name\"},{\"type\":\"string\",\"optional\":false,\"field\":\"last_name\"},{\"type\":\"string\",\"optional\":false,\"field\":\"email\"}],\"optional\":true,\"name\":\"dbserver1.inventory.customers.Value\",\"field\":\"before\"},{\"type\":\"struct\",\"fields\":[{\"type\":\"int32\",\"optional\":false,\"field\":\"id\"},{\"type\":\"string\",\"optional\":false,\"field\":\"first_name\"},{\"type\":\"string\",\"optional\":false,\"field\":\"last_name\"},{\"type\":\"string\",\"optional\":false,\"field\":\"email\"}],\"optional\":true,\"name\":\"dbserver1.inventory.customers.Value\",\"field\":\"after\"},{\"type\":\"struct\",\"fields\":[{\"type\":\"string\",\"optional\":false,\"field\":\"version\"},{\"type\":\"string\",\"optional\":false,\"field\":\"connector\"},{\"type\":\"string\",\"optional\":false,\"field\":\"name\"},{\"type\":\"int64\",\"optional\":false,\"field\":\"ts_ms\"},{\"type\":\"string\",\"optional\":true,\"name\":\"io.debezium.data.Enum\",\"version\":1,\"parameters\":{\"allowed\":\"true,last,false\"},\"default\":\"false\",\"field\":\"snapshot\"},{\"type\":\"string\",\"optional\":false,\"field\":\"db\"},{\"type\":\"string\",\"optional\":true,\"field\":\"sequence\"},{\"type\":\"string\",\"optional\":true,\"field\":\"table\"},{\"type\":\"int64\",\"optional\":false,\"field\":\"server_id\"},{\"type\":\"string\",\"optional\":true,\"field\":\"gtid\"},{\"type\":\"string\",\"optional\":false,\"field\":\"file\"},{\"type\":\"int64\",\"optional\":false,\"field\":\"pos\"},{\"type\":\"int32\",\"optional\":false,\"field\":\"row\"},{\"type\":\"int64\",\"optional\":true,\"field\":\"thread\"},{\"type\":\"string\",\"optional\":true,\"field\":\"query\"}],\"optional\":false,\"name\":\"io.debezium.connector.mysql.Source\",\"field\":\"source\"},{\"type\":\"string\",\"optional\":false,\"field\":\"op\"},{\"type\":\"int64\",\"optional\":true,\"field\":\"ts_ms\"},{\"type\":\"struct\",\"fields\":[{\"type\":\"string\",\"optional\":false,\"field\":\"id\"},{\"type\":\"int64\",\"optional\":false,\"field\":\"total_order\"},{\"type\":\"int64\",\"optional\":false,\"field\":\"data_collection_order\"}],\"optional\":true,\"field\":\"transaction\"}],\"optional\":false,\"name\":\"dbserver1.inventory.customers.Envelope\"},\"payload\":{\"before\":{\"id\":1004,\"first_name\":\"Anne\",\"last_name\":\"Kretchmar\",\"email\":\"annek@noanswer.org\"},\"after\":{\"id\":1004,\"first_name\":\"Anne Marie\",\"last_name\":\"Kretchmar\",\"email\":\"annek@noanswer.org\"},\"source\":{\"version\":\"1.8.0.Alpha2\",\"connector\":\"mysql\",\"name\":\"dbserver1\",\"ts_ms\":1637748235000,\"snapshot\":\"false\",\"db\":\"inventory\",\"sequence\":null,\"table\":\"customers\",\"server_id\":223344,\"gtid\":null,\"file\":\"mysql-bin.000003\",\"pos\":400,\"row\":0,\"thread\":null,\"query\":null},\"op\":\"u\",\"ts_ms\":1637748236050,\"transaction\":null}}";

		JSONObject obj = new JSONObject(jsonString);
		JSONObject payloadJsonObj = obj.getJSONObject("payload");
		System.out.println(payloadJsonObj);

		Gson gson = new Gson();
		CustomerPayload customerPayloadCustomer = gson.fromJson(payloadJsonObj.toString(),
				CustomerPayload.class);

		Assertions.assertTrue(customerPayloadCustomer.getOp().equals("u"));
		Assertions.assertTrue(customerPayloadCustomer.getBefore().getFirstName().equals("Anne"));
		Assertions.assertTrue(customerPayloadCustomer.getAfter().getFirstName().equals("Anne Marie"));





	}

}
