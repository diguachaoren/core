package com.digua.repository.net.company.bean;

import java.io.Serializable;

public class SampleItem implements Serializable {
    private String productId;
    private String productName;
    private String cookerName;
    private String sampleWeight;
    private String verifierName;
    private Long createTime;
    private FoodPeriodTypeDTO foodPeriodType;
    private String sampleImage;
    private String sampleStatus;
    private String auditStatus;
    private String comment;
    private Long modifyTime;
    private String shopId;
    private String sampleCode;
    private String sampleProductId;
    private Object sampleProductIdList;
    private String sampleDate;
    private String shopName;

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getSampleDate() {
        return sampleDate;
    }

    public void setSampleDate(String sampleDate) {
        this.sampleDate = sampleDate;
    }

    private boolean select = true;

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCookerName() {
        return cookerName;
    }

    public void setCookerName(String cookerName) {
        this.cookerName = cookerName;
    }

    public String getSampleWeight() {
        return sampleWeight;
    }

    public void setSampleWeight(String sampleWeight) {
        this.sampleWeight = sampleWeight;
    }

    public String getVerifierName() {
        return verifierName;
    }

    public void setVerifierName(String verifierName) {
        this.verifierName = verifierName;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public FoodPeriodTypeDTO getFoodPeriodType() {
        return foodPeriodType;
    }

    public void setFoodPeriodType(FoodPeriodTypeDTO foodPeriodType) {
        this.foodPeriodType = foodPeriodType;
    }

    public String getSampleImage() {
        return sampleImage;
    }

    public void setSampleImage(String sampleImage) {
        this.sampleImage = sampleImage;
    }

    public String getSampleStatus() {
        return sampleStatus;
    }

    public void setSampleStatus(String sampleStatus) {
        this.sampleStatus = sampleStatus;
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Long modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getSampleCode() {
        return sampleCode;
    }

    public void setSampleCode(String sampleCode) {
        this.sampleCode = sampleCode;
    }

    public String getSampleProductId() {
        return sampleProductId;
    }

    public void setSampleProductId(String sampleProductId) {
        this.sampleProductId = sampleProductId;
    }

    public Object getSampleProductIdList() {
        return sampleProductIdList;
    }

    public void setSampleProductIdList(Object sampleProductIdList) {
        this.sampleProductIdList = sampleProductIdList;
    }

    public static class FoodPeriodTypeDTO implements Serializable{
        private String title;
        private String name;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
