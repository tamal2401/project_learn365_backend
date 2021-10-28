package org.learn365.course.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.learn365.course.repository.ChapterVideoRepository;
import org.learn365.course.repository.UserTrialDetailsRepository;
import org.learn365.course.service.UserTrialDetailsService;
import org.learn365.exception.UserTrialDetailException;
import org.learn365.modal.constants.TrialStatus;
import org.learn365.modal.course.entity.UserTrialDetails;
import org.learn365.modal.course.request.TrialPeriodRequest;
import org.learn365.modal.course.response.Recentvideo;
import org.learn365.modal.course.response.TrialPeriodResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.persistence.Tuple;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserTrialDetailsServiceImpl implements UserTrialDetailsService {

    private Integer validity;
    private UserTrialDetailsRepository userTrialRepository;
    private ChapterVideoRepository chapterVideoRepository;

    public UserTrialDetailsServiceImpl(@Value("${learn365.trial.period.validity}") Integer validity,
                                       UserTrialDetailsRepository userTrialRepository,
                                       ChapterVideoRepository chapterVideoRepository) {
        this.validity = validity;
        this.userTrialRepository = userTrialRepository;
        this.chapterVideoRepository = chapterVideoRepository;
    }

    @Override
    public TrialPeriodResponse createTrialData(TrialPeriodRequest trialPeriodRequest) {
        if (null == trialPeriodRequest) {
            throw new IllegalArgumentException("Request is not valid");
        }
        Optional<UserTrialDetails> isuserTrialDetails = Optional.empty();

        isuserTrialDetails = userTrialRepository.findUserTrialDetailsByUserid(trialPeriodRequest.getUserid());
        if (isuserTrialDetails.isPresent()) {
            throw new UserTrialDetailException("User already had " +
                    "registerd for trial video and exiry date is :" + isuserTrialDetails.get().getEndTime());
        }

        UserTrialDetails userTrialDetails = new UserTrialDetails();
        BeanUtils.copyProperties(trialPeriodRequest, userTrialDetails);
        LocalDate startdate = LocalDate.now();
        LocalDate enddate = startdate.plusDays(validity);
        userTrialDetails.setStarttime(startdate);
        userTrialDetails.setEndTime(enddate);
        userTrialDetails.setStatus(TrialStatus.NOTEXPIRED);
        UserTrialDetails saved = null;
        try {
            saved = userTrialRepository.save(userTrialDetails);

        } catch (Exception exception) {
            throw new UserTrialDetailException("Trial Details has not been created");
        }

        TrialPeriodResponse trialperiod = new TrialPeriodResponse();
        BeanUtils.copyProperties(saved, trialperiod);
        return trialperiod;
    }

    @Override
    public TrialPeriodResponse getTrailStatus(Long userid) {
        Optional<UserTrialDetails> isuserTrialDetails = Optional.empty();
        try {
            isuserTrialDetails = userTrialRepository.findUserTrialDetailsByUserid(userid);
        } catch (Exception e) {
            throw new UserTrialDetailException("Sql Exception");
        }
        if (!isuserTrialDetails.isPresent()) {
            throw new UserTrialDetailException("User doesn't have trial information");
        }

        UserTrialDetails userTrialDetails = isuserTrialDetails.get();
        TrialPeriodResponse trialPeriodResponse = new TrialPeriodResponse();
        BeanUtils.copyProperties(userTrialDetails, trialPeriodResponse);
        if (!userTrialDetails.getEndTime().isAfter(LocalDate.now())) {
            trialPeriodResponse.setStatus(TrialStatus.EXPIRED);
        }
        return trialPeriodResponse;
    }

    @Override
    public List<Recentvideo> getTrialVideos(String grade) {
        List<Recentvideo> trialvideos = new ArrayList<>();
        if (StringUtils.isBlank(grade)) {
            throw new IllegalArgumentException("Request is not valid");
        }
        List<Tuple> trialvideostuple = chapterVideoRepository.findTrialVideo(grade);
        if (!CollectionUtils.isEmpty(trialvideostuple)) {
            trialvideos = mapTupleToUserVideo(trialvideostuple);
        }
        return trialvideos;
    }

    public List<Recentvideo> mapTupleToUserVideo(List<Tuple> mostViewedVideotuple) {
        return mostViewedVideotuple.stream().map(t -> new Recentvideo(t.get(0, String.class), t.get(1, String.class),
                t.get(4, String.class), t.get(3, String.class), t.get(2, String.class),
                t.get(5, String.class))).collect(Collectors.toList());
    }
}
