package com.shutup.ohaus_app.api;

/**
 * Created by shutup on 2016/10/16.
 */
public class TianpingEntity {
    private String no;
    private String desc;
    private int price;
    private String readable;
    private String maxRange;
    private String adjust;
    private String autoWindCapGate;
    private String originImport;
    private int id;

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getReadable() {
        return readable;
    }

    public void setReadable(String readable) {
        this.readable = readable;
    }

    public String getMaxRange() {
        return maxRange;
    }

    public void setMaxRange(String maxRange) {
        this.maxRange = maxRange;
    }

    public String getAdjust() {
        return adjust;
    }

    public void setAdjust(String adjust) {
        this.adjust = adjust;
    }

    public String getAutoWindCapGate() {
        return autoWindCapGate;
    }

    public void setAutoWindCapGate(String autoWindCapGate) {
        this.autoWindCapGate = autoWindCapGate;
    }

    public String getOriginImport() {
        return originImport;
    }

    public void setOriginImport(String originImport) {
        this.originImport = originImport;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
