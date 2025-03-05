package com.greenbowl.greenbowlserver.recommendation.adapter.out.request;

import lombok.Getter;

@Getter
public class LlmRequest {
    private String llm_type;
    private String template;
    private Object options;
    private String secret_key;

    private LlmRequest(String llm_type, String template, Object options, String secret_key) {
        this.llm_type = llm_type;
        this.template = template;
        this.options = options;
        this.secret_key = secret_key;
    }

    public static LlmRequest from(
            String llm_type, String template, Object options, String secret_key
    ) {
        return new LlmRequest(llm_type, template, options, secret_key);
    }
}
