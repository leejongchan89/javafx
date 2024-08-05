package com.itgroup.test.bean;

//Items 데이터 테이블 참조해서 상품 1개를 의미하는 bean class

public class Item {
    private int item_num;
    private String category01;
    private String category02;
    private String category03;
    private String condition;
    private String item_size;
    private String brand;
    private int shipping_fee;
    private int price;
    private String negotiation;
    private String image01;
    private String image02;
    private String image03;
    private String item_name;
    private String item_description;
    private String item_inputdate;
    private String user_id;
    private String user_nickname;

    public Item() {
    }

    @Override
    public String toString() {
        final String SEPARATOR = "/"; //항목 구분자

        String temp = "";
        temp += item_num + SEPARATOR;
        temp += category01 + SEPARATOR;
        temp += category02 + SEPARATOR;
        temp += category03 + SEPARATOR;
        temp += condition + SEPARATOR;
        temp += item_size + SEPARATOR;
        temp += brand + SEPARATOR;
        temp += shipping_fee + SEPARATOR;
        temp += price + SEPARATOR;
        temp += negotiation + SEPARATOR;
        temp += image01 + SEPARATOR;
        temp += image02 + SEPARATOR;
        temp += image03 + SEPARATOR;
        temp += item_name + SEPARATOR;
        temp += item_description + SEPARATOR;
        temp += item_inputdate + SEPARATOR;
        temp += user_id + SEPARATOR;
        temp += user_nickname;


        return temp;
    }

    public int getItem_num() {
        return item_num;
    }

    public void setItem_num(int item_num) {
        this.item_num = item_num;
    }

    public String getCategory01() {
        return category01;
    }

    public void setCategory01(String category01) {
        this.category01 = category01;
    }

    public String getCategory02() {
        return category02;
    }

    public void setCategory02(String category02) {
        this.category02 = category02;
    }

    public String getCategory03() {
        return category03;
    }

    public void setCategory03(String category03) {
        this.category03 = category03;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getItem_size() {
        return item_size;
    }

    public void setItem_size(String item_size) {
        this.item_size = item_size;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getShipping_fee() {
        return shipping_fee;
    }

    public void setShipping_fee(int shipping_fee) {
        this.shipping_fee = shipping_fee;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getNegotiation() {
        return negotiation;
    }

    public void setNegotiation(String negotiation) {
        this.negotiation = negotiation;
    }

    public String getImage01() {
        return image01;
    }

    public void setImage01(String image01) {
        this.image01 = image01;
    }

    public String getImage02() {
        return image02;
    }

    public void setImage02(String image02) {
        this.image02 = image02;
    }

    public String getImage03() {
        return image03;
    }

    public void setImage03(String image03) {
        this.image03 = image03;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getItem_description() {
        return item_description;
    }

    public void setItem_description(String item_description) {
        this.item_description = item_description;
    }

    public String getItem_inputdate() {
        return item_inputdate;
    }

    public void setItem_inputdate(String item_inputdate) {
        this.item_inputdate = item_inputdate;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_nickname() {
        return user_nickname;
    }

    public void setUser_nickname(String user_nickname) {
        this.user_nickname = user_nickname;
    }
}
