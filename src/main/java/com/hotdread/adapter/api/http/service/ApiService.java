package com.hotdread.adapter.api.http.service;

import com.hotdread.adapter.api.http.model.AnalysisREQ;
import com.hotdread.adapter.api.http.model.DTO.AnalysisRES;

import java.util.List;

public interface ApiService {

    List<AnalysisRES> sendRequest(AnalysisREQ analysisREQ) throws Exception;

}
