package com.hotdread.adapter.api.http.controller;

import com.hotdread.adapter.api.http.model.AnalysisREQ;
import com.hotdread.adapter.api.http.model.DTO.AnalysisRES;
import com.hotdread.adapter.api.http.service.AnalysisService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/analysis")
public class AnalysisController {

    @Inject
    AnalysisService analysisService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<AnalysisRES> getAnalysisUris(AnalysisREQ request) throws Exception {
        return analysisService.sendRequest(request);
    }

}
