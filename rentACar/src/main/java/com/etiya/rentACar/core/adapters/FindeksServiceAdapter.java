package com.etiya.rentACar.core.adapters;

import org.springframework.stereotype.Service;
import com.etiya.rentACar.core.services.FindeksService;

@Service
public class FindeksServiceAdapter implements FindeksService {

    @Override
    public int findeksPoint() {
        return 900;
    }




}
