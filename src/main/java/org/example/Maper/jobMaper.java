package org.example.Maper;


import org.example.databaseacess.JobDTO;
import org.example.model.Job;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface jobMaper {
    jobMaper INSTANCE = Mappers.getMapper(jobMaper.class);

    JobDTO toJobDto(Job d);
// احول الجاي من الjob الى JobDTO
    Job toModel(JobDTO dto);
    // احول الجاي من JobDTO  الى Job

}
