package com.code;

import java.sql.Time;
import java.util.Date;

public class Task {
    private int id;              // Added ID field
    private String toDo;
    private String description;
    private Date dueDate;
    private Time dueTime;
    private boolean sendMail;    // Added sendMail field
    private boolean important;   // Added important field

    // Updated Constructor
    public Task(int id, String toDo, String description, Date dueDate, Time dueTime, boolean sendMail, boolean important) {
        this.id = id;
        this.toDo = toDo;
        this.description = description;
        this.dueDate = dueDate;
        this.dueTime = dueTime;
        this.sendMail = sendMail;
        this.important = important;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToDo() {
        return toDo;
    }

    public void setToDo(String toDo) {
        this.toDo = toDo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Time getDueTime() {
        return dueTime;
    }

    public void setDueTime(Time dueTime) {
        this.dueTime = dueTime;
    }

    public boolean isSendMail() {
        return sendMail;
    }

    public void setSendMail(boolean sendMail) {
        this.sendMail = sendMail;
    }

    public boolean isImportant() {
        return important;
    }

    public void setImportant(boolean important) {
        this.important = important;
    }
}
