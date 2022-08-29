package com.hotdread.adapter.api.virustotal.service;

import com.hotdread.adapter.api.http.model.AnalysisREQ;
import com.hotdread.adapter.api.http.model.DTO.AnalysisRES;

import java.util.List;

public interface IVirusTotalService {

    List<AnalysisRES> sendRequest(AnalysisREQ analysisREQ) throws Exception;

}
