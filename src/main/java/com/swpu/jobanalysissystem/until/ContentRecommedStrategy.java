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
public class ContentRecommedStrategy implements  RecommedStrategy{
    /*
    作比较的矩阵需要的JobImage
    */
    public static HashMap<Integer,RecommedJobImage> jobBeanHash = null;

    @Autowired
    public  RecommendJobImageMapper recommendJobImageMapper;
    //
    //id job_name min_salary min_xueli job_place label company_name
    //
    public static List<RecommedJobImage> jobImages = null;

    @Override
    public ArrayList<Integer> getRecommendJobIds(HashMap<String, Double> userMap) {
        //jobImages只加载一次
        //节约资源
        if (jobImages==null) {
            jobImages = recommendJobImageMapper.getRecommenedJobImages();
        }

        jobBeanHash = new HashMap<>();

        //计算相似度的结果map  矩阵
        HashMap<Double,Integer> result = new HashMap<>();


        ArrayList<Double> nums = new ArrayList<>();

        //做推荐最后返回的job的ids
        ArrayList<Integer> recommendJobIds = new ArrayList<>();

        for (RecommedJobImage job : jobImages){

            //传入一个user对象
            //user与jobs比较
            //计算相似度
            //然后好推荐
            result.put(getMatchValue(userMap, getTFIDF(job.toString(), 20)),Integer.parseInt(job.getId()));


            nums.add(getMatchValue(userMap, getTFIDF(job.toString(), 20)));
        }

        //排序
        //排序后才好按相关度从高到低推荐
        nums.sort(null);



        for (int i = nums.size()-1; i>nums.size()-10; i--){
            if(result.containsKey(nums.get(i))){
                for (int j = 0; j<jobImages.size();j++){
                    if (Integer.parseInt(jobImages.get(j).getId()) == result.get(nums.get(i))){
                        recommendJobIds.add(Integer.parseInt(jobImages.get(j).getId()));
                    }
                }
            }
        }


        return recommendJobIds;
    }

    /*
    TF-IDF 计算词语在文本中的重要程度
    * */
    public static List<Keyword> getTFIDF(String content,int keyNums)
    {
        KeyWordComputer kwc = new KeyWordComputer(keyNums);
        return kwc.computeArticleTfidf(content);
    }


    private static double getMatchValue(HashMap<String, Double> userMap, List<Keyword> listTFIDF)
    {
        Set<String> keywordsSet = userMap.keySet();

        double matchValue = 0;
        for (Keyword keyword : listTFIDF)
        {
            if (keywordsSet.contains(keyword.getName()))
            {
                matchValue += keyword.getScore() * userMap.get(keyword.getName());
            }
        }
        return matchValue;
    }
}
