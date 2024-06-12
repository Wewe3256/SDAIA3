package test.java;

import org.example.dao.JobDAO;
import org.example.databaseacess.jobDOA;
import org.example.model.Job;
import org.example.models.Jobs;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;


@ExtendWith(MockitoExtension.class)
public class TestJobDao {

    @InjectMocks
    jobDOA dao;

    @Test
    void testUpdateJob() throws SQLException, ClassNotFoundException {

        Job Jobs = new Job(19,"Test",4000,10000);

        Assertions.assertDoesNotThrow(()-> dao.updateJob(Jobs));

        Jobs newJ = dao.selectJob(Jobs.getJob_id());

        Assertions.assertNotNull(newJ);
        Assertions.assertEquals(newJ.getJob_id(),Jobs.getJob_id());
        Assertions.assertEquals(newJ.getJob_title(),Jobs.getJob_title());
}
}


