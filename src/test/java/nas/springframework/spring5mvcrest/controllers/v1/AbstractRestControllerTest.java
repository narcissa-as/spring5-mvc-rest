package nas.springframework.spring5mvcrest.controllers.v1;

import com.fasterxml.jackson.databind.ObjectMapper;

public class AbstractRestControllerTest {

    //added to convert an object to json
    public String asJsonString(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);//convert object to json string
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

}
