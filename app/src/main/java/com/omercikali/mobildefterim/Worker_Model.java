package com.omercikali.mobildefterim;

import java.util.Date;

public class Worker_Model {
    private String isci_ismi;
    private long  calistigi_gun;
    private int saatlik_ucret;
    private int calistigi_saat;

    public Worker_Model() {
    }

    public Worker_Model(String isci_ismi, long calistigi_gun, int saatlik_ucret, int calistigi_saat) {
        this.isci_ismi = isci_ismi;
        this.calistigi_gun = calistigi_gun;
        this.saatlik_ucret = saatlik_ucret;
        this.calistigi_saat = calistigi_saat;
    }

    public String getIsci_ismi() { return isci_ismi; }
    public void setIsci_ismi(String isci_ismi) { this.isci_ismi = isci_ismi; }
    public long getCalistigi_gun() { return calistigi_gun; }
    public void setCalistigi_gun(long calistigi_gun) { this.calistigi_gun = calistigi_gun; }
    public int getSaatlik_ucret() { return saatlik_ucret; }
    public void setSaatlik_ucret(int saatlik_ucret) { this.saatlik_ucret = saatlik_ucret; }
    public int getCalistigi_saat() { return calistigi_saat; }
    public void setCalistigi_saat(int calistigi_saat) { this.calistigi_saat = calistigi_saat; }
}
