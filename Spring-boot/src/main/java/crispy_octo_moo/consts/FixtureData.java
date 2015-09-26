package crispy_octo_moo.consts;

import crispy_octo_moo.dto.LabelObject;
import crispy_octo_moo.dto.LabelValueObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yangboz on 9/25/15.
 *
 * @see: http://stackoverflow.com/questions/507602/how-can-i-initialize-a-static-map
 */
public class FixtureData {

    public static final List<LabelValueObject> ElectricVehicleCredits = new ArrayList<LabelValueObject>() {{
        add(new LabelValueObject("Accord Plug-In Hybrid", (float) 3626));
        add(new LabelValueObject("Azure Dynamics Transit Connect Electric Vehicle", (float) 7500));
        add(new LabelValueObject("BMW i3 Sedan", (float) 7500));
        add(new LabelValueObject("BMW i8", (float) 3793));
        add(new LabelValueObject("Boulder Electric DV-500", (float) 7500));
        add(new LabelValueObject("BYD e6 Electric Vehicle", (float) 7500));
        add(new LabelValueObject("Fiat 500e", (float) 7500));
        add(new LabelValueObject("CODA Sedan", (float) 7500));
        add(new LabelValueObject("Electric Vehicles International (EVI) Electric truck", (float) 7500));
        add(new LabelValueObject("EMC Model E36", (float) 7500));
        add(new LabelValueObject("Fisker Karma", (float) 7500));
        add(new LabelValueObject("Ford Focus Electric", (float) 7500));
        add(new LabelValueObject("Ford C-MAX Energi", (float) 4007));
        add(new LabelValueObject("Ford Fusion Energi", (float) 4007));
        add(new LabelValueObject("Cadillac ELR", (float) 7500));
        add(new LabelValueObject("Chevrolet Volt", (float) 7500));
        add(new LabelValueObject("Chevrolet Spark EV", (float) 7500));
        add(new LabelValueObject("Kia Soul Electric", (float) 7500));
        add(new LabelValueObject("Mercedes-Benz smart Coupe/Cabrio EV", (float) 7500));
        add(new LabelValueObject("Mercedes-Benz B-Class EV", (float) 7500));
        add(new LabelValueObject("Mitsubishi i-MiEV", (float) 7500));
        add(new LabelValueObject("Nissan Leaf", (float) 7500));
        add(new LabelValueObject("Porsche 918 Spyder", (float) 3667));
        add(new LabelValueObject("Porsche Panamera S E Hybrid", (float) 4751.80));
        add(new LabelValueObject("Porsche Caynee S E-Hybrid", (float) 5335.60));
        add(new LabelValueObject("smart fortwo", (float) 7500));
        add(new LabelValueObject("Tesla Roadster", (float) 7500));
        add(new LabelValueObject("Tesla Model S", (float) 7500));
        add(new LabelValueObject("Think City EV", (float) 7500));
        add(new LabelValueObject("Toyota Prius Plug-in Electic Drive Vehicle", (float) 2500));
        add(new LabelValueObject("Toyota RAV4 EV", (float) 7500));
        add(new LabelValueObject("VIA 2500", (float) 7500));
        add(new LabelValueObject("VIA 1500", (float) 7500));
        add(new LabelValueObject("Volkswagen e-Golf", (float) 7500));
        add(new LabelValueObject("Wheego LiFe Electric Vehicle", (float) 7500));
        add(new LabelValueObject("Zenith Electric Van", (float) 7500));
    }};

    public static final List<LabelObject> ChildrenKeywords = new ArrayList<LabelObject>() {{
        add(new LabelObject("new baby"));
        add(new LabelObject("my daughter"));
        add(new LabelObject("my son"));
    }};

}
