package com.daniellsantiago.fooddeliveryapi.api.controller;

import com.daniellsantiago.fooddeliveryapi.domain.filter.DailySaleFilter;
import com.daniellsantiago.fooddeliveryapi.domain.model.dto.DailySale;
import com.daniellsantiago.fooddeliveryapi.domain.service.SalesQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/statistic")

public class StatisticsController {

    @Autowired
    private SalesQueryService salesQueryService;

    @GetMapping("/daily-sales")
    public List<DailySale> findDailySales(DailySaleFilter filter,
                                          @RequestParam(required = false, defaultValue = "+00:00")String timeOffset) {
        return salesQueryService.consultDailySales(filter, timeOffset);
    }
}
