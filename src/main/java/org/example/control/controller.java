package org.example.control;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import org.example.Exption.DataNotFoundException;
import org.example.Exption.SQLExceptionMapper;
import org.example.Maper.jobMaper;
import org.example.databaseacess.JobDTO;
import org.example.databaseacess.jobDOA;
import org.example.Filter.JobFilterDot;
import org.example.model.Job;

import java.net.URI;
import java.sql.SQLException;
import java.util.ArrayList;

@Path("/JOB")
public class controller {
    @Inject
    jobDOA dao;

    @Context UriInfo uriInfo;
    @Context HttpHeaders headers;

    // كلاس يشيير للهيدر وurL
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    ///////////?????????????? تحديد لنوع البيانات اللتي سوف يعرضها server
    public Response getAllJob(
            // responce به heder , cookis ,وكل شيء
            @BeanParam JobFilterDot filter
            ///اطلع على تفاصيل الكلاس
    ) {
        try {
            GenericEntity<ArrayList<Job>> d = new GenericEntity<ArrayList<Job>>(dao.selectAllJobs(filter)) {};
            //حطيت Array داخل generic لانه الxml ماتفهم المجموعات او تعرضها الا لو حطيته
            if(headers.getAcceptableMediaTypes().contains(MediaType.valueOf(MediaType.APPLICATION_XML))) {
                return Response
                        .ok(d)
                        // الرسالة سواء غلط صح او ايا كان
                        .type(MediaType.APPLICATION_XML)
                        .build();
            }
            return Response
                    .ok()
                    .entity(d)
                    .type(MediaType.APPLICATION_JSON)
                    .build();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

//// linkkkkkk linkkkkkkkk
    @GET
    @Path("{jobId}")
    public Response getJOB(@PathParam("jobId") int jobId) throws SQLExceptionMapper {

        try {
            //return dao.selectJobs(deptId);
            Job jobs = null;
            try {
                jobs = dao.selectJobs(jobId);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            if (jobs == null) {
                throw new DataNotFoundException("Jobs " + jobId + "Not found");
            }
//            JobDTO dtt = new JobDTO();
            //dt.setJob_title(jobs.getJob_title());
            //dt.setMin_salary(jobs.getMin_salary());
            //dt.setMax_salary(jobs.getMax_salary());
            JobDTO dt = jobMaper.INSTANCE.toJobDto(jobs);
            // وش الفايده طيب
            addLinks(dt);

            return Response.ok(dt).build();
        } catch (DataNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private void addLinks(JobDTO dto) {
        URI selfUri = uriInfo.getAbsolutePath();
        URI empsUri = uriInfo.getAbsolutePathBuilder()
                .path(controller.class)
                .build();

        dto.addLink(selfUri.toString(), "self");
        dto.addLink(empsUri.toString(), "employees");
    }
    @DELETE
    @Path("{jobId}")
    public void deleteJOB(@PathParam("jobId") int deptId) {

        try {
            dao.setDeleteJobs(deptId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    ////////?//??/?/////????? يحدد نوع البيانات يلي يستقبلها server
    // بهالحاله مايستقبل ادخال بيانات الا xml
    public Response insertJOB(JobDTO f , @Context UriInfo uriInfo) {
// job d بدالها
        try {
            Job d= jobMaper.INSTANCE.toModel(f);
            // وش الفايده ؟؟؟؟؟
            dao.setInsertJobs(d);
            NewCookie cookie = (new NewCookie.Builder("username")).value("OOOOO").build();
            // يظهر في cokki
            URI uri = uriInfo.getAbsolutePathBuilder().path(d.getJob_id() + "").build();
            // in header //يعطي قيمة ال //url
            //مالها دخل بالكوكيز
            return Response
//                    .status(Response.Status.CREATED)
//                    .location(uri)
                    .created(uri)
//                  "username", "OOOOO"))
                    .cookie(cookie)
                    // اضيفها لل respons
                    // respons يحتوي على كل شي
                    .header("Created by", "wjdan")
                    //
                    .build();
            // ليش نوعها respons
            // وليش سوينا
            // لانها تحتوي على كل شيء respons

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @PUT
    @Path("{jobId}")
    public void updateJOB(@PathParam("jobId") int deptId, Job dept) {

        try {
            dept.setJob_id(deptId);
            dao.setUpdateJobs(dept);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
