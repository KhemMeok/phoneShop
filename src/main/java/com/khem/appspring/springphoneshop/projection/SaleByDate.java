package com.khem.appspring.springphoneshop.projection;

import java.time.LocalDate;

public interface SaleByDate {
    LocalDate getsoldDate();
    Long getProductId();
    String getProductName();
    Long getTotal();
    Double getAmount();

}
