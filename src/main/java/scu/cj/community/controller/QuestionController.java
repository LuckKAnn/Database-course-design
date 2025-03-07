package scu.cj.community.controller;

import scu.cj.community.dto.CommentDTO;
import scu.cj.community.dto.QuestionDTO;
import scu.cj.community.enums.CommentTypeEnum;
import scu.cj.community.exception.CustomizeErrorCode;
import scu.cj.community.exception.CustomizeException;
import scu.cj.community.model.Advertisement;
import scu.cj.community.model.Question;
import scu.cj.community.service.AdService;
import scu.cj.community.service.CommentService;
import scu.cj.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private AdService adService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") String id, Model model) {
        Long questionId = null;
        try {
            questionId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new CustomizeException(CustomizeErrorCode.INVALID_INPUT);
        }
        QuestionDTO questionDTO = questionService.getById(questionId);
        List<QuestionDTO> relatedQuestions = questionService.selectRelated(questionDTO);
        List<CommentDTO> comments = commentService.listByTargetId(questionId, CommentTypeEnum.QUESTION);
        List<Question> recommands = questionService.getRecommand();
        List<Advertisement> advs = adService.getAll();
        //累加阅读数
        questionService.incView(questionId);
        model.addAttribute("question", questionDTO);
        model.addAttribute("comments", comments);
        model.addAttribute("advs", advs);
        model.addAttribute("recommands",recommands);
        model.addAttribute("relatedQuestions", relatedQuestions);
        return "question";
    }
}
