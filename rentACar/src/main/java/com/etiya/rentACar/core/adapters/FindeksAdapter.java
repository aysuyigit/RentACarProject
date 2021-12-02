package com.etiya.rentACar.core.adapters;

import com.etiya.rentACar.business.externalService.FakeFindeksService;
import org.springframework.stereotype.Service;

@Service
public class FindeksAdapter implements FinancialDataService{

    @Override
    public int getFindeksScore(int userId) {
        FakeFindeksService fakeFindeksService = new FakeFindeksService();
        return fakeFindeksService.getFindeksScore(userId);
    }
}