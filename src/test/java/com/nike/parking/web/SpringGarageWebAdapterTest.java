/*
 * COPYRIGHT:     Copyright reserved by Pulkit Mehra
 */
package com.nike.parking.web;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.nike.parking.core.GarageService;
import com.nike.parking.model.Garage;
import com.nike.parking.model.ParkingSlot;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * The Class SpringGarageWebAdapterTest.
 *
 * @author pulkit.mehra
 * Created: Nov 19, 2015
 */
public class SpringGarageWebAdapterTest {

    /** The Constant GARAGE_ID_A. */
    private static final int GARAGE_ID_A = 1;

    /** The Constant GARAGE_A_PS_2. */
    private static final int GARAGE_A_PS_2 = 12;

    /** The Constant GARAGE_A_PS_1. */
    private static final int GARAGE_A_PS_1 = 11;

    /** The garage service. */
    @Mock
    public GarageService garageService;

    /** The spring garage web adapter. */
    private SpringGarageWebAdapter springGarageWebAdapter;

    /** The mock mvc. */
    private MockMvc mockMvc;

    /**
     * Sets the up.
     */
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        springGarageWebAdapter = new SpringGarageWebAdapter(garageService);
        mockMvc = MockMvcBuilders.standaloneSetup(springGarageWebAdapter).build();
    }

    /**
     * Gets the parking slots test.
     *
     * @return the parking slots test
     * @throws Exception the exception
     */
    @Test
    public void getParkingSlotsTest() throws Exception {
        when(garageService.getParkingSlots(GARAGE_ID_A, false)).thenReturn(getGarage().getParkingSlots());

        mockMvc.perform(get("/garage/1/parkingSlots").accept(APPLICATION_JSON))
            .andExpect(content().contentType(APPLICATION_JSON)).andExpect(status().isOk())
            .andExpect(jsonPath("$ParkingSlots[0].parkingSlotID", is(GARAGE_A_PS_1)))
            .andExpect(jsonPath("$ParkingSlots[1].parkingSlotID", is(GARAGE_A_PS_2)));

        verify(garageService).getParkingSlots(GARAGE_ID_A, false);
    }

    /**
     * Gets the garage.
     *
     * @return the garage
     */
    private static Garage getGarage() {

        Set<ParkingSlot> parkingSlotsSet = new LinkedHashSet<>();
        ParkingSlot p = getParkingSlot();
        parkingSlotsSet.add(p);

        p = new ParkingSlot();
        p.setParkingSlotID(GARAGE_A_PS_2);
        parkingSlotsSet.add(p);

        Garage g = new Garage();
        g.setGarageID(GARAGE_ID_A);
        g.setParkingSlots(parkingSlotsSet);
        return g;
    }

    /**
     * Minus hours by.
     *
     * @param hoursBefore the hours before
     * @return the local date time
     */
    private static LocalDateTime minusHoursBy(int hoursBefore) {
        return LocalDateTime.now().minusHours(hoursBefore);
    }

    /**
     * Gets the parking slot.
     *
     * @return the parking slot
     */
    private static ParkingSlot getParkingSlot() {
        ParkingSlot p = new ParkingSlot();
        p.setParkingSlotID(GARAGE_A_PS_1);
        p.setAvailable(false);
        p.setParkedSince(minusHoursBy(5));
        return p;
    }

}
