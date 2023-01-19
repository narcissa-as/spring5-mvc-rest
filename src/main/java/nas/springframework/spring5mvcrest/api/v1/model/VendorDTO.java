package nas.springframework.spring5mvcrest.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VendorDTO {

//    Long id; we don't need id, we pass id through front by @Pathvariable
    String name;

    @JsonProperty("vendor_url")
    String vendorUrl;
}
