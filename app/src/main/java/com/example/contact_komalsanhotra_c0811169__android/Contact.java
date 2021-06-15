package com.example.contact_komalsanhotra_c0811169__android;

public class Contact {
    private String fname;
    private String lName;
    private String email;
    private int Pnumber;
    private String address;

    public Contact( String trim2, String s1, String trim1, String trim, String s){
        this.fname = fname;
        this.lName = lName;
        this.email = email;
        this.Pnumber = Pnumber;
        this.address = address;
    }



    public String getFname(){
        return fname;
    }
    public String getlName(){
        return lName;
    }
    public String getEmail(){
        return email;
    }
    public int getPnumber(){
        return Pnumber;
    }
    public String getAddress(){
        return address;
    }
}
