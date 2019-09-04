package cz.praha.jsikora.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.praha.jsikora.mock.UserMock;
import cz.praha.jsikora.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-09-04T16:23:58.912+02:00")

@Controller
public class UsersApiController implements UsersApi {

    private static final Logger log = LoggerFactory.getLogger(UsersApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;
    private final UserMock userMock;

    @org.springframework.beans.factory.annotation.Autowired
    public UsersApiController(ObjectMapper objectMapper, HttpServletRequest request, UserMock userMock) {
        this.objectMapper = objectMapper;
        this.request = request;
        this.userMock = userMock;
    }

    public ResponseEntity<List<User>> getUsers() {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {

            List<User> userList = userMock.getUserList();
            return new ResponseEntity<List<User>>(userList, HttpStatus.OK);

        }

        return new ResponseEntity<List<User>>(HttpStatus.NOT_IMPLEMENTED);
    }

}
