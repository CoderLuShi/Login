package net.lanon.entiy;

/**
 * @author 殷若淮
 * @version 1.0.0
 * @ClassName Sms.java
 * @Description TODO
 * @createTime 2021年11月05日 06:18:00
 */
public class Sms {
    String phoneNum;
    String pinCode;
    int minTime;

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public int getMinTime() {
        return minTime;
    }

    public void setMinTime(int minTime) {
        this.minTime = minTime;
    }
}
