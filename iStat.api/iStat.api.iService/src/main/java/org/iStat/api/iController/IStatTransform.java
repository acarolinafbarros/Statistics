package org.iStat.api.iController;

import java.util.Objects;

import org.iStat.api.iCommon.converter.Converter;
import org.iStat.api.iDomain.DocumentiStat;
import org.iStat.api.iResponse.StatusEnum;
import org.iStat.api.iService.TransformService;
import org.iStat.api.iStatTransform.request.RequestiStatTransform;
import org.iStat.api.iStatTransform.response.ResponseiStatTransform;
import org.iStat.api.iUtils.ResponseUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/iStatTransform")
public class IStatTransform {

    private final Logger LOGGER = LoggerFactory
        .getLogger(IStatTransform.class);

    @Autowired
    private TransformService transformService;

    @Autowired
    private Converter<RequestiStatTransform, DocumentiStat> converterRequestiStatTransform;

    @Autowired
    private Converter<DocumentiStat, ResponseiStatTransform> converterResponseiStatTransform;

    /**
     * WebService responsible for transpose the dataset.
     * 
     * URL example:
     * http://localhost:8080/iStatTransform/transformTranpose
     * 
     * @param valuesToCalc
     *            - List of floats separated with ;
     * @return JSON of status and result
     */
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(value = "/transformTranspose", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<ResponseiStatTransform> transformTranspose(@RequestBody RequestiStatTransform request, @RequestParam(required = true) Integer finalLine, @RequestParam(required = true) String finalColumn) {
        ResponseiStatTransform response = new ResponseiStatTransform();

        if (Objects.nonNull(request)) {

            try {
                DocumentiStat documentiStat = converterRequestiStatTransform
                    .convert(request);

                LOGGER.info("'operation=transformTranspose', 'documentiStat={}'", documentiStat);
                
                DocumentiStat result = transformService
                    .transformTranspose(documentiStat, finalColumn, finalLine);

                LOGGER.info("'operation=transformTranspose', 'result={}'", result);

                response = converterResponseiStatTransform
                    .convert(result);
                response.setStatus(ResponseUtils
                    .buildResponseStatus(StatusEnum.SUCCESS));

                return ResponseEntity.ok(response);
            } catch (Exception ex) {
                response.setStatus(ResponseUtils.buildResponseStatus(
                        StatusEnum.UNEXPECTED, "Error message: %s",
                        ex));
                return ResponseEntity
                    .status(HttpStatus.SERVICE_UNAVAILABLE)
                    .body(response);
            }
        } else {
            response.setStatus(ResponseUtils.buildResponseStatus(
                    StatusEnum.UNSUCCESS, "Request object: %s",
                    request));
            LOGGER.info("'operation=transformTranspose', 'response={}'", response);
            return ResponseEntity
                .status(HttpStatus.BAD_REQUEST).body(response);
        }

    }

    /**
     * WebService responsible for scale the dataset.
     * 
     * URL example:
     * http://localhost:8080/iStatTransform/transformScale
     * 
     * @param valuesToCalc
     *            - List of floats separated with ;
     * @return JSON of status and result
     */
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(value = "/transformScale", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<ResponseiStatTransform> transformScale(@RequestBody RequestiStatTransform request, @RequestParam(required = true) Float scale, @RequestParam(required = false) Integer finalLine, @RequestParam(required = false) String finalColumn) {

        ResponseiStatTransform response = new ResponseiStatTransform();

        if (Objects.nonNull(request)) {

            try {
                DocumentiStat documentiStat = converterRequestiStatTransform
                    .convert(request);

                LOGGER.info("'operation=transformScale', 'documentiStat={}'", documentiStat);

                Objects.requireNonNull(scale, "scale must be not null!");
                
                DocumentiStat result = transformService
                    .transformScale(documentiStat,
                            scale);

                LOGGER.info("'operation=transformScale', 'result={}'", result);

                response = converterResponseiStatTransform
                    .convert(result);
                response.setStatus(ResponseUtils
                    .buildResponseStatus(StatusEnum.SUCCESS));

                return ResponseEntity.ok(response);
            } catch (Exception ex) {
                response.setStatus(ResponseUtils.buildResponseStatus(
                        StatusEnum.UNEXPECTED, "Error message: %s",
                        ex));
                return ResponseEntity
                    .status(HttpStatus.SERVICE_UNAVAILABLE)
                    .body(response);
            }
        } else {
            response.setStatus(ResponseUtils.buildResponseStatus(
                    StatusEnum.UNSUCCESS, "Request object: %s",
                    request));
            LOGGER.info("'operation=transformScale', 'response={}'", response);
            return ResponseEntity
                .status(HttpStatus.BAD_REQUEST).body(response);
        }

    }

    /**
     * WebService responsible for add a scalar to every element of the dataset.
     * 
     * URL example:
     * http://localhost:8080/iStatTransform/transformScale
     * 
     * @param valuesToCalc
     *            - List of floats separated with ;
     * @return JSON of status and result
     */
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(value = "/transformAddScalar", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<ResponseiStatTransform> transformAddScalar(@RequestBody RequestiStatTransform request, @RequestParam(required = true) Float scale, @RequestParam(required = false) Integer finalLine, @RequestParam(required = false) String finalColumn) {

        ResponseiStatTransform response = new ResponseiStatTransform();

        if (Objects.nonNull(request)) {

            try {
                DocumentiStat documentiStat = converterRequestiStatTransform
                    .convert(request);

                LOGGER.info("'operation=transformAddScalar', 'documentiStat={}'", documentiStat);

                Objects.requireNonNull(scale, "scale must be not null!");
                
                DocumentiStat result = transformService
                    .transformAddScalar(documentiStat,
                            scale);

                LOGGER.info("'operation=transformAddScalar', 'result={}'", result);

                response = converterResponseiStatTransform
                    .convert(result);
                response.setStatus(ResponseUtils
                    .buildResponseStatus(StatusEnum.SUCCESS));

                return ResponseEntity.ok(response);
            } catch (Exception ex) {
                response.setStatus(ResponseUtils.buildResponseStatus(
                        StatusEnum.UNEXPECTED, "Error message: %s",
                        ex));
                return ResponseEntity
                    .status(HttpStatus.SERVICE_UNAVAILABLE)
                    .body(response);
            }
        } else {
            response.setStatus(ResponseUtils.buildResponseStatus(
                    StatusEnum.UNSUCCESS, "Request object: %s",
                    request));
            LOGGER.info("'operation=transformAddScalar', 'response={}'", response);
            return ResponseEntity
                .status(HttpStatus.BAD_REQUEST).body(response);
        }

    }

    /**
     * WebService responsible for add two datasets.
     * 
     * URL example:
     * http://localhost:8080/iStatTransform/transformAddTwoDatasets
     * 
     * @param valuesToCalc
     *            - List of floats separated with ;
     * @return JSON of status and result
     */
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(value = "/transformAddTwoDatasets", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<ResponseiStatTransform> transformAddTwoDatasets(@RequestBody RequestiStatTransform request, @RequestParam(required = true) Integer finalLine, @RequestParam(required = true) String finalColumn) {

        ResponseiStatTransform response = new ResponseiStatTransform();

        if (Objects.nonNull(request)) {

            try {
                DocumentiStat documentiStat = converterRequestiStatTransform
                    .convert(request);

                LOGGER.info("'operation=transformAddTwoDatasets', 'documentiStat={}'", documentiStat);

                DocumentiStat result = transformService
                    .transformAddTwoDatasets(documentiStat, finalColumn, finalLine);

                LOGGER.info("'operation=transformAddTwoDatasets', 'result={}'", result);

                response = converterResponseiStatTransform
                    .convert(result);
                response.setStatus(ResponseUtils
                    .buildResponseStatus(StatusEnum.SUCCESS));

                return ResponseEntity.ok(response);
            } catch (Exception ex) {
                response.setStatus(ResponseUtils.buildResponseStatus(
                        StatusEnum.UNEXPECTED, "Error message: %s",
                        ex));
                return ResponseEntity
                    .status(HttpStatus.SERVICE_UNAVAILABLE)
                    .body(response);
            }
        } else {
            response.setStatus(ResponseUtils.buildResponseStatus(
                    StatusEnum.UNSUCCESS, "Request object: %s",
                    request));
            LOGGER.info("'operation=transformAddTwoDatasets', 'response={}'", response);
            return ResponseEntity
                .status(HttpStatus.BAD_REQUEST).body(response);
        }

    }

    /**
     * WebService responsible for multiply two datasets.
     * 
     * URL example:
     * http://localhost:8080/iStatTransform/transformAddTwoDatasets
     * 
     * @param valuesToCalc
     *            - List of floats separated with ;
     * @return JSON of status and result
     */
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(value = "/transformMultiplyTwoDatasets", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<ResponseiStatTransform> transformMultiplyTwoDatasets(@RequestBody RequestiStatTransform request, @RequestParam(required = true) Integer finalLine, @RequestParam(required = true) String finalColumn) {

        ResponseiStatTransform response = new ResponseiStatTransform();

        if (Objects.nonNull(request)) {

            try {
                DocumentiStat documentiStat = converterRequestiStatTransform
                    .convert(request);

                LOGGER.info("'operation=transformMultiplyTwoDatasets', 'documentiStat={}'", documentiStat);

                DocumentiStat result = transformService
                    .transformMultiplyTwoDatasets(documentiStat, finalColumn, finalLine);

                LOGGER.info("'operation=transformMultiplyTwoDatasets', 'result={}'", result);

                response = converterResponseiStatTransform
                    .convert(result);
                response.setStatus(ResponseUtils
                    .buildResponseStatus(StatusEnum.SUCCESS));

                return ResponseEntity.ok(response);
            } catch (Exception ex) {
                response.setStatus(ResponseUtils.buildResponseStatus(
                        StatusEnum.UNEXPECTED, "Error message: %s",
                        ex));
                return ResponseEntity
                    .status(HttpStatus.SERVICE_UNAVAILABLE)
                    .body(response);
            }
        } else {
            response.setStatus(ResponseUtils.buildResponseStatus(
                    StatusEnum.UNSUCCESS, "Request object: %s",
                    request));
            LOGGER.info("'operation=transformMultiplyTwoDatasets', 'response={}'", response);
            return ResponseEntity
                .status(HttpStatus.BAD_REQUEST).body(response);
        }

    }
    
    /**
     * WebService responsible for linear interpolation on lines.
     * 
     * URL example:
     * http://localhost:8080/iStatTransform/transformInterpolationLine
     * 
     * @param valuesToCalc
     *            - List of floats separated with ;
     * @return JSON of status and result
     */
    @RequestMapping(value = "/transformInterpolationLine", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<ResponseiStatTransform> transformInterpolationLine(@RequestBody RequestiStatTransform request, @RequestParam(required = true) Integer finalLine, @RequestParam(required = true) String finalColumn) {

        ResponseiStatTransform response = new ResponseiStatTransform();

        if (Objects.nonNull(request)) {

            try {
                DocumentiStat documentiStat = converterRequestiStatTransform
                    .convert(request);

                LOGGER.info("'operation=transformInterpolationLine', 'documentiStat={}'", documentiStat);

                DocumentiStat result = transformService
                    .transformInterpolationLine(documentiStat, finalColumn, finalLine);

                LOGGER.info("'operation=transformInterpolationLine', 'result={}'", result);

                response = converterResponseiStatTransform
                    .convert(result);
                response.setStatus(ResponseUtils
                    .buildResponseStatus(StatusEnum.SUCCESS));

                return ResponseEntity.ok(response);
            } catch (Exception ex) {
                response.setStatus(ResponseUtils.buildResponseStatus(
                        StatusEnum.UNEXPECTED, "Error message: %s",
                        ex));
                return ResponseEntity
                    .status(HttpStatus.SERVICE_UNAVAILABLE)
                    .body(response);
            }
        } else {
            response.setStatus(ResponseUtils.buildResponseStatus(
                    StatusEnum.UNSUCCESS, "Request object: %s",
                    request));
            LOGGER.info("'operation=transformInterpolationLine', 'response={}'", response);
            return ResponseEntity
                .status(HttpStatus.BAD_REQUEST).body(response);
        }

    }
    
    /**
     * WebService responsible for linear interpolation on columns.
     * 
     * URL example:
     * http://localhost:8080/iStatTransform/transformInterpolationColumn
     * 
     * @param valuesToCalc
     *            - List of floats separated with ;
     * @return JSON of status and result
     */
    @RequestMapping(value = "/transformInterpolationColumn", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<ResponseiStatTransform> transformInterpolationColumn(@RequestBody RequestiStatTransform request, @RequestParam(required = true) Integer finalLine, @RequestParam(required = true) String finalColumn) {

        ResponseiStatTransform response = new ResponseiStatTransform();

        if (Objects.nonNull(request)) {

            try {
                DocumentiStat documentiStat = converterRequestiStatTransform
                    .convert(request);

                LOGGER.info("'operation=transformInterpolationColumn', 'documentiStat={}'", documentiStat);

                DocumentiStat result = transformService
                    .transformInterpolationColumn(documentiStat, finalColumn, finalLine);

                LOGGER.info("'operation=transformInterpolationColumn', 'result={}'", result);

                response = converterResponseiStatTransform
                    .convert(result);
                response.setStatus(ResponseUtils
                    .buildResponseStatus(StatusEnum.SUCCESS));

                return ResponseEntity.ok(response);
            } catch (Exception ex) {
                response.setStatus(ResponseUtils.buildResponseStatus(
                        StatusEnum.UNEXPECTED, "Error message: %s",
                        ex));
                return ResponseEntity
                    .status(HttpStatus.SERVICE_UNAVAILABLE)
                    .body(response);
            }
        } else {
            response.setStatus(ResponseUtils.buildResponseStatus(
                    StatusEnum.UNSUCCESS, "Request object: %s",
                    request));
            LOGGER.info("'operation=transformInterpolationColumn', 'response={}'", response);
            return ResponseEntity
                .status(HttpStatus.BAD_REQUEST).body(response);
        }

    }

}
