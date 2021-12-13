package com.redhat.camel.kafka.kafkamysql.model;

public class CustomerPayload {


    private Customer before;
    private Customer after;
    private String op;

    @Override
    public String toString() {
        return "Payload{" +
                "before=" + before +
                ", after=" + after +
                ", op='" + op + '\'' +
                '}';
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public Customer getAfter() {
        return after;
    }

    public void setAfter(Customer after) {
        this.after = after;
    }

    public Customer getBefore() {
        return before;
    }

    public void setBefore(Customer before) {
        this.before = before;
    }
}
