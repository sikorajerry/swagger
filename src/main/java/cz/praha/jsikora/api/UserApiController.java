package cz.praha.jsikora.api;

import cz.praha.jsikora.mock.UserMock;
import cz.praha.jsikora.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-09-04T16:23:58.912+02:00")

@Controller
public class UserApiController implements UserApi {

    private static final Logger log = LoggerFactory.getLogger(UserApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;
    private final UserMock userMock;

    @org.springframework.beans.factory.annotation.Autowired
    public UserApiController(ObjectMapper objectMapper, HttpServletRequest request, UserMock userMock) {
        this.objectMapper = objectMapper;
        this.request = request;
        this.userMock = userMock;
    }

    public ResponseEntity<Void> addUser(@ApiParam(value = "User item to add"  )  @Valid @RequestBody User user) {
        String accept = request.getHeader("Accept");

        userMock.addUser(user);

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<Void> deleteUser(@ApiParam(value = "Parameter description in CommonMark or HTML.",required=true) @PathVariable("userId") Integer userId) {
        String accept = request.getHeader("Accept");

        User user = userMock.getUserList().get(userId);
        if (user == null) {
            log.info("Nenasel jsem zaznam na smazani userId -"+ userId);
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        } else {
            printAll(userMock.getUserList());
            log.info("PROBIHA MAZANI");
            User userInDb = userMock.getUserList().get(userId);
            userMock.getUserList().remove(userInDb);
            printAll(userMock.getUserList());
            return new ResponseEntity<Void>(HttpStatus.OK);
        }
    }

    private void printAll(List<User> userList) {
        for (User user: userList) {
            log.info(user.getId() + " " + user.getName());
        }

    }

    public ResponseEntity<User> getUserInfo(@ApiParam(value = "Parameter description in CommonMark or HTML.",required=true) @PathVariable("userId") Integer userId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {

            User user = userMock.getUserList().get(userId);
            return new ResponseEntity<User>(user, HttpStatus.ACCEPTED);

        }

        return new ResponseEntity<User>(HttpStatus.NOT_IMPLEMENTED);
    }

}
