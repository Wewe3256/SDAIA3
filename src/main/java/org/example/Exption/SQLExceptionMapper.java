package org.example.Exption;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;

import java.sql.SQLException;

public class SQLExceptionMapper extends Throwable implements ExceptionMapper<SQLException> {
    @Override
    public Response toResponse(SQLException exception) {
        ErrorMessage err = new ErrorMessage();
        err.setErrorContent(exception.getMessage());
        err.setErrorCode(500);
        err.setDocumentationUrl("https://google.com");

        return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(err)
                .build();
    }
}