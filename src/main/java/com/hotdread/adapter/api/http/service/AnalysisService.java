package com.hotdread.adapter.api.http.service;

import com.hotdread.adapter.api.http.model.AnalysisREQ;
import com.hotdread.adapter.api.http.model.DTO.AnalysisRES;
import com.hotdread.adapter.api.http.model.ReportREQ;
import com.hotdread.adapter.api.utils.exception.AnalysisException;
import com.hotdread.adapter.api.virustotal.model.VirusTotalResponse;
import com.hotdread.adapter.api.virustotal.service.IVirusTotalService;
import com.hotdread.adapter.api.virustotal.service.VirusTotalService;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class AnalysisService implements IVirusTotalService {

    @Inject
    @RestClient
    VirusTotalService virusTotalService;

    @ConfigProperty(name = "virustotal.apiKey")
    String apiKey;

    @Override
    public List<AnalysisRES> sendRequest(AnalysisREQ request) throws Exception {
        List<AnalysisRES> analysisRES = new ArrayList<>();
        try {
            if (request.getUrls().isEmpty()) {
                throw new AnalysisException("Urls is empty");
            }
            for (String url : request.getUrls()) {
                if (url.isBlank()) {
                    throw new AnalysisException("Url is blank");
                }
                VirusTotalResponse response = virusTotalService.fetchAll(apiKey, url);
                AnalysisRES urlInfo = new AnalysisRES();
                urlInfo.setUrl(url);
                urlInfo.setScore(response.getTotal() - response.getPositives());
                analysisRES.add(urlInfo);
            }
        } catch (Exception e) {
            throw new Exception(e);
        }
        return analysisRES;
    }

    @Override
    public List<AnalysisRES> sendRequest(ReportREQ request) throws Exception {
        List<AnalysisRES> analysisRES = new ArrayList<>();
        try {
            VirusTotalResponse response = virusTotalService.fetchAll(apiKey);
            AnalysisRES urlInfo = new AnalysisRES();
            urlInfo.setUrl(request.description);
            urlInfo.setScore(response.getTotal() - response.getPositives());
            analysisRES.add(urlInfo);
        } catch (Exception e) {
            throw new Exception(e);
        }
        return analysisRES;
    }

}
