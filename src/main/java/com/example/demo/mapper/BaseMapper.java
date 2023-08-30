package com.example.demo.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

public abstract  class BaseMapper {
    
    protected ModelMapper modelMapper;

    public BaseMapper() {
        this.modelMapper = new ModelMapper();
    }

    protected <S, D> D map(S source, Class<D> destinationClass) {
        return modelMapper.map(source, destinationClass);
    }

    protected <S, D> List<D> mapList(List<S> sourceList, Class<D> destinationClass) {
        return sourceList.stream()
                .map(source -> map(source, destinationClass))
                .collect(Collectors.toList());
    }
}
