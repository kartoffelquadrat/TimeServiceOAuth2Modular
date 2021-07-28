/**
 * OAuth2 secured version of the timeservice.
 * Following this tutorial: http://websystique.com/spring-security/secure-spring-rest-api-using-oauth2/
 *
 * @Author: Maximilian Schiedermeier
 * @Date: April 2019
 */
package eu.kartoffelquadrat.timeservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/***
 * This controller tells the time via a REST interface.
 */
@RestController
public class TimeController {

    /**
     * Gateway method to access service functionality - may be replaced by other implementations, depending the feature selection.
     *
     * @return
     */
    @GetMapping("/api/time")
    public String getTime() {
        return TimeServiceUtils.lookUpCurrentTime();
    }
}
