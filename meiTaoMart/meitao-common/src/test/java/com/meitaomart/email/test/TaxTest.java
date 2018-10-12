package com.meitaomart.email.test;

import java.util.*;

import org.junit.Test;

import com.meitaomart.common.utils.TaxUtils;
import com.meitaomart.common.utils.Taxjar;
import com.taxjar.exception.TaxjarException;
import com.taxjar.model.taxes.TaxResponse;

public class TaxTest {
	@Test
	public void taxTest() {
		System.out.println( "=================MeiTaoMart TaxJar Service================ " );
        System.out.println( "===============Test start===============" );
        System.out.println( "===============Test start===============" );     
        System.out.println( "===============Let's BURN IT OUT!===============" );
        
        //authentication connect to taxjar
        Taxjar client = new Taxjar("fc160786287b446ea1165ea88edcb268");
        
        //fc160786287b446ea1165ea88edcb268
        
        
        /**
         * List all tax categories
         */
        
//        try {
//            CategoryResponse res = client.categories();
//        } catch (TaxjarException e) {
//            e.printStackTrace();
//        }
        
        /**
         * List tax rates for a location by zip/postal code
         */
        
//        try {
//            Map<String, String> params = new HashMap<>();
//            params.put("country", "US");
//            params.put("city", "Matawan");
//            params.put("street", "3996 County Rd 516");
//            RateResponse res = client.ratesForLocation("07747", params);
//            System.out.println("rate for location: "+res.rate.getCountryRate());
//        } catch (TaxjarException e) {
//            e.printStackTrace();
//        }
        
        /**
         * Calculate sales tax for an order
         */
        
        try {
            Map<String, Object> params = new HashMap<>();
            //send item from
            params.put("from_country", "US");
            params.put("from_zip", "07747");
            params.put("from_state", "NJ");
            params.put("from_city", "Matawan");
            params.put("from_street", "3996 Highway 516");
            params.put("to_country", "US");
            params.put("to_zip", "02144");
            params.put("to_state", "MA");
            params.put("to_city", "Boston");
            params.put("to_street", "5500 Wabash ave, Apt1");
            params.put("amount", 23.0);
            params.put("shipping", 5.99);

            List<Map> nexusAddresses = new ArrayList();
            Map<String, Object> nexusAddress = new HashMap<>();
            nexusAddress.put("country", "US");
            nexusAddress.put("zip", "02144");
            nexusAddress.put("state", "MA"); //收货地址      
            nexusAddresses.add(nexusAddress);

//            List<Map> lineItems = new ArrayList();
//            Map<String, Object> lineItem = new HashMap<>();
//            lineItem.put("id", 2);
//            lineItem.put("quantity", 1);
//            lineItem.put("product_tax_code", "30070");
//            lineItem.put("unit_price", 15);
//            lineItem.put("discount", 0);
//            lineItems.add(lineItem);

            params.put("nexus_addresses", nexusAddresses);
           // params.put("line_items", lineItems);

            TaxResponse res = client.taxForOrder(params);
            
            System.out.println("tax we charge: "+res.tax.getAmountToCollect());
            float total=res.tax.getAmountToCollect()+res.tax.getOrderTotalAmount();
            System.out.println("total amount we charge: "+total);
        } catch (TaxjarException e) {
            e.printStackTrace();
        }
	}
}
