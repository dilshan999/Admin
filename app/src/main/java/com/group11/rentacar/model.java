package com.group11.rentacar;

public class model {
    String qus , ans;



    model()
    {


    }


    public model(String qus, String ans) {
        this.qus = qus;
        this.ans = ans;
    }

    public String getQus() {
        return qus;
    }

    public void setQus(String qus) {
        this.qus = qus;
    }

    public String getAns() {
        return ans;
    }

    public void setAns(String ans) {
        this.ans = ans;
    }
}


