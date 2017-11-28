package com.subtitlor.utilities;

import java.io.Serializable;

public class CoupleOfCustomizedString implements Serializable {

    private static final long serialVersionUID = 1L;
    private CustomizedString  customizedString1;

    private CustomizedString  customizedString2;

    public CoupleOfCustomizedString( CustomizedString customizedString1, CustomizedString customizedString2 ) {
        super();
        this.customizedString1 = customizedString1;
        this.customizedString2 = customizedString2;
    }

    public CoupleOfCustomizedString() {
        super();
    }

    public CustomizedString customizedstring1() {
        return customizedString1;
    }

    public CustomizedString getCustomizedstring1() {
        return customizedString1;
    }

    public void setCustomizedstring1( CustomizedString customizedstring1 ) {
        this.customizedString1 = customizedstring1;
    }

    public CustomizedString customizedstring2() {
        return customizedString2;
    }

    public CustomizedString getCustomizedstring2() {
        return customizedString2;
    }

    public void setCustomizedstring2( CustomizedString customizedstring2 ) {
        this.customizedString2 = customizedstring2;
    }
}
