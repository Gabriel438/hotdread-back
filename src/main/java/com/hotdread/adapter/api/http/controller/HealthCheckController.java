package com.hotdread.adapter.api.http.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/health-check")
public class HealthCheckController {

    @GET
    public String liveness() {
        return null;
    }

}
