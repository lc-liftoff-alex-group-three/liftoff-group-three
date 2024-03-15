package org.example.models;

public class EmailVerificationResponse {

    private boolean formatCheck;
    private boolean smtpCheck;
    private boolean dnsCheck;
    private boolean freeCheck;

    public boolean isFormatCheck() {
        return formatCheck;
    }

    public void setFormatCheck(boolean formatCheck) {
        this.formatCheck = formatCheck;
    }

    public boolean isSmtpCheck() {
        return smtpCheck;
    }

    public void setSmtpCheck(boolean smtpCheck) {
        this.smtpCheck = smtpCheck;
    }

    public boolean isDnsCheck() {
        return dnsCheck;
    }

    public void setDnsCheck(boolean dnsCheck) {
        this.dnsCheck = dnsCheck;
    }

    public boolean isFreeCheck() {
        return freeCheck;
    }

    public void setFreeCheck(boolean freeCheck) {
        this.freeCheck = freeCheck;
    }


    public boolean isEmailValid() {

        if (formatCheck && smtpCheck && dnsCheck && freeCheck) {
            return true;
        } else {
            return false;
        }
    }



}
