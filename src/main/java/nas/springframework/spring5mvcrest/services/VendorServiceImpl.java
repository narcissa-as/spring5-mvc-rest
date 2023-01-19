package nas.springframework.spring5mvcrest.services;

import nas.springframework.spring5mvcrest.api.v1.mapper.VendorMapper;
import nas.springframework.spring5mvcrest.api.v1.model.VendorDTO;
import nas.springframework.spring5mvcrest.api.v1.model.VendorListDTO;
import nas.springframework.spring5mvcrest.controllers.v1.VendorController;
import nas.springframework.spring5mvcrest.domain.Vendor;
import nas.springframework.spring5mvcrest.repositories.VendorRepository;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository;
    private final VendorMapper vendorMapper;

    public VendorServiceImpl(VendorRepository vendorRepository, VendorMapper vendorMapper) {
        this.vendorRepository = vendorRepository;
        this.vendorMapper = vendorMapper;
    }

    @Override
    public VendorListDTO getAllVendors() {
        List<VendorDTO> vendorDTOS = vendorRepository
                .findAll()
                .stream()
                .map(vendor -> {
                    VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);

                    vendorDTO.setVendorUrl(getVendorUrl(vendor.getId()));
                    return vendorDTO;

                })
                .collect(Collectors.toList());
        return new VendorListDTO(vendorDTOS);
    }

    @Override
    public VendorDTO getVendorById(Long id) {

        return vendorRepository.findById(id)
                .map(vendorMapper::vendorToVendorDTO)
                .map(vendorDTO -> {
                    vendorDTO.setVendorUrl(getVendorUrl(id));
                    return vendorDTO;
                })
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public VendorDTO createNewVendor(VendorDTO vendorDTO) {

        return saveAndReturnDTO(vendorMapper.vendorDTOToVendor(vendorDTO));
    }

    //update for a whole object, insert
    @Override
    public VendorDTO saveVendorByDTO(Long id, VendorDTO vendorDTO) {

        Vendor vendorToSave = new Vendor();
        vendorToSave.setId(id);

        return saveAndReturnDTO(vendorToSave);

    }


    @Override
    public VendorDTO patchVendor(Long id, VendorDTO vendorDTO) {

        //if we have this object in DB, then update it, otherwise throw Exception
        return vendorRepository.findById(id)
                .map(vendor -> {
                            if (vendorDTO.getName() != null) {
                                vendor.setName(vendor.getName());
                            }

                            return saveAndReturnDTO(vendor);
                        }

                ).orElseThrow(ResourceNotFoundException::new);


    }


    @Override
    public void deleteVendorById(Long id) {

        //  if (!vendorRepository.findById(id).isPresent()) {
        //       throw new ResourceNotFoundException();
        //  } else {
        //     vendorRepository.deleteById(id);
        // }
        vendorRepository.deleteById(id);

    }


    private String getVendorUrl(Long id) {

        return (VendorController.BASE_URL + "/" + id);
    }

    private VendorDTO saveAndReturnDTO(Vendor vendor) {

        Vendor savedVendor = vendorRepository.save(vendor);

        VendorDTO returnDto = vendorMapper.vendorToVendorDTO(savedVendor);
        returnDto.setVendorUrl(getVendorUrl(savedVendor.getId()));

        return returnDto;
    }
}

