package org.learn365.test.util;

import org.learn365.modal.test.entity.QuestionEntity;

import java.util.Comparator;

public class QuestionListComparator implements Comparator<QuestionEntity> {
    @Override
    public int compare(QuestionEntity q1, QuestionEntity q2) {
        if(q1.getQuestionOrder() > q2.getQuestionOrder()){
            return 1;
        }else if(q1.getQuestionOrder() < q2.getQuestionOrder()){
            return -1;
        }
        return 0;
    }
}
