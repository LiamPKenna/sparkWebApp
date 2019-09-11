package com.liamkenn.courses;

import static spark.Spark.get;

public class Main {
    public static void main(String[] args) {
        get("/", (req, res) -> "<a href=\"./hello\"><h1>sup dog</h1></a>");
        get("/hello", (req, res) -> "Hello Cruel World");
    }
}
