package nas.springframework.spring5mvcrest.services;

import nas.springframework.spring5mvcrest.api.v1.mapper.VendorMapper;
import nas.springframework.spring5mvcrest.api.v1.model.VendorDTO;
import nas.springframework.spring5mvcrest.api.v1.model.VendorListDTO;
import nas.springframework.spring5mvcrest.domain.Vendor;
import nas.springframework.spring5mvcrest.repositories.VendorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

//bring Spring small web context(Front-End) in test
class VendorServiceImplTest {

    static final String NAME_1 = "My Vendor";
    static final Long ID_1 = 1l;
    static final String NAME_2 = "My Vendor";
    static final Long ID_2 = 2l;

    @Mock
    VendorRepository vendorRepository;

    VendorMapper vendorMapper = VendorMapper.INSTANCE;


    VendorService vendorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        vendorService = new VendorServiceImpl(vendorRepository, vendorMapper);
    }

    @Test
    public void getAllVendors() throws Exception {

        //given
        List<Vendor> vendors = Arrays.asList(getVendor1(), getVendor2());
        //when
        //mockito BDD syntax
        given(vendorRepository.findAll()).willReturn(vendors);

        //then
        VendorListDTO vendorListDTO = vendorService.getAllVendors();

        //then
        then(vendorRepository).should(times(1)).findAll();
        assertThat(vendorListDTO.getVendors().size(), is(equalTo(2)));
    }

    @Test
    public void getVendorById() throws Exception {
        //given
        Vendor vendor = getVendor1();

        //  vendorDTO.setVendorUrl(VendorController.BASE_URL + "/1");
        //mockito BDD syntax (Behaviour Driven Development)
        given(vendorRepository.findById(anyLong())).willReturn(Optional.of(vendor));
        //when
        VendorDTO vendorDTO = vendorService.getVendorById(1l);
        //then
        then(vendorRepository).should(times(1)).findById(anyLong());
        // assertThat(vendorDTO.getName(), is(equalTo(NAME_1)));
    }

    @Test
    public void createVendor() throws Exception {

        //given
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(NAME_1);

        Vendor vendor = getVendor1();

        given(vendorRepository.save(any(Vendor.class))).willReturn(vendor);
        //when
        VendorDTO savedVendorDTO = vendorService.createNewVendor(vendorDTO);

        //then
        //'should' defaults to times =1
        then(vendorRepository).should().save(any(Vendor.class));
        assertThat(savedVendorDTO.getVendorUrl(), containsString("1"));

    }

    @Test
    public void saveVendorByDTO() throws Exception {
        //given
        Vendor vendor = getVendor1();

        //defining VendorDTO as an input for vendorService.saveVendorByDTO
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(NAME_1);

        given(vendorRepository.save(any(Vendor.class))).willReturn(vendor);
        //when
        VendorDTO savedVendorDTO = vendorService.saveVendorByDTO(ID_1, vendorDTO);

        //then
        //'should' defaults to times = 1
        then(vendorRepository).should().save(any(Vendor.class));
        assertThat(savedVendorDTO.getVendorUrl(), containsString("1"));

    }

    @Test
    public void patchVendor() throws Exception {

        //given
        Vendor vendor = getVendor1();

        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(NAME_1);

        given(vendorRepository.findById(anyLong())).willReturn(Optional.of(vendor));
        given(vendorRepository.save(any(Vendor.class))).willReturn(vendor);

        VendorDTO savedVendorDTO = vendorService.patchVendor(ID_1, vendorDTO);

        //then
        then(vendorRepository).should().save(any(Vendor.class));
        then(vendorRepository).should(times(1)).findById(anyLong());
        assertThat(savedVendorDTO.getVendorUrl(), containsString("1"));

    }

    @Test
    public void deleteById() throws Exception {

        //when
        vendorService.deleteVendorById(1l);

        //then
        then(vendorRepository).should().deleteById(anyLong());

    }

    @Test
    public void NotFoundException() {

        given(vendorRepository.findById(anyLong())).willReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            VendorDTO vendorDTO = vendorService.getVendorById(1l);
        });
    }

    private Vendor getVendor1() {

        Vendor vendor = new Vendor();
        vendor.setId(ID_1);
        vendor.setName(NAME_1);
        return vendor;
    }

    private Vendor getVendor2() {

        Vendor vendor = new Vendor();
        vendor.setId(ID_1);
        vendor.setName(NAME_1);
        return vendor;

    }
}