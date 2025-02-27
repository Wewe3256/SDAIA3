package org.example.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Job {
    private String job_title;
    private int job_id;
    private double min_salary;
    private double max_salary;

    public Job(String job_title, int job_id, double min_salary, double max_salary){

        this.job_id = job_id;
        this.job_title = job_title;
        this.min_salary = min_salary;
        this.max_salary = max_salary;
    }
    public Job(ResultSet rs) throws SQLException {
        job_id = rs.getInt("job_id");
        job_title = rs.getString("job_title");
        min_salary = rs.getDouble("min_salary");
        max_salary = rs.getDouble("max_salary");
    }

    public Job() {

    }


    public void setJob_id(int job_id) {
        this.job_id = job_id;
    }

    public void setJob_title(String job_title) {
        this.job_title = job_title;
    }


    public void setMin_salary(double min_salary) {
        this.min_salary = min_salary;
    }

    public void setMax_salary(double max_salary) {
        this.max_salary = max_salary;
    }



    public int getJob_id() {
        return job_id;
    }
    public String getJob_title() {
        return job_title;
    }

    public double getMin_salary() {
        return min_salary;
    }

    public double getMax_salary() {
        return max_salary;
    }



    @Override
    public String toString() {
        return "Job{" +
                "job_title='" + job_title + '\'' +
                ", job_id=" + job_id +
                ", min_salary=" + min_salary +
                ", max_salary=" + max_salary +
                '}';
    }
}

