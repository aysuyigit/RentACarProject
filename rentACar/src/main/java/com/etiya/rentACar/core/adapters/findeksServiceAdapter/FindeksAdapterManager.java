package com.etiya.rentACar.core.adapters.findeksServiceAdapter;

import com.etiya.rentACar.business.externalService.FakeFindeksService;
import org.springframework.stereotype.Service;

@Service
public class FindeksAdapterManager implements FinancialDataService{

    @Override
    public int getFindeksScore(int userId) {
        FakeFindeksService fakeFindeksService = new FakeFindeksService();
        return fakeFindeksService.getFindeksScore(userId);
    }
}