package com.temesgenbesha.projectmanagementsystem.entity;

public enum Priority {

    // we can't see enum values in DB
    LOW, NORMAL, HIGH;

    /*
    if we want to display the enums in the database we can use this form
        LOW(value:"low"), NORMAL(value:"NORMAL"), HIGH(value: "HIGH");
        public String value;
        priority(String value){

            this.value = value;
        }
        public void setValue(String value){
        this.value =value;


     */




}
