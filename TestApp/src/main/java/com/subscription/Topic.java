package com.subscription;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author LvChang
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Topic {

    private Integer topicId;
    private String topicName;
    
    public Integer getTopicId() {
        return topicId;
    }
    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }
    public String getTopicName() {
        return topicName;
    }
    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

}
