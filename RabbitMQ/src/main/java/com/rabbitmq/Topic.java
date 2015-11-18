package com.rabbitmq;

public class Topic {
    private String topic;
    private String description;
    private String namespace;
    private String eventPayloadSchema;
    private String eventAggregationTemplate;
    
    public String getTopic() {
        return topic;
    }
    public void setTopic(String topic) {
        this.topic = topic;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getNamespace() {
        return namespace;
    }
    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }
    public String getEventPayloadSchema() {
        return eventPayloadSchema;
    }
    public void setEventPayloadSchema(String eventPayloadSchema) {
        this.eventPayloadSchema = eventPayloadSchema;
    }
    public String getEventAggregationTemplate() {
        return eventAggregationTemplate;
    }
    public void setEventAggregationTemplate(String eventAggregationTemplate) {
        this.eventAggregationTemplate = eventAggregationTemplate;
    }
}
