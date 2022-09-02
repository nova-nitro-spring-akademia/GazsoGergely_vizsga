package com.gergely.vizsga_0902.service;

import com.gergely.vizsga_0902.data.CardEntity;
import com.gergely.vizsga_0902.data.GyujtemenyEntity;
import com.gergely.vizsga_0902.data.GyujtemenyRepository;
import com.gergely.vizsga_0902.data.mapper.GyujtemenyEntityMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    @Transactional
    public Gyujtemeny saveWithCards(Gyujtemeny gyujtemeny) {
        GyujtemenyEntity savedGyujtemenyEntity = gyujtemenyRepository.save(gyujtemenyEntityMapper.toGyujtemenyEntityWithCards(gyujtemeny));
        return gyujtemenyEntityMapper.fromGyujtemenyEntityWithCards(savedGyujtemenyEntity);
    }

    public Set<Gyujtemeny> findAll() {
        List<GyujtemenyEntity> gyujtemenyEntityList = gyujtemenyRepository.findAll();
        Set<Gyujtemeny> gyujtemenySet = gyujtemenyEntityList.stream().map(gyujtemenyEntityMapper::fromGyujtemenyEntity).collect(Collectors.toSet());
        return gyujtemenySet;
    }

    public Set<Gyujtemeny> findAllWithCards() {
        List<GyujtemenyEntity> gyujtemenyEntityList = gyujtemenyRepository.findAll();
        Set<Gyujtemeny> gyujtemenySet = gyujtemenyEntityList.stream().map(gyujtemenyEntityMapper::fromGyujtemenyEntityWithCards).collect(Collectors.toSet());
        return gyujtemenySet;
    }

    public Gyujtemeny finddById(int gyujtemeny_id) {
        GyujtemenyEntity gyujtemenyEntity = gyujtemenyRepository.findById(Long.valueOf(gyujtemeny_id)).orElseThrow();
        return gyujtemenyEntityMapper.fromGyujtemenyEntity(gyujtemenyEntity);
    }
}
