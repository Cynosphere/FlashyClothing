package pm.c7.skinblinker;

import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;
import me.sargunvohra.mcmods.autoconfig1u.annotation.ConfigEntry;
import me.sargunvohra.mcmods.autoconfig1u.serializer.PartitioningSerializer;
import net.minecraft.client.render.entity.PlayerModelPart;

@Config(name = "flashyclothing")
public class FlashyClothingConfig extends PartitioningSerializer.GlobalData {
    @ConfigEntry.Category("main")
    @ConfigEntry.Gui.TransitiveObject
    public CategoryMain main = new CategoryMain();

    @Config(name = "main")
    public static class CategoryMain implements ConfigData {
        public boolean enabled = false;
        public boolean blinkCape = false;
        @ConfigEntry.BoundedDiscrete(min = 1, max = 200)
        public int blinkCapeInterval = 20;
        public boolean blinkJacket = true;
        @ConfigEntry.BoundedDiscrete(min = 1, max = 200)
        public int blinkJacketInterval = 20;
        public boolean blinkLeftArm = true;
        @ConfigEntry.BoundedDiscrete(min = 1, max = 200)
        public int blinkLeftArmInterval = 20;
        public boolean blinkRightArm = true;
        @ConfigEntry.BoundedDiscrete(min = 1, max = 200)
        public int blinkRightArmInterval = 20;
        public boolean blinkLeftLeg = true;
        @ConfigEntry.BoundedDiscrete(min = 1, max = 200)
        public int blinkLeftLegInterval = 20;
        public boolean blinkRightLeg = true;
        @ConfigEntry.BoundedDiscrete(min = 1, max = 200)
        public int blinkRightLegInterval = 20;
        public boolean blinkHat = true;
        @ConfigEntry.BoundedDiscrete(min = 1, max = 200)
        public int blinkHatInterval = 20;

        public int getInterval(PlayerModelPart part) {
            switch (part) {
                case HAT:
                    return blinkHatInterval;
                case CAPE:
                    return blinkCapeInterval;
                case JACKET:
                    return blinkJacketInterval;
                case LEFT_SLEEVE:
                    return blinkLeftArmInterval;
                case RIGHT_SLEEVE:
                    return blinkRightArmInterval;
                case LEFT_PANTS_LEG:
                    return blinkLeftLegInterval;
                case RIGHT_PANTS_LEG:
                    return blinkRightLegInterval;
                default:
                    throw new AssertionError("No case defined for " + part);
            }
        }

        public boolean getPartEnabled(PlayerModelPart part) {
            switch (part) {
                case HAT:
                    return blinkHat;
                case CAPE:
                    return blinkCape;
                case JACKET:
                    return blinkJacket;
                case LEFT_SLEEVE:
                    return blinkLeftArm;
                case RIGHT_SLEEVE:
                    return blinkRightArm;
                case LEFT_PANTS_LEG:
                    return blinkLeftLeg;
                case RIGHT_PANTS_LEG:
                    return blinkRightLeg;
                default:
                    throw new AssertionError("No case defined for " + part);
            }
        }
    }
}
