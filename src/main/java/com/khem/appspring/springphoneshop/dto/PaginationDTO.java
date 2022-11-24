package com.khem.appspring.springphoneshop.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaginationDTO {

    private int NumberOfElements;
    private int number;
    private int size;
    private long totalElement;
    private int totalPage;
    private boolean empty;
    private boolean first;
    private boolean last;

}
