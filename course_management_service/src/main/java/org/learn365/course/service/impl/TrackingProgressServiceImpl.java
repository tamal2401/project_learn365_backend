package org.learn365.course.service.impl;

import org.learn365.course.repository.*;
import org.learn365.course.service.TrackingProgressService;
import org.learn365.exception.CoursePortFolioNotFoundException;
import org.learn365.exception.UserCourseInprogressException;
import org.learn365.modal.constants.StudentProgress;
import org.learn365.modal.course.TestResult;
import org.learn365.modal.course.entity.SubscribedChapter;
import org.learn365.modal.course.request.TrackStudentProgressRequest;
import org.learn365.modal.course.response.Recentvideo;
import org.learn365.modal.course.response.SubscribedSubjectReport;
import org.learn365.modal.course.response.UserVideos;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.persistence.Tuple;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TrackingProgressServiceImpl implements TrackingProgressService
{

    private static Logger log = LoggerFactory.getLogger(
            StandardServiceImpl.class);

    private SubscribedGradeRepository subscribedGradeRepository;
    private SubscribedSubjectRepository subscribedSubjectRepository;
    private SubscribedChapterRepository subscribedChapterRepository;
    private SubscribedvideoRepository subscribedvideoRepository;

    public TrackingProgressServiceImpl(
            SubscribedGradeRepository subscribedGradeRepository,
            SubscribedSubjectRepository subscribedSubjectRepository,
            SubscribedChapterRepository subscribedChapterRepository,
            SubscribedvideoRepository subscribedvideoRepository)
    {
        this.subscribedGradeRepository = subscribedGradeRepository;
        this.subscribedSubjectRepository = subscribedSubjectRepository;
        this.subscribedChapterRepository = subscribedChapterRepository;
        this.subscribedvideoRepository = subscribedvideoRepository;
    }

    @Override
    public Boolean updateProgress(TrackStudentProgressRequest trackservice)
    {
        Boolean isprogresstracked = false;
        switch (trackservice.getTableName())
        {
            case PROFILEGRADE:
                try
                {
                    getuserGradeUsage(trackservice);
                    subscribedGradeRepository.updateStudentProgress(
                            trackservice.getTimespent(),
                            trackservice.getProgressStatus(),
                            trackservice.getId(), trackservice.getUserId());
                    isprogresstracked = true;
                } catch (Exception e)
                {
                    throw new UserCourseInprogressException(
                            "Failed to update data for Grade table");
                }
                break;
            case PROFILESUBJECT:
                try
                {
                    getuserSubjectUsage(trackservice);
                    subscribedSubjectRepository.updateStudentProgress(
                            trackservice.getTestflag(),
                            trackservice.getTimespent(),
                            trackservice.getProgressStatus(),
                            trackservice.getId(), trackservice.getUserId());
                    isprogresstracked = true;
                } catch (Exception e)
                {
                    throw new UserCourseInprogressException(
                            "Failed to update data for subscribed PROFILESUBJECT table");
                }
                break;

            case PROFILECHAPTER:
                try
                {
                    getuserChapterUsage(trackservice);
                    subscribedChapterRepository.updateStudentProgress(
                            trackservice.getTestflag(),
                            trackservice.getTimespent(),
                            trackservice.getProgressStatus(),
                            trackservice.getId(), trackservice.getUserId());
                    isprogresstracked = true;
                } catch (Exception e)
                {
                    throw new UserCourseInprogressException(
                            "Failed to update data for subscribed PROFILECHAPTER table");
                }
                break;
            case PROFILECHAPTERVIDEO:
                try
                {
                    getuserChapterVideoUsage(trackservice);
                    subscribedvideoRepository.updateStudentProgress(
                            trackservice.getTestflag(),
                            trackservice.getTimespent(),
                            trackservice.getProgressStatus(),
                            trackservice.getId(), trackservice.getUserId(),
                            LocalDateTime.now());
                    if (trackservice.getLastVideo())
                    {
                        log.info(
                                "Got the lastflag up for userid: {} and video {}:",
                                trackservice.getUserId(), trackservice.id);
                        Long subchapterid = subscribedvideoRepository.findSubscribedChapterById(
                                trackservice.getId());
                        subscribedChapterRepository.updateChapterProgress(
                                StudentProgress.COMPLETE,
                                subchapterid);

                    }
                    isprogresstracked = true;
                } catch (Exception e)
                {
                    throw new UserCourseInprogressException(
                            "Failed to update data for subscribed PROFILECHAPTERVIDEO table: " + e.getMessage());
                }
                break;

        }
        return isprogresstracked;
    }

    private void getuserGradeUsage(
            TrackStudentProgressRequest trackserviceRequest)
    {
        Optional<ProgressingFields> isSubscribedgrade = Optional.empty();
        try
        {
            isSubscribedgrade = subscribedGradeRepository.findByIdAndUserId(
                    trackserviceRequest.getId(),
                    trackserviceRequest.getUserId());
        } catch (Exception e)
        {
            log.error(
                    "Unable to get grade entry for user  :{} and exception :{}",
                    trackserviceRequest.getUserId(), e);
            throw new UserCourseInprogressException(
                    "Failed to update grade data exception e: " + e.getMessage());
        }
        isSubscribedgrade.ifPresent(subscribedgrade ->
        {
            Long earlierusage = subscribedgrade.getTimespent();
            Long nextusage = trackserviceRequest.getTimespent();
            trackserviceRequest.setTimespent(
                    calculateUsage(earlierusage, nextusage));
            if (!subscribedgrade.getStudentprogress()
                    .equals(StudentProgress.COMPLETE))
            {
                if (trackserviceRequest.getTestflag() == true)
                {
                    trackserviceRequest.setProgressStatus(
                            StudentProgress.COMPLETE);
                } else
                {
                    trackserviceRequest.setProgressStatus(
                            StudentProgress.INCOMPLETE);
                }
            }
        });
    }

    private void getuserSubjectUsage(
            TrackStudentProgressRequest trackserviceRequest)
    {
        Optional<ProgressingFields> isSubscribedSubject = Optional.empty();
        try
        {
            isSubscribedSubject = subscribedSubjectRepository.findByIdAndUserId(
                    trackserviceRequest.getId(),
                    trackserviceRequest.getUserId());
        } catch (Exception e)
        {
            log.error(
                    "unable to get data for user: {} for table {} and exception is {}",
                    trackserviceRequest.getUserId(),
                    trackserviceRequest.getTableName(), e);

            throw new UserCourseInprogressException(
                    "Failed to update Subject data exception e: {}" +
                            e.getMessage());
        }
        isSubscribedSubject.ifPresent(subscribedSubject ->
        {
            Long earlierusage = subscribedSubject.getTimespent();
            Long nextusage = trackserviceRequest.getTimespent();
            trackserviceRequest.setTimespent(
                    calculateUsage(earlierusage, nextusage));
            if (!(subscribedSubject.getTestCompleted() && subscribedSubject.getStudentprogress()
                    .equals(StudentProgress.COMPLETE)))
            {
                if (trackserviceRequest.getTestflag() == true)
                {
                    trackserviceRequest.setProgressStatus(
                            StudentProgress.COMPLETE);
                } else
                {
                    trackserviceRequest.setProgressStatus(
                            StudentProgress.INCOMPLETE);
                }
            }
        });
    }

    private void getuserChapterUsage(
            TrackStudentProgressRequest trackserviceRequest)
    {
        Optional<ProgressingFields> isSubscribedSubject = Optional.empty();
        try
        {
            isSubscribedSubject = subscribedChapterRepository.findByIdAndUserId(
                    trackserviceRequest.getId(),
                    trackserviceRequest.getUserId());
        } catch (Exception e)
        {
            log.error(
                    "unable to get data for user: {} for table {} and exception is {}",
                    trackserviceRequest.getUserId(),
                    trackserviceRequest.getTableName(), e);

            throw new UserCourseInprogressException(
                    "Failed to update Subject data exception e: {}" + e.getMessage());
        }
        isSubscribedSubject.ifPresent(subscribedchapter ->
        {
            Long earlierusage = subscribedchapter.getTimespent();
            Long nextusage = trackserviceRequest.getTimespent();
            trackserviceRequest.setTimespent(
                    calculateUsage(earlierusage, nextusage));
            if (!(subscribedchapter.getTestCompleted() && subscribedchapter.getStudentprogress()
                    .equals(StudentProgress.COMPLETE)))
            {
                if (trackserviceRequest.getTestflag() == true)
                {
                    trackserviceRequest.setProgressStatus(
                            StudentProgress.COMPLETE);
                } else
                {
                    trackserviceRequest.setProgressStatus(
                            StudentProgress.INCOMPLETE);
                }
            }
        });
    }

    private void getuserChapterVideoUsage(
            TrackStudentProgressRequest trackserviceRequest)
    {
        Optional<ProgressingFields> isSubscribedSubject = Optional.empty();
        try
        {
            isSubscribedSubject = subscribedvideoRepository.findByIdAndUserId(
                    trackserviceRequest.getId(),
                    trackserviceRequest.getUserId());
        } catch (Exception e)
        {
            log.error(
                    "unable to get data for user: {} for table {} and exception is {}",
                    trackserviceRequest.getUserId(),
                    trackserviceRequest.getTableName(), e);

            throw new UserCourseInprogressException(
                    "Failed to update Subject data exception e: {}" + e.getMessage());
        }
        isSubscribedSubject.ifPresent(subscribedChvideo ->
        {
            Long earlierusage = subscribedChvideo.getTimespent();
            Long nextusage = trackserviceRequest.getTimespent();
            trackserviceRequest.setTimespent(
                    calculateUsage(earlierusage, nextusage));
            if (!(subscribedChvideo.getTestCompleted() && subscribedChvideo.getStudentprogress()
                    .equals(StudentProgress.COMPLETE)))
            {
                if (trackserviceRequest.getTestflag() == true)
                {
                    trackserviceRequest.setProgressStatus(
                            StudentProgress.COMPLETE);
                } else
                {
                    trackserviceRequest.setProgressStatus(
                            StudentProgress.INCOMPLETE);
                }
            }
        });
    }

    private Long calculateUsage(Long earlierusage, Long nextUsage)
    {
        Long totalUsage = nextUsage;
        if (nextUsage == null || nextUsage == 0)
        {
            nextUsage = 0L;
        }
        if (null != earlierusage && earlierusage > 0)
        {
            totalUsage = earlierusage + nextUsage;
        }
        return totalUsage;

    }

    @Override
    public List<SubscribedSubjectReport> getSubscribedSubject(Long userId,
                                                              String gradeName)
    {
        if (userId == null || gradeName == null)
        {
            log.info(
                    "userId : {} and gradeName: {} are required field to get subscribed subject",
                    userId, gradeName);
            throw new IllegalArgumentException(
                    "userId and gradeName are required field");
        }
        List<SubscribedSubjectReport> subscribedSubjectReport = new ArrayList<>();
        try
        {
            subscribedSubjectReport = subscribedSubjectRepository.findSubscribedSubjectByGradeIdAndUserId(
                    gradeName, userId);
        } catch (Exception e)
        {
            log.error(
                    "Unable to get subscribed subject for user id {} and grade id {} exception {}",
                    userId, gradeName, e);

            throw new CoursePortFolioNotFoundException(
                    "Unable to get subscribed subject for user id :" + userId + " and grade id " + gradeName + " exception " + e.getMessage());
        }
        return subscribedSubjectReport;
    }

    @Override
    public UserVideos getRecentVideos(final Long userid)
    {
        UserVideos userVideos = new UserVideos();
        Pageable recent10Video = PageRequest.of(0, 10);
        /** Most Recent watched videos **
         *
         */
        List<Long> recntlywatchedVideo = new ArrayList<>();
        try
        {
            recntlywatchedVideo = subscribedvideoRepository.findvideoIdForUser(
                    userid, recent10Video);
        } catch (Exception e)
        {
            log.error(
                    "Exception to find recentvideos for user id :{} and exception is {}",
                    userid, e);
        }
        log.info("Recent video details: {} for userId is :{}",
                recntlywatchedVideo, userid);
        if (!CollectionUtils.isEmpty(recntlywatchedVideo))
        {
            List<Tuple> recentvideostuple = null;
            try
            {
                recentvideostuple = subscribedSubjectRepository.findRecentVideos(
                        recntlywatchedVideo);
            } catch (Exception e)
            {
                log.error(
                        "Exception to find recentvideos for user id :{} and exception is {}",
                        userid, e);
            }
            if (!CollectionUtils.isEmpty(recentvideostuple))
            {
                userVideos.setRecentvideo(
                        mapTupleToUserVideo(recentvideostuple));
            }
        }

        /** Most viewed videos **
         *
         */
        List<Long> mostViewedVideo = null;
        try
        {
            mostViewedVideo = subscribedvideoRepository.findvideoIdByTimeSpent(
                    userid, recent10Video);
        } catch (Exception e)
        {
            log.error(
                    "Exception to find MostViewed for user id :{} and exception is {}",
                    userid, e);
        }
        log.info("MostViewedVideo video details: {} for userId is :{}",
                recntlywatchedVideo, userid);
        if (!CollectionUtils.isEmpty(mostViewedVideo))
        {
            List<Tuple> mostViewedVideotuple = null;
            try
            {
                mostViewedVideotuple = subscribedSubjectRepository.findRecentVideos(
                        mostViewedVideo);
            } catch (Exception e)
            {
                log.error(
                        "Exception to find MostViewed for user id :{} and exception is {}",
                        userid, e);
            }
            if (!CollectionUtils.isEmpty(mostViewedVideotuple))
            {
                userVideos.setMostviewedVideo(
                        mapTupleToUserVideo(mostViewedVideotuple));
            }
        }

        //Recommended videos

        List<Long> recommendedVideos = null;
        try
        {
            recommendedVideos = subscribedvideoRepository.findvideoIdBytestStatus(
                    userid, TestResult.FAILED, recent10Video);
        } catch (Exception e)
        {
            log.error(
                    "Exception to find MostViewed for user id :{} and exception is {}",
                    userid, e);
        }
        log.info("Recommended video details: {} for userId is :{}",
                recommendedVideos, userid);
        if (!CollectionUtils.isEmpty(recommendedVideos))
        {
            List<Tuple> recommendedVideosTuple = null;
            try
            {
                recommendedVideosTuple = subscribedSubjectRepository.findRecentVideos(
                        recommendedVideos);
            } catch (Exception e)
            {
                log.error(
                        "Exception to find Recommended for user id :{} and exception is {}",
                        userid, e);
            }
            if (!CollectionUtils.isEmpty(recommendedVideosTuple))
            {
                userVideos.setRecommendedVideo(
                        mapTupleToUserVideo(recommendedVideosTuple));
            }
        }

        return userVideos;
    }

    public List<Recentvideo> mapTupleToUserVideo(
            List<Tuple> mostViewedVideotuple)
    {
        return mostViewedVideotuple.stream()
                .map(t -> new Recentvideo(t.get(0, String.class),
                        t.get(2, String.class), t.get(4, String.class),
                        t.get(5, String.class), t.get(8, String.class),
                        t.get(7, String.class))).collect(Collectors.toList());
    }

}
