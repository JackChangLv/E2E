package com.clientsimulator;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.util.HtmlUtils;


/**
 * @author LvChang
 *
 */
@Controller
public class ClientSimulator {


    @SuppressWarnings("rawtypes")
    Map<String,List> clientMap = new HashMap<String,List>();


    @RequestMapping(value = "/ClientSimulator/{id}", method = RequestMethod.GET)
    public String MessageBoard(@PathVariable String id, Model model) {
        if (clientMap.get(id) != null) {
            @SuppressWarnings("unchecked")
            List<Message> messagesList = new ArrayList<Message>(clientMap.get(id));
            //Response messagesList descending order
            Collections.reverse(messagesList);
            model.addAttribute("messagesList", messagesList);
        }
        return "ClientSimulator";
    }


    @SuppressWarnings("unchecked")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @RequestMapping(value = "/ClientSimulator/{id}", method = RequestMethod.POST)
    public void Receiver(@PathVariable String id, @RequestBody String receivedmessage) {

        Long milliseconds = System.currentTimeMillis();
        System.out.println(" [Received] #"+Long.toString(milliseconds)+"# "+receivedmessage);

        //messageString
        List<Message> messagesList = new ArrayList<Message>();
        if (clientMap.get(id) != null) {
            messagesList = clientMap.get(id);
        }
        Message message = new Message();
        message.setMessageString(HtmlUtils.htmlEscape(receivedmessage));

        //Received Time
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        message.setReceivedTimeString(sdf.format(new Date(milliseconds)));

        //Time Lapse
        Pattern r = Pattern.compile("<TestTime:(\\d+)>");
        Matcher m = r.matcher(receivedmessage);
        if(m.find()) {
            float timeLapse = (float)(milliseconds - Long.parseLong(m.group(1))) / 1000L;
            message.setTimeLapse(String.format("%.3f", timeLapse));
        }

        messagesList.add(message);
        //Keep last 100 messages
        if(messagesList.size() > 100){
            messagesList.remove(0);
        }

        clientMap.put(id,messagesList);
    }


    @RequestMapping(value = "/ClientSimulator/{id}", method = RequestMethod.DELETE)
    public void clearMessages(@PathVariable String id) {
        if (clientMap.get(id) != null) {
            clientMap.remove(id);
        }
    }
}
