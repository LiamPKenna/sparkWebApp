package com.liamkenn.courses;

import com.liamkenn.courses.model.CourseIdea;
import com.liamkenn.courses.model.CourseIdeaDAO;
import com.liamkenn.courses.model.SimpleCourseIdeaDAO;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;


import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {
        staticFileLocation("/public");
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

        get("/ideas", (req,res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("ideas", dao.findAll());
            return new ModelAndView(model, "ideas.hbs");
        }, new HandlebarsTemplateEngine());

        post("/ideas", (req, res) -> {
            String title = req.queryParams("title");
            String username = req.cookie("username");
            CourseIdea courseIdea = new CourseIdea(title, username);
            dao.add(courseIdea);
            res.redirect("/ideas");
            return "Signed in";
        } );
    }
}
