package com.liamkenn.courses;

import com.liamkenn.courses.model.CourseIdeaDAO;
import com.liamkenn.courses.model.SimpleCourseIdeaDAO;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;


import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {

        CourseIdeaDAO dao = new SimpleCourseIdeaDAO();

        get("/", (req, res) -> {
            Map<String, String> model = new HashMap<>();
            String username = req.cookie("username");
            model.put("username", username);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        post("/sign-in", (req, res) -> {
            String username = req.queryParams("username");
            res.cookie("username", username);
            res.redirect("/");
            return "Signed in";
        } );
    }
}
