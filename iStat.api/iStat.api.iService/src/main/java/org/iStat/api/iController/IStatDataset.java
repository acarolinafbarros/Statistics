package org.iStat.api.iController;

import java.util.Objects;

import org.iStat.api.iCommon.converter.Converter;
import org.iStat.api.iDomain.DocumentiStat;
import org.iStat.api.iResponse.StatusEnum;
import org.iStat.api.iService.DatasetService;
import org.iStat.api.iStatDataset.request.RequestiStatOpen;
import org.iStat.api.iStatDataset.request.RequestiStatSave;
import org.iStat.api.iStatDataset.response.ResponseiStatOpen;
import org.iStat.api.iStatDataset.response.ResponseiStatSave;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/iStatDataset")
public class IStatDataset {

    private final Logger LOGGER = LoggerFactory
        .getLogger(IStatDataset.class);

    @Autowired
    private DatasetService datasetService;

    @Autowired
    private Converter<RequestiStatSave, DocumentiStat> converterRequestiStatSave;

    @Autowired
    private Converter<Boolean, ResponseiStatSave> converterResponseiStatSave;

    @Autowired
    private Converter<RequestiStatOpen, DocumentiStat> converterRequestiStatOpen;

    @Autowired
    private Converter<DocumentiStat, ResponseiStatOpen> converterResponseiStatOpen;

    /**
     * @TODO
     *       WebService responsible for saving a dataset.
     * 
     *       URL example:
     *       http://localhost:8080/iStatDataset/saveDataset...
     * 
     * @param x
     * @return JSON of status and result
     */
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(value = "/saveDataset", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<ResponseiStatSave> saveDataset(@RequestBody RequestiStatSave request) {

        ResponseiStatSave response = new ResponseiStatSave();

        if (Objects.nonNull(request)) {

            try {
                DocumentiStat documentiStat = converterRequestiStatSave
                    .convert(request);

                LOGGER.info("DocumentiStat: {}", documentiStat);

                Boolean result = datasetService
                    .saveDataset(documentiStat);

                LOGGER.info("Final result: {}", result);

                response = converterResponseiStatSave.convert(result);
                response.setStatus(ResponseUtils
                    .buildResponseStatus(StatusEnum.SUCCESS));

                return ResponseEntity.ok(response);
            } catch (Exception ex) {
                LOGGER.error("Unexpected error at saveDataset:", ex);
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
            LOGGER.info("Response {}", response);
            return ResponseEntity
                .status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    /**
     * @TODO
     *       WebService responsible for getting a dataset.
     * 
     *       URL example:
     *       http://localhost:8080/iStatDataset/getDataset...
     * 
     * @param x
     * @return JSON of status and result
     */
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(value = "/openDataset", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<ResponseiStatOpen> openDataset(@RequestBody RequestiStatOpen request) {

        ResponseiStatOpen response = new ResponseiStatOpen();

        if (Objects.nonNull(request)) {

            try {

                DocumentiStat documentiStat = converterRequestiStatOpen
                    .convert(request);

                LOGGER.info("DocumentiStat: {}", documentiStat);

                DocumentiStat result = datasetService
                    .openDataset(documentiStat);

                LOGGER.info("Final result: {}", result);

                response = converterResponseiStatOpen.convert(result);
                response.setStatus(ResponseUtils
                    .buildResponseStatus(StatusEnum.SUCCESS));

                return ResponseEntity.ok(response);
            } catch (Exception ex) {
                LOGGER.error("Unexpected error at openDataset:", ex);
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
            LOGGER.info("Response {}", response);
            return ResponseEntity
                .status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

}
