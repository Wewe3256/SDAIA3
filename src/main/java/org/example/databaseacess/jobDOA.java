package org.example.databaseacess;

import org.example.Filter.JobFilterDot;
import org.example.model.Job;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;

public class jobDOA implements Serializable {
    private static final String URL = "jdbc:sqlite:C:\\Users\\dev\\Downloads\\hr.db";
    private static final String SELECT_ALL_jobs = "select * from Jobs";
    private static final String SELECT_ONE_jobs = "select * from Jobs where job_id = ?";
    private static final String INSERT_jobs = "insert into Jobs values (?, ?, ? ,?)";
    private static final String UPDATE_jobs = "update Jobs set job_title = ? ,min_salary = ?, max_salary = ? where job_id = ?";
    private static final String DELETE_jobs = "delete from Jobs where job_id = ?";
    ////???/??/?//?/////??/
    private static final String SELECT_Job_WITH_MIN = "select * from Jobs where min_salary = ?";
    private static final String SELECT_Job_WITH_Min_PAGINATION = "select * from Jobs where min_salary = ? order by job_id limit ? offset ?";
    private static final String SELECT_Job_WITH_PAGINATION = "select * from Jobs order by job_id limit ? offset ?";
    //==========================================================================================


    public void setInsertJobs(Job d) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st = conn.prepareStatement(INSERT_jobs);
        st.setInt(1, d.getJob_id());
        st.setString(2, d.getJob_title());
        st.setDouble(3, d.getMin_salary());
        st.setDouble(4, d.getMax_salary());
        st.executeUpdate();
    }

    public void setUpdateJobs(Job d) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st = conn.prepareStatement(UPDATE_jobs);
        st.setInt(1, d.getJob_id());
        st.setString(2, d.getJob_title());
        st.setDouble(3, d.getMin_salary());
        st.setDouble(4, d.getMax_salary());
        st.executeUpdate();
    }

    public void setDeleteJobs(int jobstId) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st = conn.prepareStatement(DELETE_jobs);
        st.setInt(1, jobstId);
        st.executeUpdate();
    }

    public Job selectJobs(int job_id) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st = conn.prepareStatement(SELECT_ONE_jobs);
        st.setInt(1, job_id);
        ResultSet rs = st.executeQuery();
        if(rs.next()) {
            return new Job(rs);
        }
        else {
            return null;
        }
    }

    public ArrayList<Job> selectAllJobs(Integer minsalary ,Integer limit ,int offest) throws SQLException, ClassNotFoundException {
        // int  يعني القيمه الابتدائية صفر
        // معناها يبدا من صفر
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st;
        if(minsalary != null && limit != null) {
            //  بمعنى لازم يكون محدد لي كل القيم
            st = conn.prepareStatement(SELECT_Job_WITH_Min_PAGINATION);
            st.setInt(1, minsalary);
            st.setInt(2, limit);
            st.setInt(3, offest);
        }else if(minsalary != null) {
            st = conn.prepareStatement(SELECT_Job_WITH_MIN);
            st.setInt(1, minsalary);
            // ادخل بس الحد الادنى للرواتب
        }else if(limit != null) {
            st = conn.prepareStatement(SELECT_Job_WITH_PAGINATION);
            st.setInt(1, limit);
            st.setInt(2,offest);
           // ادخل بس الحد الادنى/ والحد الادنى تلقائي يكون صفر//
        }else {
            st = conn.prepareStatement(SELECT_ALL_jobs);
        }

        ResultSet rs = st.executeQuery();
        ArrayList<Job> jobs = new ArrayList<>();
        while (rs.next()) {
            jobs.add(new Job(rs));
        }

        return jobs;
    }
    public ArrayList<Job> selectAllJobs(JobFilterDot filter) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st;
        if(filter.getMin_salary() != null && filter.getLimit() != null) {
            st = conn.prepareStatement(SELECT_Job_WITH_Min_PAGINATION);
            st.setDouble(1, filter.getMin_salary());
            st.setInt(2, filter.getLimit());
            st.setInt(3, filter.getOffset());
        }
        else if(filter.getMin_salary()!= null) {
            st = conn.prepareStatement(SELECT_Job_WITH_MIN);
            st.setDouble(1, filter.getMin_salary());
        }
        else if(filter.getLimit() != null) {
            st = conn.prepareStatement(SELECT_Job_WITH_PAGINATION);
            st.setInt(1, filter.getLimit());
            st.setInt(2, filter.getOffset());
        }
        else {
            st = conn.prepareStatement(SELECT_ALL_jobs);
        }
        ResultSet rs = st.executeQuery();
        ArrayList<Job> depts = new ArrayList<>();
        while (rs.next()) {
            depts.add(new Job(rs));
        }

        return depts;
    }

}
