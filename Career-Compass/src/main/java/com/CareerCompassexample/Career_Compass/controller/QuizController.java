//package com.CareerCompassexample.Career_Compass.controller;
//
//import com.CareerCompassexample.Career_Compass.dto.QuizQuestion;
//import com.CareerCompassexample.Career_Compass.service.QuizService;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpSession;
//
//@Controller
//public class QuizController {
//
//    private final QuizService quizService;
//
//    public QuizController(QuizService quizService) {
//        this.quizService = quizService;
//    }
//
//    @GetMapping("/start-quiz")
//    public String startQuiz(@RequestParam(defaultValue = "general") String topic,
//                            HttpSession session, Model model) {
//
//        QuizQuestion question = quizService.fetchQuiz(topic);
//        session.setAttribute("quizQuestion", question);
//        model.addAttribute("question", question);
//        model.addAttribute("topic", topic);
//        return "quiz"; // quiz.html template
//    }
//
//    @PostMapping("/submit-quiz")
//    public String submitQuiz(HttpServletRequest request, HttpSession session, Model model) {
//        QuizQuestion question = (QuizQuestion) session.getAttribute("quizQuestion");
//        if (question == null) {
//            model.addAttribute("error", "Session expired. Please restart the quiz.");
//            return "error"; // error.html template
//        }
//
//        String userAnswer = request.getParameter("answer");
//        model.addAttribute("question", question);
//        model.addAttribute("userAnswer", userAnswer);
//
//        // No automatic scoring since API doesn't provide a correct answer
//        session.removeAttribute("quizQuestion");
//        return "result"; // result.html template
//    }
//}
