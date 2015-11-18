package com.rabbitmq;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @author LvChang
 *
 */
@Controller
public class EventController {

    int i=1;

    @RequestMapping(value = "/Event", method = RequestMethod.GET)
    public String Event() {
        i=1;
        return "Event";
    }
    
    @RequestMapping(value = "/Event", method = RequestMethod.POST)
    public @ResponseBody String Event(RabbitMQ rabbitmq, EventsList eventsList) {
        StringBuffer sb = new StringBuffer();
        ConnectionFactory factory = new ConnectionFactory();
        try{
            factory.setUri("amqp://" + rabbitmq.getUsername() + ":" + rabbitmq.getPassword() + "@" + rabbitmq.getHost() + ":" + rabbitmq.getPort() + "/" + rabbitmq.getVhost());
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            for(Event event : eventsList.getEvents()){
                if (event.getTopic().isEmpty()||event.getPayload().isEmpty()){
                    continue;
                }
                String milliseconds = Long.toString(System.currentTimeMillis());
                String topic = new StringBuffer().append( "\"topic\":\"").append(event.getTopic()).append("\"").toString();
                String payLoad = new StringBuffer().append( "\"payload\":\"").append("#").append(i).append(" ").append(event.getPayload()).append(" <TestTime:").append(milliseconds).append(">\"").toString();
                String timeStamp = new StringBuffer().append( "\"timestamp\":\"").append(milliseconds).append("\"").toString();
                //{"topic":"Airings","payload":"#1 TomCruise in Top Gun on air <TestTime:1447048159746>","timestamp":"1447048159746"}
                String message = new StringBuffer().append("{").append(topic).append(",").append(payLoad).append(",").append(timeStamp).append("}").toString();
                channel.basicPublish(rabbitmq.getExchange_name(), rabbitmq.getRouting_key(), null, message.getBytes("UTF-8"));
                System.out.println(" [Sent] '" + message + "'");
                sb.append(HtmlUtils.htmlEscape(message)).append("<br>");
                i++;
            }
            channel.close();
            connection.close();
        } catch(Exception ex) {
            sb.append(ex.getMessage());
        }
        return sb.toString();
    }
}