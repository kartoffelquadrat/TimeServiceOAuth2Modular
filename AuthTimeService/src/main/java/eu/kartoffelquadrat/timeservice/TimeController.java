/**
 * OAuth2 secured version of the timeservice.
 *
 * @Author: Maximilian Schiedermeier
 * @Date: April 2019
 */
package eu.kartoffelquadrat.timeservice;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/***
 * This controller tells the time via a REST interface.
 */
@RestController
@CrossOrigin
public class TimeController {

    @Value("${token.service.location}")
    String tokenServiceLocation;

    /**
     * Gateway method to access service functionality - may be replaced by other implementations, depending the feature selection.
     *
     * @return
     */
    @GetMapping("/api/time")
    public String getTime(@RequestParam(name = "token") String token) throws UnirestException {

        // verify token by contacting token service
        String owner = getOwnerName(token);
        if(owner.isEmpty())
            return("I dont know you! I'm not telling you the time!");

        return TimeServiceUtils.lookUpCurrentTime();
    }

    /**
     * This method has been added. Resolves a token to a name or to the empty string (if invalid).
     *
     * Resolves an OAuth2 token to the associated user.
     *
     * @param accessToken as the string encoded OAuth2 token.
     * @return the name of the associated user.
     * @throws UnirestException in case the lobby service is not reachable
     */
    // curl "http://127.0.0.1:8084/api/username" -H 'Authorization:Bearer MeZDOgUsratgK9kdNHD2ny03r/0='; echo
    public String getOwnerName(String accessToken) throws UnirestException {
        HttpResponse<String> response = Unirest
                .get(tokenServiceLocation)
                .header("Authorization", "Bearer " + accessToken)
                .asString();
        if (response.getStatus() != 200)
            return "";
        return response.getBody();
    }
}
