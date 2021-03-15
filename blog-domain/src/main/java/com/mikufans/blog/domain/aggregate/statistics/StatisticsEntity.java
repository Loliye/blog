package com.mikufans.blog.domain.aggregate.statistics;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StatisticsEntity {
    private Long articles;
    private Long comments;
    private Long links;
    private Long attaches;
}
