package org.example.Exption;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class DataNotFoundExceptionMapper  implements ExceptionMapper<DataNotFoundException> {
    @Override
    public Response toResponse(DataNotFoundException exception) {
        ErrorMessage err = new ErrorMessage();
        err.setErrorContent(exception.getMessage());
        // هنا كيف حددت الرسالة
        err.setErrorCode(404);
        err.setDocumentationUrl("https://google.com");

        return Response
                .status(Response.Status.NOT_FOUND)
                .entity(err)
                // يظهر بالbody
                .build();
    }
}
