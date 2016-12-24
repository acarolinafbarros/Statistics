package org.iStat.api.iService;

import org.iStat.api.iDomain.DocumentiStat;
import org.iStat.api.iExceptions.DomainException;
import org.iStat.api.iExceptions.TransformException;
import org.iStat.api.iLogic.TransformStatistical;
import org.iStat.api.iLogic.TransformStatistical.TransformType;
import org.springframework.stereotype.Service;

@Service
public class TransformService {

    private TransformStatistical transformStatistical;

    public TransformService() {
        transformStatistical = new TransformStatistical();
    }

    public DocumentiStat transformTranspose(DocumentiStat input) throws DomainException {
        return transformStatistical.transformTranspose(input);
    }

    public DocumentiStat transformScale(DocumentiStat input, Float scalar) {
        return transformStatistical.transformScale(input, scalar);
    }

    public DocumentiStat transformAddScalar(DocumentiStat input, Float scalar) {
        return transformStatistical.transformAddScalar(input, scalar);
    }

    public DocumentiStat transformAddTwoDatasets(DocumentiStat input) throws TransformException, DomainException {
        return transformStatistical.transformAddTwoDatasets(input);
    }

    public DocumentiStat transformMultiplyTwoDatasets(DocumentiStat input) throws TransformException, DomainException {
        return transformStatistical.transformMultiplyTwoDatasets(input);
    }

    public DocumentiStat transformInterpolationLine(DocumentiStat input) throws Exception {
        return transformStatistical.transformInterpolation(input, TransformType.LINE);
    }

    public DocumentiStat transformInterpolationColumn(DocumentiStat input) throws Exception {
        return transformStatistical.transformInterpolation(input, TransformType.COLUMN);
    }

}
