package org.example;

import spark.Request;
import spark.Response;

import static spark.Spark.*;

public class SparkWebServer {

    public static void main(String... args){
        port(getPort());
        staticFileLocation("/public");
        get("/hello", (req, res) -> "Hello, Docker!");
        get("/collatzsequence",  (req, res) -> {
            int value = Integer.parseInt(req.queryParams("value"));
            String operation = "collatzsequence";
            String answer = Service.calculateCollatz(value);
            return "" +
                    "{\n" +
                    "\t\"operation\": \"" + operation + "\",\n" +
                    "\t\"input\": " + value + ",\n"+
                    "\t\"output\": \"" + answer + "\"\n" +
                    "}";
        });
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }

}
