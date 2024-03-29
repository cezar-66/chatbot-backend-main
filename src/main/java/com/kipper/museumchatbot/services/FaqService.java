package com.kipper.museumchatbot.services;

import com.kipper.museumchatbot.domain.FaqAnswer;
import com.kipper.museumchatbot.utils.FaqAnswers;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class FaqService {
    final private FaqAnswers faqAnswers = new FaqAnswers();

    public String getAnswer(String question){
        final String[] words = question.toLowerCase().split("\\s+");
        final List<String> wordsList = Arrays.stream(words).map(String::toLowerCase).toList();

        for (FaqAnswer entry : faqAnswers.getAnswers()) {
            for (String keyword : entry.getKeywords()) {
                if (wordsList.contains(keyword.toLowerCase())) {
                    return entry.getAnswer();
                }
            }
        }

        return faqAnswers.getDefaultAnswer();
    }
}
