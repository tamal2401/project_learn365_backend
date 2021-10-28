package org.learn365.modal.course.response;

import java.util.ArrayList;
import java.util.List;

public class UserVideos
{

    private List<Recentvideo> recentvideo = new ArrayList<>();
    private List<Recentvideo> mostviewedVideo = new ArrayList<>();
    private List<Recentvideo> recommendedVideo = new ArrayList<>();

    public List<Recentvideo> getRecentvideo()
    {
        return recentvideo;
    }

    public void setRecentvideo(List<Recentvideo> recentvideo)
    {
        this.recentvideo = recentvideo;
    }

    public List<Recentvideo> getMostviewedVideo()
    {
        return mostviewedVideo;
    }

    public void setMostviewedVideo(List<Recentvideo> mostviewedVideo)
    {
        this.mostviewedVideo = mostviewedVideo;
    }

    public List<Recentvideo> getRecommendedVideo()
    {
        return recommendedVideo;
    }

    public void setRecommendedVideo(
            List<Recentvideo> recommendedVideo)
    {
        this.recommendedVideo = recommendedVideo;
    }
}
