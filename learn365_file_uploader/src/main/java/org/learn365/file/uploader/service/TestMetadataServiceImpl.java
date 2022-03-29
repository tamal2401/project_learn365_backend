package org.learn365.file.uploader.service;

import org.learn365.file.uploader.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.lang.invoke.MethodHandles;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TestMetadataServiceImpl implements TestMetadataService {

    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass().getName());

    @Value("${cloud.service.course}")
    String courseServiceAddress;

    @Autowired
    @Qualifier("customTemplate")
    RestTemplate restTemplate;

    private static Map<String, Map<String, Map<Identifier, Set<Identifier>>>> standardResponseMap = new HashMap<>();

    @Override
    public Set<String> getSubjectSet() throws Exception {
        if (standardResponseMap.isEmpty()) loadData();
        return standardResponseMap.entrySet()
                .stream()
                .flatMap(set -> set.getValue().keySet().stream())
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> gradeSet() throws Exception {
        if (standardResponseMap.isEmpty()) loadData();
        return standardResponseMap.keySet();
    }

    @Override
    public List<Identifier> getChapterList(String grade, String subject) {
        return standardResponseMap.entrySet()
                .stream()
                .filter(each -> each.getKey().equals(grade))
                .map(set -> set.getValue().entrySet().stream())
                .flatMap(stream -> stream.filter(each -> each.getKey().equals(subject))
                        .flatMap(set -> set.getValue().keySet().stream()))
                .sorted(Comparator.comparing(Identifier::getKey, (i1, i2) -> {
                    int key1 = Integer.parseInt(i1);
                    int key2 = Integer.parseInt(i2);
                    if (key1 > key2) {
                        return 1;
                    } else if (key1 < key2) {
                        return -1;
                    }
                    return 0;
                }))
                .collect(Collectors.toList());
    }

    @Override
    public List<Identifier> getVideoList(String grade, String subject) {
        return standardResponseMap.entrySet()
                .stream()
                .filter(each -> each.getKey().equals(grade))
                .map(set -> set.getValue().entrySet().stream())
                .flatMap(stream -> stream.filter(each -> each.getKey().equals(subject))
                        .flatMap(set -> set.getValue()
                                .entrySet()
                                .stream()
                                .flatMap(value -> value.getValue().stream())))
                .sorted(Comparator.comparing(Identifier::getKey, (i1, i2) -> {
                    int key1 = Integer.parseInt(i1);
                    int key2 = Integer.parseInt(i2);
                    if (key1 > key2) {
                        return 1;
                    } else if (key1 < key2) {
                        return -1;
                    }
                    return 0;
                }))
                .collect(Collectors.toList());
    }

    @Scheduled(cron = "0 0 */12 ? * *")
    public void loadData() throws Exception {
        List<StandardResponse> courseComponents = getStandardResponse();
        log.info("Standard responses fetched successfully.");

        assert courseComponents != null;
        Map<String, Map<String, Map<Identifier, Set<Identifier>>>> gradeToSubject = new HashMap<>();
        for (StandardResponse res : courseComponents) {
            Map<String, Map<Identifier, Set<Identifier>>> subjectToChapterMap = new HashMap<>();
            for (SubjectResponse subRes : res.getSubjects()) {
                Map<Identifier, Set<Identifier>> chapterAndVideo = new HashMap<>();
                for (ChapterResponse chapter : subRes.getChapters()) {
                    Set<Identifier> videoSet = chapter.getChaptervideo().stream()
                            .map(video -> new Identifier(String.valueOf(video.getId()), video.getVideoname()))
                            .collect(Collectors.toSet());
                    chapterAndVideo.put(new Identifier(String.valueOf(chapter.getId()), chapter.getChapterName()), videoSet);
                }
                subjectToChapterMap.put(subRes.getName(), chapterAndVideo);
            }
            gradeToSubject.put(res.getName(), subjectToChapterMap);
        }

        standardResponseMap = gradeToSubject;
    }

    @Override
    public List<StandardResponse> getStandardResponse() throws TestException {
        UriComponents builder = UriComponentsBuilder.fromHttpUrl(courseServiceAddress+"/v1/grade/service/getallavailableportfolio").build();

        try {
            ResponseEntity<List<StandardResponse>> entity = restTemplate.exchange(builder.toUri(),
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<StandardResponse>>() {
                    });
            if (entity.getStatusCodeValue() != 200) {
                throw new TestException("Course service not responding");
            }
            return entity.getBody();
        } catch (Exception e) {
            throw new TestException("Course service not responding");
        }
    }
}
