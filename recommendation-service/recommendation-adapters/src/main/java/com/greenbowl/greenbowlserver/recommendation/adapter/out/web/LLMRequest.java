package com.greenbowl.greenbowlserver.recommendation.adapter.out.web;

import com.greenbowl.greenbowlserver.recommendation.domain.RecipeOptions;
import lombok.Getter;

@Getter
public class LLMRequest {
    private String llm_type;
    private String template;
    private RecipeOptions options;
    private String secret_key;

    private LLMRequest(String llm_type, String template, RecipeOptions recipeOptions, String secret_key) {
        this.llm_type = llm_type;
        this.template = template;
        options = recipeOptions;
        this.secret_key = secret_key;
    }

    public static LLMRequest from(
            String llm_type, String template, RecipeOptions recipeOptions, String secret_key
    ) {
        return new LLMRequest(llm_type, template, recipeOptions, secret_key);
    }
}
