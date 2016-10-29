package tw.com.riko.andrew.rest;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpServer;
import tw.com.riko.andrew.VO.Address;
import tw.com.riko.andrew.VO.Amount;
import tw.com.riko.andrew.VO.Product;
import tw.com.riko.andrew.VO.Warehouse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.*;

// The Java class will be hosted at the URI path "/helloworld"
@Path("helloworld")
public class ProductService {
    // The Java method will process HTTP GET requests
    @GET
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Produces("text/plain")
    public String getClichedMessage() {
        // Return some cliched textual content
        return "Hello World";
    }


    @GET
    @Path("product/{productID}")
    @Produces("text/html; charset=UTF-8")
    public String getProduct(@PathParam("productID") String productID){

        productID = "A0020030007" ;
        List<Amount> amountList = new ArrayList<>();

        Warehouse warehouse2 = new Warehouse("S002", "材料倉", "專門放材料的地方",null);
        Amount amount2 = new Amount(warehouse2, 55, 6,  9);

        amountList.add(amount2);

        Product product = new Product(productID, "PR-410-S" , "PSC" , "K2-3-7" , amountList);

        Gson gson = new Gson();
        String jsonString = gson.toJson(product,Product.class);

        return jsonString ;
    }


}
