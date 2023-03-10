package com.digua.repository.net.company.bean;

import java.io.Serializable;
import java.util.List;

/**
 */
public class GetAccountFlowListResponse {
    private List<AccountFlow> pageItems;

    public List<AccountFlow> getPageItems() {
        return pageItems;
    }

    public void setPageItems(List<AccountFlow> pageItems) {
        this.pageItems = pageItems;
    }

    public class AccountFlow implements Serializable {
        private String creator;
        private String createTime;
        private String modifier;
        private String modifyTime;
        private String accountFlowId;
        private String accountId;
        private String shiftId;
        private String ownerId;
        private String ownerName;
        private String shopId;
        private String orderId;
        private String orderCode;
        private Double transAmount;
        private String payChannel;
        private String transId;
        private String transName;
        private Double accountBalance;
        private String remark;
        private String oppositeId;
        private String oppositeName;
        private String phoneNum;
        private boolean select = true;

        public boolean isSelect() {
            return select;
        }

        public void setSelect(boolean select) {
            this.select = select;
        }

        private String creatorName;

        public String getCreator() {
            return creator;
        }

        public void setCreator(String creator) {
            this.creator = creator;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getModifier() {
            return modifier;
        }

        public void setModifier(String modifier) {
            this.modifier = modifier;
        }

        public String getModifyTime() {
            return modifyTime;
        }

        public void setModifyTime(String modifyTime) {
            this.modifyTime = modifyTime;
        }

        public String getAccountFlowId() {
            return accountFlowId;
        }

        public void setAccountFlowId(String accountFlowId) {
            this.accountFlowId = accountFlowId;
        }

        public String getAccountId() {
            return accountId;
        }

        public void setAccountId(String accountId) {
            this.accountId = accountId;
        }

        public String getShiftId() {
            return shiftId;
        }

        public void setShiftId(String shiftId) {
            this.shiftId = shiftId;
        }

        public String getOwnerId() {
            return ownerId;
        }

        public void setOwnerId(String ownerId) {
            this.ownerId = ownerId;
        }

        public String getOwnerName() {
            return ownerName;
        }

        public void setOwnerName(String ownerName) {
            this.ownerName = ownerName;
        }

        public String getShopId() {
            return shopId;
        }

        public void setShopId(String shopId) {
            this.shopId = shopId;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getOrderCode() {
            return orderCode;
        }

        public void setOrderCode(String orderCode) {
            this.orderCode = orderCode;
        }

        public Double getTransAmount() {
            return transAmount;
        }

        public void setTransAmount(Double transAmount) {
            this.transAmount = transAmount;
        }

        public String getPayChannel() {
            return payChannel;
        }

        public void setPayChannel(String payChannel) {
            this.payChannel = payChannel;
        }

        public String getTransId() {
            return transId;
        }

        public void setTransId(String transId) {
            this.transId = transId;
        }

        public String getTransName() {
            return transName;
        }

        public void setTransName(String transName) {
            this.transName = transName;
        }

        public Double getAccountBalance() {
            return accountBalance;
        }

        public void setAccountBalance(Double accountBalance) {
            this.accountBalance = accountBalance;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getOppositeId() {
            return oppositeId;
        }

        public void setOppositeId(String oppositeId) {
            this.oppositeId = oppositeId;
        }

        public String getOppositeName() {
            return oppositeName;
        }

        public void setOppositeName(String oppositeName) {
            this.oppositeName = oppositeName;
        }

        public String getPhoneNum() {
            return phoneNum;
        }

        public void setPhoneNum(String phoneNum) {
            this.phoneNum = phoneNum;
        }

        public String getCreatorName() {
            return creatorName;
        }

        public void setCreatorName(String creatorName) {
            this.creatorName = creatorName;
        }
    }
}
