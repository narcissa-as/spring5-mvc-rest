package nas.springframework.spring5mvcrest.controllers.v1;

import nas.springframework.spring5mvcrest.api.v1.model.VendorDTO;
import nas.springframework.spring5mvcrest.api.v1.model.VendorListDTO;
import nas.springframework.spring5mvcrest.services.ResourceNotFoundException;
import nas.springframework.spring5mvcrest.services.VendorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
//import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//here is a test like Integration test, with using SpringContext and specifically part of Web(we use it for front and web testing)
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = {VendorController.class})
class VendorControllerTest extends AbstractRestControllerTest {

    @MockBean //provided by Spring Context, like @Mock,
    VendorService vendorService;

    @Autowired //provided by Spring Context
    MockMvc mockMvc;

    VendorDTO vendorDTO1;
    VendorDTO vendorDTO2;

    @BeforeEach
    void setUp() {
        vendorDTO1 = new VendorDTO( "first Vendor", VendorController.BASE_URL + "/1");
        vendorDTO2 = new VendorDTO( "second Vendor", VendorController.BASE_URL + "/2");
        // with using this type of test we don't need to do this works, we Autowired the mockmvc
        //        MockitoAnnotations.openMocks(this);
        //        mockMvc = MockMvcBuilders.standaloneSetup(vendorController).build();
    }

    @Test
    void getAllVendors() throws Exception {

        //given
        VendorListDTO vendorListDTO = new VendorListDTO(Arrays.asList(vendorDTO1, vendorDTO2));

        //when
        given(vendorService.getAllVendors()).willReturn(vendorListDTO);

        //then
        mockMvc.perform(get(VendorController.BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())

                .andExpect(jsonPath("$.vendors", hasSize(2)));


    }

    @Test
    void getVendorById() throws Exception {
        given(vendorService.getVendorById(anyLong())).willReturn(vendorDTO1);

        //then
        mockMvc.perform(get(VendorController.BASE_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("first Vendor")));
    }

    @Test
    void createNewVendor() throws Exception {
        given(vendorService.createNewVendor(any())).willReturn(vendorDTO1);
        mockMvc.perform(post(VendorController.BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(vendorDTO1))
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", equalTo("first Vendor")))
                .andExpect(jsonPath("$.vendor_url", equalTo(VendorController.BASE_URL + "/1")));
    }

    @Test
    void updateVendor() throws Exception {

        //given
        given(vendorService.saveVendorByDTO(anyLong(), any(VendorDTO.class))).willReturn(vendorDTO1);

        //when
        mockMvc.perform(put(VendorController.BASE_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(vendorDTO1))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("vendor_url", equalTo(VendorController.BASE_URL + "/1")));
    }

    @Test
    public void patchVendor() throws Exception {
        //when
        given(vendorService.patchVendor(anyLong(), any(VendorDTO.class))).willReturn(vendorDTO1);
        //then
        mockMvc.perform(patch(VendorController.BASE_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(vendorDTO1))

                ).andExpect(status().isOk())
                .andExpect(jsonPath("$.vendor_url", equalTo(VendorController.BASE_URL + "/1")));
    }

    @Test
    public void deleteVendor() throws Exception {
        mockMvc.perform(delete(VendorController.BASE_URL + "/1"))
                .andExpect(status().isOk());

        then(vendorService).should().deleteVendorById(anyLong());
    }

    @Test
    public void NotFoundException() throws Exception {

        given(vendorService.getVendorById(anyLong())).willThrow(ResourceNotFoundException.class);

        mockMvc.perform(get(VendorController.BASE_URL + "/222")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNotFound());
    }
}