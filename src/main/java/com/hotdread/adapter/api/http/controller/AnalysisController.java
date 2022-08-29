package com.hotdread.adapter.api.http.controller;

import com.hotdread.adapter.api.http.model.AnalysisREQ;
import com.hotdread.adapter.api.http.model.DTO.AnalysisRES;
import com.hotdread.adapter.api.http.model.ReportREQ;
import com.hotdread.adapter.api.http.service.AnalysisService;
import com.hotdread.adapter.api.utils.exception.AnalysisException;
import io.quarkus.logging.Log;
import lombok.SneakyThrows;
import org.apache.http.HttpStatus;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/analysis")
public class AnalysisController {

    @Inject
    AnalysisService analysisService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<AnalysisRES> getAnalysisUris(AnalysisREQ request) throws AnalysisException {
        try {
            return analysisService.sendRequest(request);
        }catch (Exception e){
            Log.error("Analysis Uri failed: " + e.getMessage(), e);
            throw new AnalysisException(e);
        }
    }

    @PUT
    @Path("/comments")
    @Produces(MediaType.APPLICATION_JSON)
    public Response pushReportUri(ReportREQ request) throws AnalysisException {
        try{
            analysisService.sendRequest(request);
            return Response.status(HttpStatus.SC_CREATED,"push retpor sucessfull").build();
        }catch (Exception e){
            Log.error("Push report failed: " + e.getMessage(), e);
            throw new AnalysisException(e);
        }
    }

}
