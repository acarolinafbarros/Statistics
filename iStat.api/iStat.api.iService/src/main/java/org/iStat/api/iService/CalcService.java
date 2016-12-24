package org.iStat.api.iService;

import org.iStat.api.iDomain.DocumentiStat;
import org.iStat.api.iExceptions.CalcException;
import org.iStat.api.iLogic.CalcStatistical;
import org.springframework.stereotype.Service;

@Service
public class CalcService {
    
    private CalcStatistical calcStatistical;
    
    public CalcService(){
        calcStatistical = new CalcStatistical();
    }
    
    public Float calculateMedian(DocumentiStat input) {
        return calcStatistical.calculateMedian(input);
    }

    public Float calculateGeometricMean(DocumentiStat input) throws CalcException {
        return calcStatistical.calculateGeometricMean(input);
    }

    public Float calculateMode(DocumentiStat input) {
        return calcStatistical.calculateMode(input);
    }

    public Float calculateMidrange(DocumentiStat input) {
        return calcStatistical.calculateMidrange(input);
    }

    public Float calculateVariance(DocumentiStat input) {
        return calcStatistical.calculateVariance(input);
    }

    public Float calculateStandardDeviation(DocumentiStat input) {
        return calcStatistical.calculateStandardDeviation(input);
    }
    
    public Float calculateRowColumnTotal(DocumentiStat input) {
        return calcStatistical.calculateRowColumnTotal(input);
    }
 
}
