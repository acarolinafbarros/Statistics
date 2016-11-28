package org.iStat.api.iBLL;

import java.util.Comparator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;

public class CalcStatistical {

    private final Logger LOG = LoggerFactory.getLogger(CalcStatistical.class);

    public Float calculateMedian(List<Float> input) {
        Float result = null;
        if (!ObjectUtils.isEmpty(input)) {
            Integer length = input.size();
            if (length > 0) {
               Float sum=(float) input.stream().mapToDouble(i -> i.floatValue()).sum();
               result = sum/length;
            }
        }

        return result;
    }
    
    public Float calculateGeometricMean(List<Float> input) {
        Float result = null;
        if (!ObjectUtils.isEmpty(input)) {
            Integer length = input.size();
            if (length > 0) {
               Float mult= (float) input.stream().mapToDouble(i -> i.floatValue()).reduce(1, (a,b)-> a*b);
               double geoMean = Math.pow(mult, 1.0 / length);
               result = (float) geoMean;               
            }
        }
       
        return result;
    }
    

    
    public Float calculateMidrange(List<Float> input) {
        Float result = null;
        if (!ObjectUtils.isEmpty(input)) {
            Integer length = input.size();
            if (length > 0) {                
                Float max=(float) input.stream().max(Comparator.comparing(i -> i)).get();
                Float min=(float) input.stream().min(Comparator.comparing(i -> i)).get();
                
                result = (max + min)/2;             
            }
        }
       
        return result;
    }
    
    public Float calculateVariance(List<Float> input) {
        Float result = null;
        if (!ObjectUtils.isEmpty(input)) {
            Integer length = input.size();
            if (length > 0) { 
                float median = calculateMedian(input);
                float temp = 0;
                for(float value : input){
                    temp += (value-median)*(value-median);    
                }
                result = temp/((float)length-1);
            }
        }
        return result;
    }
    
    public Float calculateStandardDeviation(List<Float> input) {
        Float result = null;
        if (!ObjectUtils.isEmpty(input)) {
            Integer length = input.size();
            if (length > 0) { 
                float variance = calculateVariance(input);
                result = (float) Math.sqrt(variance);
            }
        }
        return result;
    }
    
    

}

