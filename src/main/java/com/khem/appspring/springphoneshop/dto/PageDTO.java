package com.khem.appspring.springphoneshop.dto;

import java.util.List;

import lombok.Data;

@Data
public class PageDTO {
    private List<?> list;

    private PaginationDTO pagination;

    /*
     * public PageDTO(Page<?> page) {
     * PageMapper.INSTANCE.toDTO(page);
     * 
     * // this.list = page.getContent();
     * 
     * 
     * this.pagenation = PagenationDTO.builder()
     * .last(page.isLast())
     * .first(page.isFirst()).build();
     */

    // this.pagenation=new PagenationDTO();this.pagenation.setEmpty(page.isEmpty());this.pagenation.setFirst(page.isFirst());this.pagenation.setLast(page.isLast());this.pagenation.setNumber(page.getNumber());this.pagenation.setNumberOfElements(page.getNumberOfElements());this.pagenation.setSize(page.getSize());this.pagenation.setTotalElement(page.getTotalElements());this.pagenation.setTotalPage(page.getTotalPages());
    //  }
}
