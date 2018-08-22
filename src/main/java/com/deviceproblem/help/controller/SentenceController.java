package com.deviceproblem.help.controller;

import com.deviceproblem.help.model.Sentence;
import com.deviceproblem.help.repository.SentenceRepository;
import com.deviceproblem.help.util.EmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SentenceController {
    @Autowired
    private SentenceRepository sentenceRepository;
    @Autowired
    private EmailServiceImpl emailService;

    @RequestMapping(value = "/sendSentence", method = RequestMethod.GET)
    public String sendMessage(ModelMap map) {
        map.addAttribute("sentence",new Sentence());
        return "send";
    }

    @RequestMapping(value = "/sendSentences", method = RequestMethod.POST)
    public String message(@ModelAttribute("message") Sentence sentence) {
        emailService.sendSimpleMessages("hovsoyans@mail.ru","hello You have sentence", sentence.getSentence(),sentence.getUserUsername());
        sentenceRepository.save(sentence);
        return "redirect:/sendSentence";

    }
}
