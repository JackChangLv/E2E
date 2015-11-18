package com.subscription;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpStatusCodeException;

import com.utils.RestClient;

/**
 * @author LvChang
 *
 */
@Controller
public class SubscriptionController {

    final String TOPICS_PATH = "/notify/v1/topics";
    final String SUBSCRIPTION_PATH = "/notify/v1/subscription";
    final String HOST_URI = "http://localhost:8282";

    String uri = HOST_URI;
    String consumerKey = "";
    String consumerSecret = "";

    //Get Index page
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String Index() {
        return "index";
    }

    //Post Index page to set uri and apiKey
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String Index(String Uri, String Key, String Secret) {
        uri = Uri.isEmpty() ? HOST_URI : new StringBuilder().append("http://").append(Uri.replace("http://","")).toString();
        consumerKey =  Key;
        consumerSecret = Secret;
        return "redirect:/Subscription";
    }

    //Get Page for Create a New Subscription Subscriptions Grid
    @RequestMapping(value = "/Subscription/Create", method = RequestMethod.GET)
    public String Create(Model model) {
        String TopicsListUrl = new StringBuilder().append(uri).append(TOPICS_PATH).toString();
        RestClient restClient = new RestClient();
        try{
            ResponseEntity<TopicsList> responseTopicsList = restClient.exchange(TopicsListUrl, HttpMethod.GET, null, TopicsList.class);
            model.addAttribute("TopicsList",responseTopicsList.getBody().getTopics());
        } catch (HttpStatusCodeException ex) {
            model.addAttribute("GetTopicsListError", "Get TopicsList Error: "+ex.getMessage());
        } catch (Exception ex) {
            model.addAttribute("GetTopicsListError", "Get TopicsList Error: "+ex.getCause());
        }
        return "Subscription_Create";
    }

    //Post to Create a New Subscription
    @RequestMapping(value = "/Subscription/Create", method = RequestMethod.POST)
    public @ResponseBody String Create(Subscription subscription) {
        String CreateSubscriptionsUrl = new StringBuilder().append(uri).append(SUBSCRIPTION_PATH).toString();
        String response = "";
        RestClient restClient = new RestClient();
        try {
            ResponseEntity<Subscription> responseSubscription = restClient.exchange(CreateSubscriptionsUrl, HttpMethod.POST, subscription, Subscription.class, consumerKey, consumerSecret);
            response = "SubscriptionId #" + responseSubscription.getBody().getSubscriptionId() + "created";
        } catch (HttpStatusCodeException ex) {
            response = ex.getResponseBodyAsString();
        } catch (Exception ex) {
            response = ex.getMessage();
        }
        return response;
    }

    //Get Subscriptions Grid
    @RequestMapping(value = "/Subscription", method = RequestMethod.GET)
    public void Get(Model model) {
        String SubscriptionsListUrl = new StringBuilder().append(uri).append(SUBSCRIPTION_PATH).toString();
        RestClient restClient = new RestClient();
        try{
            ResponseEntity<SubscriptionsList> responseSubscriptionsList = restClient.exchange(SubscriptionsListUrl, HttpMethod.GET, null, SubscriptionsList.class, consumerKey, consumerSecret);
            model.addAttribute("SubscriptionsList",responseSubscriptionsList.getBody().getSubscriptionsList());
        } catch (HttpStatusCodeException ex) {
            model.addAttribute("GetSubscriptionsError", "Get Subscriptions Error: "+ex.getMessage());
        } catch (Exception ex) {
            model.addAttribute("GetSubscriptionsError", "Get Subscriptions Error: "+ex.getCause());
        }
    }

    //Update Subscription in Subscriptions Grid
    @RequestMapping(value = "/Subscription/{id}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void Update(@PathVariable String id,  @RequestBody MultiValueMap<String, String> body) {
        Subscription subscription = new Subscription();
        subscription.setNotificationPushUrl(body.getFirst("notificationPushUrl"));
        subscription.setDeliveryTiming(Integer.parseInt(body.getFirst("deliveryTiming")));
        subscription.setMaxRetryCount(Integer.parseInt(body.getFirst("maxRetryCount")));

        String UpdateSubscriptionsUrl = new StringBuilder().append(uri).append(SUBSCRIPTION_PATH).append("/").append(id).toString();
        RestClient restClient = new RestClient();
        restClient.exchange(UpdateSubscriptionsUrl, HttpMethod.PUT, subscription, Subscription.class, consumerKey, consumerSecret);
    }

    //Delete Subscription from Subscriptions Grid
    @RequestMapping(value = "/Subscription/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void Delete(@PathVariable String id) {
        String DeleteSubscriptionsUrl = new StringBuilder().append(uri).append(SUBSCRIPTION_PATH).append("/").append(id).toString();
        RestClient restClient = new RestClient();
        restClient.exchange(DeleteSubscriptionsUrl, HttpMethod.DELETE, null, String.class, consumerKey, consumerSecret);
    }
}
