package nas.springframework.spring5mvcrest.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {

    //private Long id; we don't need id, we pass id through front by @Pathvariable
    @ApiModelProperty(value = "This is the first name", required = true)
    private String firstname;

    @ApiModelProperty(value = "Last Name", required = true)
    private String lastname;
    @JsonProperty("customer_url")// the way this prop would be shown in a json object
    private String customerUrl;
}
