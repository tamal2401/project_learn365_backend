package org.learn365.modal.user;

public class SignedCookieResponse {
    private String policy;
    private String KeyPairId;
    private String signature;

    public SignedCookieResponse(String policy, String keyPairId, String signature) {
        this.policy = policy;
        KeyPairId = keyPairId;
        this.signature = signature;
    }

    public SignedCookieResponse() {
    }

    public String getPolicy() {
        return policy;
    }

    public void setPolicy(String policy) {
        this.policy = policy;
    }

    public String getKeyPairId() {
        return KeyPairId;
    }

    public void setKeyPairId(String keyPairId) {
        KeyPairId = keyPairId;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
