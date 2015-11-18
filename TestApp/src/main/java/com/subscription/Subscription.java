package com.subscription;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author LvChang
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Subscription {

    private Integer subscriptionId;
    private Integer topicId;
    private String apiKey;
    private String mobileDeviceToken;
    private String notificationPushUrl;
    private String deliveryType;
    private Integer deliveryTiming;
    private String callbackURL;
    private Integer maxRetryCount;
    private String topic;

    public Integer getSubscriptionId() {
        return subscriptionId;
    }
    public void setSubscriptionId(Integer subscriptionId) {
        this.subscriptionId = subscriptionId;
    }
    public Integer getTopicId() {
        return topicId;
    }
    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }
    public String getApiKey() {
        return apiKey;
    }
    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
    public String getMobileDeviceToken() {
        return mobileDeviceToken;
    }
    public void setMobileDeviceToken(String mobileDeviceToken) {
        this.mobileDeviceToken = mobileDeviceToken;
    }
    public String getNotificationPushUrl() {
        return notificationPushUrl;
    }
    public void setNotificationPushUrl(String notificationPushUrl) {
        this.notificationPushUrl = notificationPushUrl;
    }
    public String getDeliveryType() {
        return deliveryType;
    }
    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }
    public Integer getDeliveryTiming() {
        return deliveryTiming;
    }
    public void setDeliveryTiming(Integer deliveryTiming) {
        this.deliveryTiming = deliveryTiming;
    }
    public String getCallbackURL() {
        return callbackURL;
    }
    public void setCallbackURL(String callbackURL) {
        this.callbackURL = callbackURL;
    }
    public Integer getMaxRetryCount() {
        return maxRetryCount;
    }
    public void setMaxRetryCount(Integer maxRetryCount) {
        this.maxRetryCount = maxRetryCount;
    }
    public String getTopic() {
        return topic;
    }
    public void setTopic(String topic) {
        this.topic = topic;
    }
}
