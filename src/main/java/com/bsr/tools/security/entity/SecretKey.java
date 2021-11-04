package com.bsr.tools.security.entity;

/**
 * 存储一对公钥和私钥
 */
public class SecretKey {
    private byte[] privateKey; //私钥
    private byte[] publicKey; //公钥

    public byte[] getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(byte[] privateKey) {
        this.privateKey = privateKey;
    }

    public byte[] getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(byte[] publicKey) {
        this.publicKey = publicKey;
    }
}
