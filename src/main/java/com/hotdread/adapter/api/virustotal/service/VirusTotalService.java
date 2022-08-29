package com.hotdread.adapter.api.virustotal.service;

import com.hotdread.adapter.api.virustotal.model.VirusTotalResponse;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@RegisterRestClient(configKey="virustotal-service")
public interface  VirusTotalService {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    VirusTotalResponse fetchAll(@QueryParam("apikey") String apiKey,
                                     @QueryParam("resource") String uri);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    VirusTotalResponse fetchAll(@QueryParam("apikey") String apiKey,
                                @QueryParam("resource") String uri);

}
