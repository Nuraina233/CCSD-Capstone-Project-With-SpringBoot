package com.example.ecommerce.models;

public class Customers {
    private int customer_id;
    private String fName;
    private String lName;
    private String email;
    private String address;
    private String phone;
    private String password;

    public Customers(int id, String fName, String lName, String email, String address, String phone, String password){
        this.customer_id = id;
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.password = password;    }

    public int getCustomer_id() {
        return customer_id;
    }

    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }

//    @Override
//    public String toString() {
//        return "Customers{" +
//                "\n\tcustomer_id=" + customer_id +
//                ",\n\tfName='" + fName + '\'' +
//                ",\n\tlName='" + lName + '\'' +
//                ",\n\temail='" + email + '\'' +
//                ",\n\taddress='" + address + '\'' +
//                ",\n\tphone='" + phone + '\'' +
//                ",\n\tpassword='" + password + '\'' +
//                "\n}";
//    }
}
