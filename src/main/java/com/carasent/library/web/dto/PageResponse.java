package com.carasent.library.web.dto;

import java.util.List;

public record PageResponse<T>(int number, long totalElements, int totalPages, int size, List<T> content) { }
