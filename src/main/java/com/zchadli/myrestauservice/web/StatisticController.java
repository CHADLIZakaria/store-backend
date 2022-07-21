package com.zchadli.myrestauservice.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zchadli.myrestauservice.business.service.StatisticService;
import com.zchadli.myrestauservice.entities.Stats;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
public class StatisticController {
    private final StatisticService statisticService;
    
    @RequestMapping(value="/admin/stats", method = RequestMethod.GET)
    public Stats findStats() {
        return statisticService.getStatistics();
    }
}
