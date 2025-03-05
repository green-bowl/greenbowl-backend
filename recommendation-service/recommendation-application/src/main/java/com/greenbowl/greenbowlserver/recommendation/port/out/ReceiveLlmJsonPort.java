package com.greenbowl.greenbowlserver.recommendation.port.out;

public interface ReceiveLlmJsonPort {
    String receiveLlmResponse(Object abstractOptions, boolean isDetailed);
}
