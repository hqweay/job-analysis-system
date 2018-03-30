package com.swpu.jobanalysissystem.until;

import com.swpu.jobanalysissystem.dao.RecommendJobImageMapper;
import com.swpu.jobanalysissystem.entity.RecommedJobImage;
import org.ansj.app.keyword.KeyWordComputer;
import org.ansj.app.keyword.Keyword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

@Component
public class ContendRecommedStrategy implements  RecommedStrategy{
    public static HashMap<Integer,RecommedJobImage> jobBeanHash = null;

    @Autowired
    public  RecommendJobImageMapper recommendJobImageMapper;
    public static List<RecommedJobImage> jobImages = null;

    @Override
    public ArrayList<Integer> glgorithm(HashMap<String, Double> user) {
        if (jobImages==null) {
            jobImages = recommendJobImageMapper.getRecommenedJobImages();
        }
        jobBeanHash = new HashMap<>();
        // HashMap<Integer,List<Keyword>> keywords = new HashMap<>();
        //计算相似度的结果map
        HashMap<Double,Integer> result = new HashMap<>();
        ArrayList<Double> nums = new ArrayList<>();
        for (RecommedJobImage job : jobImages){
            // jobBeanHash.put(Integer.parseInt(job.getId()),job.toString());
            // keywords.put(Integer.parseInt(job.getId()), getTFIDE(job.toString(), 20));

            result.put(getMatchValue(user, getTFIDE(job.toString(), 20)),Integer.parseInt(job.getId()));
            System.out.println(getTFIDE(job.toString(), 20));
            nums.add(getMatchValue(user, getTFIDE(job.toString(), 20)));
        }
        nums.sort(null);

        ArrayList<Integer> recommendID = new ArrayList<>();

        for (int i = nums.size()-1;i>nums.size()-10;i--){
            if(result.containsKey(nums.get(i))){
                for (int j = 0; j<jobImages.size();j++){
                    if (Integer.parseInt(jobImages.get(j).getId())==result.get(nums.get(i))){
                        recommendID.add(Integer.parseInt(jobImages.get(j).getId()));
                    }
                }
            }
        }


        return recommendID;
    }

    public static List<Keyword> getTFIDE(String content,int keyNums)
    {
        KeyWordComputer kwc = new KeyWordComputer(keyNums);
        return kwc.computeArticleTfidf(content);
    }
    private static double getMatchValue(HashMap<String, Double> map, List<Keyword> list)
    {
        Set<String> keywordsSet = map.keySet();
        //System.out.println(keywordsSet);
        double matchValue = 0;
        for (Keyword keyword : list)
        {
            if (keywordsSet.contains(keyword.getName()))
            {
                matchValue += keyword.getScore() * map.get(keyword.getName());
            }
        }
        return matchValue;
    }


}
