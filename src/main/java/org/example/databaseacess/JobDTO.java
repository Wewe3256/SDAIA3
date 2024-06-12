package org.example.databaseacess;

import org.example.Filter.LinkDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class JobDTO {
    private int job_id;
    private String job_title;
    private double min_salary;
    private double max_salary;

    private ArrayList<LinkDto> links = new ArrayList<LinkDto>();

    public JobDTO() {
    }

    public JobDTO(ResultSet resultSet) throws SQLException {
        job_id = resultSet.getInt("job_id");
        job_title = resultSet.getString("job_title");
        min_salary = resultSet.getInt("min_salary");
        max_salary = resultSet.getInt("max_salary");
    }

    public JobDTO(int job_id, String job_title, int min_salary, int max_salary) {
        this.job_id = job_id;
        this.job_title = job_title;
        this.min_salary = min_salary;
        this.max_salary = max_salary;
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

    //    @XmlElement
    public int getJob_id() {
        return job_id;
    }

    //    @XmlElement
    public String getJob_title() {
        return job_title;
    }

    //    @XmlElement
    public double getMin_salary() {
        return min_salary;
    }

    //    @XmlElement
    public double getMax_salary() {
        return max_salary;
    }

    public void setMax_salary(double max_salary) {
        this.max_salary = max_salary;
    }

    public ArrayList<LinkDto> getLinks() {
        return links;
    }

    public void addLink(String url, String rel) {
        LinkDto link = new LinkDto();
        link.setLink(url);
        link.setRel(rel);
        links.add(link);
    }

    @Override
    public String toString() {
        return "Job ID: " + job_id + ", Title: " + job_title + ", min_Salary: " + min_salary + ", max_salary: " + max_salary;

    }
}
