package org.learn365.test.util;

import org.learn365.modal.test.entity.OptionsEntity;

import java.util.Comparator;

public class OptionListComparator implements Comparator<OptionsEntity> {

    @Override
    public int compare(OptionsEntity q1, OptionsEntity q2) {
        if(q1.getOptionSequence() > q2.getOptionSequence()){
            return 1;
        }else if(q1.getOptionSequence() < q2.getOptionSequence()){
            return -1;
        }
        return 0;
    }
}
