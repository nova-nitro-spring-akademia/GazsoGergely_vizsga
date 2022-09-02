package com.gergely.vizsga_0902.service;

import com.gergely.vizsga_0902.data.CardEntity;
import com.gergely.vizsga_0902.data.GyujtemenyEntity;
import com.gergely.vizsga_0902.data.GyujtemenyRepository;
import com.gergely.vizsga_0902.data.mapper.GyujtemenyEntityMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class GyujtemenyService {

    GyujtemenyEntityMapper gyujtemenyEntityMapper;

    GyujtemenyRepository gyujtemenyRepository;

    public GyujtemenyService(GyujtemenyEntityMapper gyujtemenyEntityMapper, GyujtemenyRepository gyujtemenyRepository) {
        this.gyujtemenyEntityMapper = gyujtemenyEntityMapper;
        this.gyujtemenyRepository = gyujtemenyRepository;
    }

    public Gyujtemeny save(Gyujtemeny gyujtemeny) {
        GyujtemenyEntity savedGyujtemenyEntity = gyujtemenyRepository.save(gyujtemenyEntityMapper.toGyujtemenyEntity(gyujtemeny));
        return gyujtemenyEntityMapper.fromGyujtemenyEntity(savedGyujtemenyEntity);
    }
}
