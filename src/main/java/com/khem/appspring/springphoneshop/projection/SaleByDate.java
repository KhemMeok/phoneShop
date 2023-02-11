package com.khem.appspring.springphoneshop.projection;

import java.time.LocalDate;

public interface SaleByDate {
    LocalDate getSoldDate();
    Long getProductId();
    String getProductName();
    Long getTotal();
    Double getAmount();

}
