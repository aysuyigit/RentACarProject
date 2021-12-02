package com.etiya.rentACar.core.adapters;

import com.etiya.rentACar.core.externalService.FindeksDataProviderService;
import org.springframework.stereotype.Service;

@Service
public class FindeksDataProviderAdapter implements FindeksDataProviderService {

    @Override
    public int findeksPointGenerator() {
        return 900;
    }

}