package com.omercikali.mobildefterim;

import java.io.Serializable;

public class WorkerModel implements Serializable {
    private String isci_ismi;
    private int gunluk_calisma_ucreti;
    private long tarih;

    public WorkerModel() {
    }

    public WorkerModel(String isci_ismi, int gunluk_calisma_ucreti, long tarih) {
        this.isci_ismi = isci_ismi;
        this.gunluk_calisma_ucreti = gunluk_calisma_ucreti;
        this.tarih = tarih;
    }
    //_

    public String getIsci_ismi() {
        return isci_ismi;
    }

    public void setIsci_ismi(String isci_ismi) {
        this.isci_ismi = isci_ismi;
    }

    public int getGunluk_calisma_ucreti() {
        return gunluk_calisma_ucreti;
    }

    public void setGunluk_calisma_ucreti(int gunluk_calisma_ucreti) {
        this.gunluk_calisma_ucreti = gunluk_calisma_ucreti;
    }

    public long getTarih() {
        return tarih;
    }

    public void setTarih(long tarih) {
        this.tarih = tarih;
    }
}
