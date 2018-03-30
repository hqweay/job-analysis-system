package com.swpu.jobanalysissystem.Test;

import java.io.*;
import java.util.*;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.*;
import org.apache.mahout.cf.taste.impl.neighborhood.*;
import org.apache.mahout.cf.taste.impl.recommender.*;

import org.apache.mahout.cf.taste.impl.similarity.*;
import org.apache.mahout.cf.taste.model.*;
import org.apache.mahout.cf.taste.neighborhood.*;
import org.apache.mahout.cf.taste.recommender.*;
import org.apache.mahout.cf.taste.similarity.*;
import org.springframework.util.ResourceUtils;


public class tuijian_alone {
  // private TestMahout(){};


  public static void main(String args[]) throws Exception {

    tuijian_alone testMahout = new tuijian_alone();
    System.out.println("The baseItemCF Result:");
    testMahout.baseItemCF();

  }


  //基于内容相似度的协同过滤推荐实现
  public void baseItemCF(){
    DataModel model;
    try {
      //构建模型
      model = new FileDataModel(new File("src/main/resources/test.txt"));
      //计算相似度

      ItemSimilarity itemsimilarity =new PearsonCorrelationSimilarity(model);
      //项目因素，用户每个项目的印象因素，
      //CandidateItemsStrategy candidateItemsStrategy = new SamplingCandidateItemsStrategy(1, 1, 1, model.getNumUsers(),  model.getNumItems());
      //最相似的物品
      //MostSimilarItemsCandidateItemsStrategy mostSimilarItemsCandidateItemsStrategy = new SamplingCandidateItemsStrategy(1, 1, 1, model.getNumUsers(),  model.getNumItems());
      //构造推荐引擎 这个是基于物品的推荐
      //Recommender recommender= new GenericItemBasedRecommender(model,itemsimilarity, candidateItemsStrategy,mostSimilarItemsCandidateItemsStrategy);
      //基于物品的无偏好度推荐
      Recommender recommender= new GenericItemBasedRecommender(model,itemsimilarity);
      //获取推荐数据
      List<RecommendedItem> recommendations =recommender.recommend(1, 2);
      //输出
      for(RecommendedItem recommendation :recommendations){
        System.out.println(recommendation);
      }
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (TasteException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }



  }

}
