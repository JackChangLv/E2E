package com.rabbitmq;

/**
 * @author LvChang
 *
 */
public class RabbitMQ {
    
    private String username;
    private String password;
    private String host;
    private String port;
    private String vhost;
    private String exchange_name;
    private String routing_key;
    private String queue_name;
    
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getHost() {
        return host;
    }
    public void setHost(String host) {
        this.host = host;
    }
    public String getPort() {
        return port;
    }
    public void setPort(String port) {
        this.port = port;
    }
    public String getVhost() {
        return vhost;
    }
    public void setVhost(String vhost) {
        this.vhost = vhost;
    }
    public String getExchange_name() {
        return exchange_name;
    }
    public void setExchange_name(String exchange_name) {
        this.exchange_name = exchange_name;
    }
    public String getRouting_key() {
        return routing_key;
    }
    public void setRouting_key(String routing_key) {
        this.routing_key = routing_key;
    }
    public String getQueue_name() {
        return queue_name;
    }
    public void setQueue_name(String queue_name) {
        this.queue_name = queue_name;
    }

}
