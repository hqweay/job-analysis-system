package com.swpu.jobanalysissystem.until;

import java.util.HashMap;
import java.util.List;

public interface RecommedStrategy {
    public abstract List<Integer> getRecommendJobIds(HashMap<String,Double> user);
}
