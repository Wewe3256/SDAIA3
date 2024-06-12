package test.java;

import jakarta.ws.rs.core.UriBuilder;
import jakarta.ws.rs.core.UriInfo;
import org.example.databaseacess.jobDOA;
import org.example.model.Job;


import java.net.URI;


@ExtendWith(MockitoExtension.class)
public class TestJobController {
@InjectMocks
    JobController jobController;
@Mock
    UriInfo uriInfo;
@Mock
jobDOA dao;

@Test
    public void TestUpdate() {
    Job jobs = new Job(6, "It", 4000, 11000);
    URI uri = URI.create("http://localhost/api/job/1");

    when(uriInfo.getAbsolutePathBuilder()).thenReturn(UriBuilder.fromUri(uri));

    Assertions.assertDoesNotThrow(()-> jobController.updateJobs(1,jobs));
}
}