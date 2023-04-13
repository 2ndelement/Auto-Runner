package org._2ndelement.autorunner.dto;

import java.util.List;
import java.util.stream.Collectors;

public interface Convert<T> {

    T convert();

    default List<T> convertList(List<?> sourceList) {
        return sourceList.stream().map(o -> convert()).collect(Collectors.toList());
    }
}