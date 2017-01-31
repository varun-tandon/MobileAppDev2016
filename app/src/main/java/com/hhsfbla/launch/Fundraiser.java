package com.hhsfbla.launch;

import java.util.Date;

/**
 * Created by Heidi on 1/30/2017.
 */
public class Fundraiser {

    protected String uid;
    protected String organizationName;
    protected String purpose;
    protected int goal;
    protected String deadline;
    protected String description;

    public Fundraiser(String uid, String organizationName, String purpose, int goal, String deadline,
                      String description) {
        this.uid = uid;
        this.organizationName = organizationName;
        this.purpose = purpose;
        this.goal = goal;
        this.deadline = deadline;
        this.description = description;
    }
}