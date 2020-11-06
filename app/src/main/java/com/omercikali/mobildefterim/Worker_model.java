package com.omercikali.mobildefterim;

public class Worker_model {
    private String isci_adi;
    private int gunluk_ucret;

    public Worker_model() {
    }

    public Worker_model(String isci_adi, int gunluk_ucret) {
        this.isci_adi = isci_adi;
        this.gunluk_ucret = gunluk_ucret;
    }

    public String getIsci_adi() {
        return isci_adi;
    }

    public void setIsci_adi(String isci_adi) {
        this.isci_adi = isci_adi;
    }

    public int getGunluk_ucret() {
        return gunluk_ucret;
    }

    public void setGunluk_ucret(int gunluk_ucret) {
        this.gunluk_ucret = gunluk_ucret;
    }
}
