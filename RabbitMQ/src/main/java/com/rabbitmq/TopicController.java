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
public class TopicController {

    @RequestMapping(value = "/Topic", method = RequestMethod.GET)
    public String Topic() {
        return "Topic";
    }
    
    @RequestMapping(value = "/Topic", method = RequestMethod.POST)
    public @ResponseBody String Topic(RabbitMQ rabbitmq, Topic topic) {

        StringBuffer sb = new StringBuffer();
        ConnectionFactory factory = new ConnectionFactory();

        try{
            factory.setUri("amqp://" + rabbitmq.getUsername() + ":" + rabbitmq.getPassword() + "@" + rabbitmq.getHost() + ":" + rabbitmq.getPort() + "/" + rabbitmq.getVhost());
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
       
            String topicMsg = "{\"topic\": \""+topic.getTopic()+"\","
                    + " \"description\": \""+topic.getDescription()+"\","
                    + " \"namespace\": \""+topic.getNamespace()+"\","
                    + " \"eventPayloadSchema\": \""+topic.getEventPayloadSchema()+"\","
                    + " \"eventAggregationTemplate\": \""+topic.getEventAggregationTemplate()+"\"}";
            
            channel.basicPublish(rabbitmq.getExchange_name(), rabbitmq.getRouting_key(), null, topicMsg.getBytes("UTF-8"));
            System.out.println(" [Sent] '" + topicMsg + "'");
            sb.append(HtmlUtils.htmlEscape(topicMsg)).append("<br>");
 
            channel.close();
            connection.close();
        } catch(Exception ex) {
            sb.append(ex.getMessage());
        }
        
        return sb.toString();
    }
}
