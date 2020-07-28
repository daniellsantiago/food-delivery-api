package com.daniellsantiago.fooddeliveryapi.domain.service;

import com.daniellsantiago.fooddeliveryapi.domain.filter.DailySaleFilter;
import com.daniellsantiago.fooddeliveryapi.domain.model.dto.DailySale;

import java.util.List;

public interface SalesQueryService {
    List<DailySale> consultDailySales(DailySaleFilter dailySaleFilter, String timeOffset);
}
