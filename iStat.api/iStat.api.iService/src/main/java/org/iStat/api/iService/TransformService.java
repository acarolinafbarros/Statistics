package org.iStat.api.iService;

import org.iStat.api.iDomain.DocumentiStat;
import org.iStat.api.iLogic.CalcStatistical;
import org.iStat.api.iLogic.TransformStatistical;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TransformService {

	private final Logger LOG = LoggerFactory.getLogger(TransformService.class);
	
	 public DocumentiStat transformTranspose(DocumentiStat input) {
        TransformStatistical transformStatistical = new TransformStatistical();
        return transformStatistical.transformTranspose(input);
	 }
		 	
	 public DocumentiStat transformScale(DocumentiStat input, Float scalar) {
        TransformStatistical transformStatistical = new TransformStatistical();
        return transformStatistical.transformScale(input,scalar);
	 }
	 
	 public DocumentiStat transformAddScalar(DocumentiStat input, Float scalar) {
        TransformStatistical transformStatistical = new TransformStatistical();
        return transformStatistical.transformAddScalar(input,scalar);
	 }
	 
	 public DocumentiStat transformAddTwoDatasets(DocumentiStat input) {
        TransformStatistical transformStatistical = new TransformStatistical();
        return transformStatistical.transformAddTwoDatasets(input);
	 }
	 
	 public DocumentiStat transformMultiplyTwoDatasets(DocumentiStat input) {
        TransformStatistical transformStatistical = new TransformStatistical();
        return transformStatistical.transformMultiplyTwoDatasets(input);
	 }
	 
	 public DocumentiStat transformInterpolationLine(DocumentiStat input) {
	        TransformStatistical transformStatistical = new TransformStatistical();
	        return transformStatistical.transformInterpolationLine(input);
		 }
	 
	 public DocumentiStat transformInterpolationColumn(DocumentiStat input) {
	        TransformStatistical transformStatistical = new TransformStatistical();
	        return transformStatistical.transformInterpolationColumn(input);
		 }
  
}
