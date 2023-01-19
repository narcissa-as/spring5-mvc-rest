package nas.springframework.spring5mvcrest.api.v1.mapper;

import nas.springframework.spring5mvcrest.api.v1.model.VendorDTO;
import nas.springframework.spring5mvcrest.domain.Vendor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class VendorMapperTest {

    private static final String NAME = "My Vendor";

    VendorMapper vendorMapper = VendorMapper.INSTANCE;


    @Test
    void vendorToVendorDTO() {
        //given
        Vendor vendor = new Vendor();
        vendor.setName(NAME);
        //when
        VendorDTO returnedDTO = vendorMapper.vendorToVendorDTO(vendor);
        assertEquals(returnedDTO.getName(), vendor.getName());
    }

    @Test
    void vendorDTOToVendor() throws Exception {

        //given
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(NAME);

        //when
        Vendor returnedVendor = vendorMapper.vendorDTOToVendor(vendorDTO);

        //then
        assertEquals(returnedVendor.getName(), vendorDTO.getName());
    }
}