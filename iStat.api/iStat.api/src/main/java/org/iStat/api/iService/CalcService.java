package org.iStat.api.iService;

import java.util.List;

import org.iStat.api.iBLL.CalcStatistical;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CalcService {

    private final Logger LOG = LoggerFactory.getLogger(CalcService.class);

    public Float calculateMedian(List<Float> input) {
        CalcStatistical calcStatistical = new CalcStatistical();
        return calcStatistical.calculateMedian(input);
    }
    
    public Float calculateGeometricMean(List<Float> input) {
        CalcStatistical calcStatistical = new CalcStatistical();
        return calcStatistical.calculateGeometricMean(input);
    }

}
