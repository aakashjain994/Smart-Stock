package com.codingblocks.stocks;

import java.io.Serializable;

/**
 * Created by nagarro on 12/09/15.
 */
public class Stock implements Serializable{
    String companyName;
    String ticker;
    double currentPrice;
    double percentChange;
    String currency;
}
