package com.swpu.jobanalysissystem.until;

import java.util.HashMap;
import java.util.List;

public interface RecommedStrategy {
    public abstract List<Integer> glgorithm(HashMap<String,Double> user);
}
