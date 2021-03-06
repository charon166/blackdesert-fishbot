package ru.namibios.arduino.config.converter;

import org.aeonbits.owner.Converter;
import org.apache.log4j.Logger;
import ru.namibios.arduino.model.HotSlot;

import java.lang.reflect.Method;

public class HotSlotConverter implements Converter<HotSlot> {

    private static final Logger LOG = Logger.getLogger(HotSlotConverter.class);

    private HotSlot getSlot(String [] params){

        HotSlot hotSlot = new HotSlot();

        hotSlot.setActive(Boolean.valueOf(params[0]));
        hotSlot.setKey(params[1]);
        hotSlot.setDelay(params[2]);
        hotSlot.setPeriod(params[3]);
        hotSlot.setCommand("Slot");

        return hotSlot;
    }

    private HotSlot getSlotTask(String [] params){

        HotSlot hotSlot = new HotSlot();

        hotSlot.setActive(Boolean.valueOf(params[0]));
        hotSlot.setKey(params[1]);
        hotSlot.setDelay(params[2]);
        hotSlot.setPeriod(params[3]);
        hotSlot.setCommand(params[4]);

        return hotSlot;
    }

    private HotSlot getSmartTask(String [] params){

        HotSlot hotSlot = new HotSlot();

        hotSlot.setActive(Boolean.valueOf(params[0]));
        hotSlot.setDelay(params[1]);
        hotSlot.setCommand(params[2]);

        return hotSlot;
    }

    @Override
    public HotSlot convert(Method method, String input) {

        HotSlot hotSlot = new HotSlot();

        String[] split = input.replaceAll("\\s", "").split(",");
        switch (split.length) {
            case 3: hotSlot = getSmartTask(split); break;
            case 4: hotSlot = getSlot(split); break;
            case 5: hotSlot = getSlotTask(split); break;

            default: {
                LOG.error("Unknown constructor. Check: " + input);
                hotSlot = null;
            }
        }

        return hotSlot;
    }

}