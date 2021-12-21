package log4j;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RestController
public class Server {

    private static final Logger logger = LogManager.getLogger("HelloWorld");

    @GetMapping("/")
    public ResponseEntity<String> listAllHeaders(@RequestHeader Map<String, String> headers) {
    headers.forEach((key, value) -> {
        logger.info(key + ":" + value);
    });

    return new ResponseEntity<String>(
        String.format("Listed %d headers", headers.size()), HttpStatus.OK);
    }

}
