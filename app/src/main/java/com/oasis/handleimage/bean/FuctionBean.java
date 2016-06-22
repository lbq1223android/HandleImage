package com.oasis.handleimage.bean;

/**
 * Created by liling on 2016/6/22.
 */
public class FuctionBean {
    public String funcionName ;
    public Class className ;

    public  FuctionBean(String funcionName,Class className){
        this.funcionName = funcionName ;
        this.className = className ;
    }

    public String getFuncionName() {
        return funcionName;
    }

    public void setFuncionName(String funcionName) {
        this.funcionName = funcionName;
    }

    public Class getClassName() {
        return className;
    }

    public void setClassName(Class className) {
        this.className = className;
    }
}
