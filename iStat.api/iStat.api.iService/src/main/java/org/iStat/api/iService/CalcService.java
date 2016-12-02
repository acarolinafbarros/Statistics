package org.iStat.api.iService;

import org.iStat.api.iEntity.DocumentiStat;
import org.iStat.api.iLogic.CalcStatistical;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CalcService {

    private final Logger LOG = LoggerFactory.getLogger(CalcService.class);
    
    public Float calculateMedian(DocumentiStat input) {
        CalcStatistical calcStatistical = new CalcStatistical();
        return calcStatistical.calculateMedian(input);
    }

    public Float calculateGeometricMean(DocumentiStat input) {
        CalcStatistical calcStatistical = new CalcStatistical();
        return calcStatistical.calculateGeometricMean(input);
    }

    public Float calculateMode(DocumentiStat input) {
        CalcStatistical calcStatistical = new CalcStatistical();
        return calcStatistical.calculateMode(input);
    }

    public Float calculateMidrange(DocumentiStat input) {
        CalcStatistical calcStatistical = new CalcStatistical();
        return calcStatistical.calculateMidrange(input);
    }

    public Float calculateVariance(DocumentiStat input) {
        CalcStatistical calcStatistical = new CalcStatistical();
        return calcStatistical.calculateVariance(input);
    }

    public Float calculateStandardDeviation(DocumentiStat input) {
        CalcStatistical calcStatistical = new CalcStatistical();
        return calcStatistical.calculateStandardDeviation(input);
    }
    
    public Float calculateRowColumnTotal(DocumentiStat input) {
        CalcStatistical calcStatistical = new CalcStatistical();
        return calcStatistical.calculateRowColumnTotal(input);
    }
 
}
