package org.example.Filter;

import jakarta.ws.rs.QueryParam;

public class JobFilterDot {

    private @QueryParam("min_salary") Double min_salary;
    private @QueryParam("max_salary") Double max_salary;
    // QueryParam تاخذ مجموهة بعد علامة الاستفهام في المسار
    private @QueryParam("limit") Integer limit;
    private @QueryParam("offset") int offset;

    public Integer getLimit() {
        return limit;
    }

    public int getOffset() {
        return offset;
    }

    public Double getMin_salary() {
        return min_salary;
    }

    public void setMin_salary(Double min_salary) {
        this.min_salary = min_salary;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }
}
