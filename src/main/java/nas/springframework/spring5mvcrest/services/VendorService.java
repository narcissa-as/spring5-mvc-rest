package nas.springframework.spring5mvcrest.services;

import nas.springframework.spring5mvcrest.api.v1.model.VendorDTO;
import nas.springframework.spring5mvcrest.api.v1.model.VendorListDTO;

public interface VendorService {

    VendorListDTO getAllVendors();

    VendorDTO getVendorById(Long id);

    void deleteVendorById(Long id);

    VendorDTO createNewVendor(VendorDTO vendorDTO);
    //update with put
    VendorDTO saveVendorByDTO(Long id,VendorDTO vendorDTO);

    VendorDTO patchVendor(Long id,VendorDTO vendorDTO);
}
