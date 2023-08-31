package com.example.demo.mapper;

import com.example.demo.config.ModelMapperConfig;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public abstract  class BaseMapper {

    @Autowired
    protected ModelMapper modelMapper;

    protected <S, D> D map(S source, Class<D> destinationClass) {
        return modelMapper.map(source, destinationClass);
    }

    protected <S, D> List<D> mapList(List<S> sourceList, Class<D> destinationClass) {
        return sourceList.stream()
                .map(source -> map(source, destinationClass))
                .collect(Collectors.toList());
    }
}
